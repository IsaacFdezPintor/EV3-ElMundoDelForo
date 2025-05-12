package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.HelloApplication;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ForoControllerCreador {

    // Elementos FXML
    @FXML private ListView<Foro> listaForos;
    @FXML private Button btnEliminar;
    @FXML private Label labelDescripcionForo;
    @FXML private Label labelCreadorForo;
    @FXML private Button btnMiInformacion;
    @FXML private Button btnCerrarSesion;

    // Método de inicialización para configurar la lista de foros
    public void initialize() throws SQLException {
        configurarListaForos();
        cargarForos();
    }

    // Configura la lista de foros para mostrar solo los títulos
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

    // Carga todos los foros desde la base de datos
    private void cargarForos() throws SQLException {
        DAOForo daoForo = new DAOForo();
        List<Foro> foros = daoForo.findAll();
        listaForos.getItems().setAll(foros);
    }

    // Muestra la información del foro seleccionado
    public void mostrarForoSeleccionado(MouseEvent mouseEvent) throws SQLException {
        Foro foroSeleccionado = listaForos.getSelectionModel().getSelectedItem();
        if (foroSeleccionado != null) {
            DAOForo daoForo = new DAOForo();
            labelDescripcionForo.setText("Descripción: " + foroSeleccionado.getDescripcion());
            String creador = daoForo.findCreador(foroSeleccionado).getNombre();
            labelCreadorForo.setText("Creador: " + creador);
        }
    }

    // Elimina el foro seleccionado de la lista
    @FXML
    private void eliminarItemSeleccionado() throws SQLException {
        Foro foroSeleccionado = listaForos.getSelectionModel().getSelectedItem();
        DAOForo daoForo = new DAOForo();
        if (foroSeleccionado != null) {
            daoForo.delete(foroSeleccionado);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "No has seleccionado ningún foro.");
            alert.showAndWait();
        }
    }

    // Abre la ventana para añadir un nuevo foro
    @FXML
    public void anadirNuevoForo(ActionEvent actionEvent) throws IOException {
        abrirNuevaVentana("nuevoforo.fxml", "Añadir Foro");
        actualizarListaForos();
    }

    // Abre la ventana para actualizar un foro
    @FXML
    public void actualizarForo(ActionEvent actionEvent) throws IOException {
        abrirNuevaVentana("actualizarforo.fxml", "Actualizar Foro");
    }

    // Abre la ventana con la información personal del usuario
    @FXML
    public void miInformacion(ActionEvent actionEvent) throws IOException {
        abrirNuevaVentana("miInformacion.fxml", "Mi Información");
    }

    // Cierra la sesión y redirige al login
    @FXML
    public void cerrarSesion(ActionEvent actionEvent) throws IOException {
        abrirNuevaVentana("login.fxml", "Login");
        SesionUsuario.cerrarSesion();
        Stage currentStage = (Stage) btnCerrarSesion.getScene().getWindow();
        currentStage.close();
    }

    // Método auxiliar para abrir una nueva ventana con el FXML correspondiente
    private void abrirNuevaVentana(String fxml, String titulo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.setResizable(false);
        stage.show();
    }

    // Actualiza la lista de foros tras añadir uno nuevo
    private void actualizarListaForos() {
        DAOForo daoForo = new DAOForo();
        List<Foro> foros = daoForo.findAll();
        listaForos.getItems().setAll(foros);
    }
}
