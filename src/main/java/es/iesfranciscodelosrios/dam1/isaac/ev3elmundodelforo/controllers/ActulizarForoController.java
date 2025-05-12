package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.List;

public class ActulizarForoController {

    @FXML
    private ComboBox<Foro> ListaForos;  // ComboBox para seleccionar el foro

    @FXML
    private TextField nombreForoField;      // Campo de texto para el nombre del foro

    @FXML
    private TextArea descripcionField;      // TextArea para la descripción del foro

    @FXML
    private Button btnActualizarForo;       // Botón para actualizar el foro

    @FXML
    private Label mensajeLabel;             // Label para mostrar mensajes

    private DAOForo daoForo;

    // Este método se llama cuando se inicializa la vista FXML
    public void initialize() {
        daoForo = new DAOForo();
        cargarForos();
    }

    // Método para cargar los foros en el ComboBox
    private void cargarForos() {
        try {
            // Llamada al método findAll() para obtener los foros desde la base de datos
            List<Foro> foros = daoForo.findAll();

            // Crear una lista observable de foros para el ComboBox
            ObservableList<Foro> forosObservableList = FXCollections.observableArrayList(foros);

            // Configurar el ComboBox con la lista de foros
            ListaForos.setItems(forosObservableList);

            // Configurar cómo se deben mostrar los foros en el ComboBox
            ListaForos.setCellFactory(lv -> new ListCell<Foro>() {
                @Override
                protected void updateItem(Foro item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getTitulo());  // Mostrar el título del foro en el ComboBox
                    }
                }
            });

            // Configurar cómo se debe mostrar el foro seleccionado
            ListaForos.setButtonCell(new ListCell<Foro>() {
                @Override
                protected void updateItem(Foro item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null) {
                        setText(null);
                    } else {
                        setText(item.getTitulo());  // Mostrar el título del foro en el botón del ComboBox
                    }
                }
            });

        } catch (SQLException e) {
            mensajeLabel.setText("Error al cargar los foros: " + e.getMessage());
        }
    }

    // Método para actualizar el foro
    @FXML
    private void actualizarForo() {
        // Obtener el foro seleccionado del ComboBox
        Foro foroSeleccionado = ListaForos.getValue();
        if (foroSeleccionado != null) {
            // Obtener los nuevos valores para el nombre y descripción
            String nombreForoNuevo = nombreForoField.getText();
            String descripcionForoNuevo = descripcionField.getText();

            try {
                // Llamada al método de actualización en el DAO (debes implementarlo en DAOForo)
                SesionUsuario usuario = new SesionUsuario();
                boolean actualizado = daoForo.update(foroSeleccionado,usuarioget );
                if (actualizado) {
                    mensajeLabel.setText("Foro actualizado correctamente.");
                } else {
                    mensajeLabel.setText("Error al actualizar el foro.");
                }
            } catch (SQLException e) {
                mensajeLabel.setText("Error al actualizar el foro: " + e.getMessage());
            }
        } else {
            mensajeLabel.setText("Por favor, selecciona un foro.");
        }
    }
}
