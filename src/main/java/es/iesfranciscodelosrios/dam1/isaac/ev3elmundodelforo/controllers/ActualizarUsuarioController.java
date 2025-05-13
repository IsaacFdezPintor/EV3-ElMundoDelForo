package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ActualizarUsuarioController {

    // FXML Components
    @FXML
    private TextField nombreField;
    @FXML
    private TextField apellidoField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmarPasswordField;
    @FXML
    private ComboBox<String> comboTipoUsuario;
    @FXML
    private Label mensajeLabel;

    private Usuario usuarioActual; // Usuario actual (el que está siendo actualizado)

    // Método que se llama cuando se presiona el botón de "Actualizar"
    @FXML
    private void onActualizarUsuario(ActionEvent event) {
        // Obtener los valores de los campos
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmarPassword = confirmarPasswordField.getText().trim();

        // Validar que todos los campos obligatorios están completos
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() ) {
            mensajeLabel.setText("Todos los campos son obligatorios.");
            return;
        }

        // Validar que las contraseñas coinciden
        if (!password.isEmpty() && !password.equals(confirmarPassword)) {
            mensajeLabel.setText("Las contraseñas no coinciden.");
            return;
        }

        // Lógica para actualizar el usuario según el tipo
        if (usuarioActual.getTipoUsuario().equals("Común")) {
            UsuarioComun usuarioNuevo = new UsuarioComun(nombre, apellido, email, password) ;
            // Llama al método para actualizar el usuario común
            DAOUsuarioComun daoUsuarioComun = new DAOUsuarioComun();
            try {
                boolean success = daoUsuarioComun.update(usuarioNuevo, (UsuarioComun) usuarioActual);
                if (success) {
                    mensajeLabel.setText("Usuario común actualizado con éxito.");
                } else {
                    mensajeLabel.setText("Error al actualizar el usuario común.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                mensajeLabel.setText("Error al actualizar el usuario.");
            }
        } else if (usuarioActual.getTipoUsuario().equals("Creador")) {
            UsuarioCreador usuarioNuevo = new UsuarioCreador(nombre, apellido, email, password);
            // Llama al método para actualizar el usuario creador
            DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
            try {
                boolean success = daoUsuarioCreador.update(usuarioNuevo, (UsuarioCreador) usuarioActual);
                if (success) {
                    mensajeLabel.setText("Usuario creador actualizado con éxito.");
                } else {
                    mensajeLabel.setText("Error al actualizar el usuario creador.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                mensajeLabel.setText("Error al actualizar el usuario.");
            }
        } else {
            mensajeLabel.setText("Tipo de usuario no válido.");
        }
    }

    // Método para inicializar el controlador con el usuario actual (debe ser llamado al cargar la vista)
    public void initData(Usuario usuario) {
        this.usuarioActual = usuario;

        // Completar los campos con los datos del usuario actual
        if (usuario instanceof UsuarioComun) {
            UsuarioComun usuarioComun = (UsuarioComun) usuario;
            nombreField.setText(usuarioComun.getNombre());
            apellidoField.setText(usuarioComun.getApellidos());
            emailField.setText(usuarioComun.getEmail());
            passwordField.setText("");  // Dejar vacío para que el usuario pueda cambiarla
            confirmarPasswordField.setText("");  // Dejar vacío para que el usuario pueda cambiarla
            comboTipoUsuario.getSelectionModel().select("Común");
        } else if (usuario instanceof UsuarioCreador) {
            UsuarioCreador usuarioCreador = (UsuarioCreador) usuario;
            nombreField.setText(usuarioCreador.getNombre());
            apellidoField.setText(usuarioCreador.getApellidos());
            emailField.setText(usuarioCreador.getEmail());
            passwordField.setText("");  // Dejar vacío para que el usuario pueda cambiarla
            confirmarPasswordField.setText("");  // Dejar vacío para que el usuario pueda cambiarla
            comboTipoUsuario.getSelectionModel().select("Creador");
        }

        // Deshabilitar el campo de email porque no se debe cambiar
        emailField.setEditable(false);
    }
}
