package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.HelloApplication;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ForoControllerComun {

    @FXML
    private ListView<Foro> listaForos;

    @FXML
    private Label labelDescripcionForo;

    @FXML
    private Label labelCreadorForo;

    @FXML
    private Button btnMiInformacion;

    @FXML
    private Button btnCerrarSesion;



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
        DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
        List<Foro> foros = daoForo.findAll();
        listaForos.getItems().setAll(foros);

        if (!foros.isEmpty()) {
            Foro primerForo = foros.get(0);
            labelDescripcionForo.setText("Descripción: " + primerForo.getDescripcion());

            // Obtener el creador de ese primer foro
            //String creador = daoUsuarioCreador.findByTitulo(primerForo.getTitulo()).getNombre();
            //labelCreadorForo.setText("Creador: " + creador);
        }

        // Agregar un manejador de eventos para cuando se selecciona un foro
        listaForos.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Si hay un foro seleccionado, actualiza los labels con su información
                labelDescripcionForo.setText("Descripción: " + newValue.getDescripcion());

                // Obtener el creador de este foro
                //String creador = daoUsuarioCreador.findByTitulo(newValue.getTitulo()).getNombre();
                //labelCreadorForo.setText("Creador: " + creador);
            } else {
                labelDescripcionForo.setText("Descripción: ");
                labelCreadorForo.setText("Creador: ");
            }


        });


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
    @FXML
    public void cerrarSesion (ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Login");
        stage.setResizable(false);
        stage.show();
        SesionUsuario.cerrarSesion();
        Stage currentStage = (Stage) btnCerrarSesion.getScene().getWindow();
        currentStage.close();
    }
}
