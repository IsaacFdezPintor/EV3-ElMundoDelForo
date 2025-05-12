package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.HelloApplication;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import org.w3c.dom.events.MouseEvent;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ForoControllerCreador {
        @FXML
        private ListView<Foro> listaForos;

        @FXML
        private Button btnEliminar;

        @FXML
        private Label labelDescripcionForo;

         @FXML
        private Label labelCreadorForo;

        @FXML
        private Button btnMiInformacion;

    public void initialize() throws SQLException {
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

        DAOForo daoForo = new DAOForo();
        List<Foro> foros = daoForo.findAll();
        listaForos.getItems().setAll(foros);

    }

    public void mostrarForoSeleccionado (javafx.scene.input.MouseEvent mouseEvent) throws SQLException {
            Foro foroSeleccionado = listaForos.getSelectionModel().getSelectedItem();
            DAOForo daoForo = new DAOForo();

            if (foroSeleccionado != null) {
                labelDescripcionForo.setText("Descripción: " + foroSeleccionado.getDescripcion());
                String creador = daoForo.findCreador(foroSeleccionado).getNombre();
                labelCreadorForo.setText("Creador: " + creador);
            }
        }


    @FXML
    private void eliminarItemSeleccionado() {
        Foro seleccionado = listaForos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaForos.getItems().remove(seleccionado);
            labelDescripcionForo.setText("Descripción: ");
            labelCreadorForo.setText("Creador: ");
        } else {
            Alert alert = new Alert(AlertType.WARNING, "No has seleccionado ningún foro.");
            alert.showAndWait();
        }
    }

    @FXML
    public void anadirNuevoForo(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("nuevoforo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir Foro");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void actulizarForo (ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("actualizarforo.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Actualizar Foro");
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    public void miInformacion (ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("miInformacion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Mi Información");
        stage.setResizable(false);
        stage.show();
    }

}
