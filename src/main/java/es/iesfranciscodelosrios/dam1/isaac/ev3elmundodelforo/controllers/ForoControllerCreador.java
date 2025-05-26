package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOTexto;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.Start;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Texto;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Controlador de la pantalla principal del foro para usuarios creadores.
 */

public class ForoControllerCreador {

    @FXML private ListView<Foro> listaForos;
    @FXML private Button btnEliminar;
    @FXML private Label labelDescripcionForo;
    @FXML private Label labelCreadorForo;
    @FXML private Button btnMiInformacion;
    @FXML private Button btnCerrarSesion;
    @FXML private ListView<Texto> listaComentarios;
    @FXML private Button btnPublicarComentario;
    @FXML private TextArea campoComentario;
    @FXML private Label mensajeAlerta;

    /**
     * Inicializa el controlador cargando la lista de foros disponibles.
     *
     * @throws SQLException si ocurre un error al acceder a la base de datos.
     */
    public void initialize() throws SQLException {
        configurarListaForos();
        cargarForos();
    }

    /**
     * Configura cómo se mostrarán los foros en la ListView (solo el título).
     */
    private void configurarListaForos() {
        listaForos.setCellFactory(lv -> new ListCell<Foro>() {
            @Override
            protected void updateItem(Foro item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitulo());
                }
            }
        });
    }

    /**
     * Carga todos los foros desde la base de datos.
     *
     * @throws SQLException si ocurre un error en el acceso a los datos.
     */
    private void cargarForos() throws SQLException {
        DAOForo daoForo = new DAOForo();
        Usuario usuarioActual = SesionUsuario.getUsuario();

        if (usuarioActual != null) {
            List<Foro> foros = daoForo.findForosByID(usuarioActual.getId_Usuario());
            listaForos.getItems().setAll(foros);
        }
    }


    /**
     * Carga los comentarios asociados a un foro específico.
     *
     * @param foro Foro del cual se desean cargar los comentarios.
     * @throws SQLException si ocurre un error en la consulta.
     */
    private void cargarComentarios(Foro foro) throws SQLException {
        DAOTexto daoTexto = new DAOTexto();
        List<Texto> comentarios = daoTexto.findAllByForoId(foro.getId_foro());
        listaComentarios.getItems().setAll(comentarios);
    }


    /**
     * Muestra la descripción y creador del foro seleccionado.
     *
     * @param mouseEvent Evento de selección con el ratón.
     * @throws SQLException si ocurre un error al obtener los datos del foro.
     */
    @FXML
    public void mostrarForoSeleccionado(MouseEvent mouseEvent) throws SQLException {
        Foro foroSeleccionado = listaForos.getSelectionModel().getSelectedItem();
        if (foroSeleccionado != null) {
            DAOForo daoForo = new DAOForo();
            labelDescripcionForo.setText("Descripción: " + foroSeleccionado.getDescripcion());
            String creador = daoForo.findCreador(foroSeleccionado).getNombre();
            labelCreadorForo.setText("Creador: " + creador);
            cargarComentarios(foroSeleccionado);
        }
    }

    /**
     * Elimina el foro o comentario seleccionado por el usuario.
     *
     * Este método verifica si el usuario ha seleccionado un foro o comentario.
     * Si el usuario ha seleccionado un comentario, se eliminará este comentario.
     * Si ha seleccionado un foro, se verificará que el usuario que intenta eliminar el foro sea el creador del mismo.
     * Si es el creador, el foro se eliminará. De lo contrario, se mostrará un mensaje de error.
     *
     * @throws SQLException si ocurre un error durante la eliminación en la base de datos.
     */
    @FXML
    private void eliminarItemSeleccionado() throws SQLException {
        Foro foroSeleccionado = listaForos.getSelectionModel().getSelectedItem();
        Texto comentarioSeleccionado = listaComentarios.getSelectionModel().getSelectedItem();

        if (comentarioSeleccionado != null) {
            DAOTexto daoTexto = new DAOTexto();
            boolean eliminado = daoTexto.delete(comentarioSeleccionado);
            if (eliminado) {
                mensajeAlerta.setText("Comentario eliminado con éxito.");
                cargarComentarios(foroSeleccionado);
            } else {
                mensajeAlerta.setText("No se pudo eliminar el comentario.");
            }
            return;
        }

        if (foroSeleccionado != null) {
            Usuario usuarioActual = SesionUsuario.getUsuario();

            if (usuarioActual != null && foroSeleccionado.getCreador().getId_Usuario() == usuarioActual.getId_Usuario()) {
                DAOForo daoForo = new DAOForo();
                boolean eliminado = daoForo.delete(foroSeleccionado);

                if (eliminado) {
                    mensajeAlerta.setText("Foro eliminado con éxito.");
                    cargarForos();
                    listaComentarios.getItems().clear();
                    labelDescripcionForo.setText("Descripción:");
                    labelCreadorForo.setText("Creador:");
                } else {
                    mensajeAlerta.setText("No se pudo eliminar el foro.");
                }
            } else {
                mensajeAlerta.setText("Tienes que ser propietario del foro para eliminarlo.");
            }
        } else {
            mensajeAlerta.setText("Debes seleccionar un foro o comentario para eliminarlo.");
        }
    }


    /**
     * Abre una ventana para añadir un nuevo foro.
     *
     * @param actionEvent Evento del botón.
     * @throws IOException si falla la carga del FXML.
     */
    @FXML
    public void anadirNuevoForo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("nuevoforo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Crear Foro");
        stage.showAndWait();
        actualizarListaForos();
    }

    /**
     * Abre una ventana para actualizar un foro.
     *
     * @param actionEvent Evento del botón.
     * @throws IOException si falla la carga del FXML.
     */
    @FXML
    public void actualizarForo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("actualizarforo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Actualizar Foro");
        stage.showAndWait();
        actualizarListaForos();
    }

    /**
     * Abre la ventana de información personal del usuario.
     *
     * @param actionEvent Evento del botón.
     * @throws IOException si falla la carga del FXML.
     */
    @FXML
    public void miInformacion(ActionEvent actionEvent) throws IOException {
        ViewUtils.abrirNuevaVentanaFija("miInformacion.fxml", "Mi Información");
    }

    /**
     * Cierra la sesión del usuario y redirige al login.
     *
     * @param actionEvent Evento del botón.
     * @throws IOException si falla la carga del FXML.
     */
    @FXML
    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        ViewUtils.abrirNuevaVentanaFija("login.fxml", "Login");
        SesionUsuario.cerrarSesion();
        Stage currentStage = (Stage) btnCerrarSesion.getScene().getWindow();
        currentStage.close();
    }


    /**
     * Actualiza la lista de foros visibles en pantalla.
     */
    private void actualizarListaForos() {
        try {
            DAOForo daoForo = new DAOForo();
            Usuario usuarioActual = SesionUsuario.getUsuario();

            if (usuarioActual != null) {
                List<Foro> foros = daoForo.findForosByID(usuarioActual.getId_Usuario());
                listaForos.getItems().setAll(foros);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
