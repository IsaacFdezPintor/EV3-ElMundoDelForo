package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

/**
 * Controlador de la pantalla para crear un nuevo foro.
 */
public class CrearForoController {

    @FXML private TextField tituloField;
    @FXML private TextArea descripcionField;
    @FXML private Button btnCrearForo;
    @FXML private Label mensajeLabel;
    /**
     * Método llamado cuando el usuario hace clic en el botón para crear un foro.
     * Este método verifica que los campos no estén vacíos, crea el foro en la base de datos
     * y cierra la ventana de creación del foro.
     *
     * @throws SQLException si ocurre un error al interactuar con la base de datos
     */
    @FXML
    private void crearForo() throws SQLException {
        String titulo = tituloField.getText().trim();
        String descripcion = descripcionField.getText().trim();

        if (titulo.isEmpty() || descripcion.isEmpty()) {
            mensajeLabel.setText("Todos los campos son obligatorios.");
            return;
        }

        Usuario usuarioActual = SesionUsuario.getUsuario();

        if (usuarioActual == null) {
            mensajeLabel.setText("Error: No hay un usuario logueado.");
            return;
        }
        System.out.println(usuarioActual.getTipoUsuario());

            UsuarioCreador creador = (UsuarioCreador) usuarioActual;
            creador.setNum_ForosCreados();
            DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
            daoUsuarioCreador.updateNumForos(creador);


        Foro nuevoForo = new Foro();
        nuevoForo.setTitulo(titulo);
        nuevoForo.setDescripcion(descripcion);
        nuevoForo.setId_creador(usuarioActual.getId_Usuario());

        DAOForo daoForo = new DAOForo();
        daoForo.insert(nuevoForo , usuarioActual);


        Stage stage = (Stage) btnCrearForo.getScene().getWindow();
        stage.close();
    }
}
