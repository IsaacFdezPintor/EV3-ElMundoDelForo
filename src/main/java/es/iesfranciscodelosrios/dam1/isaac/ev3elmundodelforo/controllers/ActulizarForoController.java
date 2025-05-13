package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

/**
 * Controlador de la pantalla para actualizar un foro ya creado por el usuario.
 */
public class ActulizarForoController {

    @FXML private ComboBox<Foro> listaForos;
    @FXML private TextField nombreForo;
    @FXML private TextArea descripcionActualizar;
    @FXML private Label mensajeLabel;
    @FXML private Button btnActualizarForo;

    /**
     * Método de inicialización que se ejecuta al cargar la vista.
     * Configura la lista de foros y carga los foros del usuario actual.
     *
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    public void initialize() throws SQLException {
        configurarListaForos();
        cargarForos();
    }

    /**
     * Configura la lista de foros para mostrar solo los títulos de los mismos.
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
     * Carga todos los foros creados por el usuario actual en la lista desplegable.
     *
     * @throws SQLException si ocurre un error al acceder a la base de datos
     */
    private void cargarForos() throws SQLException {
        Usuario usuarioActual = SesionUsuario.getUsuario();

        if (usuarioActual == null) {
            mensajeLabel.setText("No hay un usuario logueado.");
            return;
        }

        DAOForo daoForo = new DAOForo();
        List<Foro> foros = daoForo.findForosByID(usuarioActual.getId_Usuario());

        listaForos.getItems().setAll(foros);
    }

    /**
     * Método llamado cuando el usuario presiona el botón de actualizar foro.
     * Actualiza el foro seleccionado con los nuevos datos (título y descripción).
     *
     * @throws SQLException si ocurre un error al intentar actualizar el foro en la base de datos
     */
    @FXML
    private void actualizarForo() throws SQLException {
        String titulo = nombreForo.getText().trim();  // Obtiene el título introducido
        String descripcion = descripcionActualizar.getText().trim(); // Obtiene la descripción introducida

        if (titulo.isEmpty() || descripcion.isEmpty()) {
            mensajeLabel.setText("Todos los campos son obligatorios.");
            return;
        }

        Usuario usuarioActual = SesionUsuario.getUsuario();

       if (usuarioActual == null) {
            mensajeLabel.setText("Error: No hay un usuario logueado.");
            return;
        }

        Foro foroSeleccionado = listaForos.getSelectionModel().getSelectedItem();

        if (foroSeleccionado == null) {
            mensajeLabel.setText("Debe seleccionar un foro para actualizar.");
            return;
        }

        Foro nuevoForo = new Foro();
        nuevoForo.setTitulo(titulo);
        nuevoForo.setDescripcion(descripcion);

        DAOForo daoForo = new DAOForo();

        boolean actualizado = daoForo.update(nuevoForo, foroSeleccionado, usuarioActual);

        if (actualizado) {
            mensajeLabel.setText("Foro actualizado con éxito.");

            Stage stage = (Stage) btnActualizarForo.getScene().getWindow();
            stage.close();
        } else {
            mensajeLabel.setText("No se pudo actualizar el foro.");
        }
    }
}
