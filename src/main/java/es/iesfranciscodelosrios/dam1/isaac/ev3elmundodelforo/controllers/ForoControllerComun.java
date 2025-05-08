package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.sql.SQLException;
import java.util.List;

public class ForoControllerComun {

    @FXML
    private ListView<Foro> listaForos;

    @FXML
    private Label labelDescripcionForo;

    @FXML
    private Label labelCreadorForo;

    public void initialize() throws SQLException {
        // Establecer cómo mostrar los foros en la lista
        listaForos.setCellFactory(lv -> new ListCell<Foro>() {
            @Override
            protected void updateItem(Foro item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getTitulo());  // Mostrar solo el título del foro en la lista
                }
            }
        });

        DAOForo daoForo = new DAOForo();
        DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
        List<Foro> foros = daoForo.findAll();  // Obtener todos los foros
        listaForos.getItems().setAll(foros);   // Rellenar la lista con los foros

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
}
