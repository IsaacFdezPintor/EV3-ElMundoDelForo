package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOTexto;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Texto;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ForoControllerComun {

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
        List<Foro> foros = daoForo.findAll();
        listaForos.getItems().setAll(foros);
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
     * Publica un comentario nuevo en el foro seleccionado.
     *
     * @throws SQLException si ocurre un error al insertar el comentario.
     */
    @FXML
    private void PublicarComentario() throws SQLException {
        String comentarioTexto = campoComentario.getText().trim();

        if (comentarioTexto.isEmpty()) {
            mensajeAlerta.setText("El comentario no puede estar vacío.");
            return;
        }

        Usuario usuarioActual = SesionUsuario.getUsuario();

        if (usuarioActual == null) {
            mensajeAlerta.setText("No hay un usuario logueado.");
            return;
        }

        Foro foroSeleccionado = listaForos.getSelectionModel().getSelectedItem();

        if (foroSeleccionado == null) {
            mensajeAlerta.setText("Debes seleccionar un foro para comentar.");
            return;
        }

        Texto nuevoComentario = new Texto();
        nuevoComentario.setTexto(comentarioTexto);
        nuevoComentario.setId_foro(foroSeleccionado.getId_foro());
        nuevoComentario.setId_usuario(usuarioActual.getId_Usuario());

        DAOTexto daoTexto = new DAOTexto();
        boolean comentarioPublicado = daoTexto.insert(usuarioActual, foroSeleccionado, nuevoComentario);

        if (comentarioPublicado) {
            cargarComentarios(foroSeleccionado);
            campoComentario.clear();
            mensajeAlerta.setText("Comentario publicado con éxito.");
        } else {
            mensajeAlerta.setText("No se pudo publicar el comentario.");
        }
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
        DAOForo daoForo = new DAOForo();
        List<Foro> foros = daoForo.findAll();
        listaForos.getItems().setAll(foros);
    }
}
