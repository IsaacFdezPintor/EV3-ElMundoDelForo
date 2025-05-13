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

    private Usuario usuarioActual;

    @FXML
    private void onActualizarUsuario(ActionEvent event) {
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmarPassword = confirmarPasswordField.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            mensajeLabel.setText("Nombre y Apellido son obligatorios.");
            return;
        }

        if (!password.isEmpty() && !password.equals(confirmarPassword)) {
            mensajeLabel.setText("Las contraseñas no coinciden.");
            return;
        }

        // Si no se quiere cambiar la contraseña, usamos la anterior
        String nuevaPassword = password.isEmpty() ? usuarioActual.getPassword() : password;

        try {
            if (usuarioActual instanceof UsuarioComun) {
                UsuarioComun actualizado = new UsuarioComun(nombre, apellido, usuarioActual.getEmail(), nuevaPassword);
                DAOUsuarioComun dao = new DAOUsuarioComun();
                boolean success = dao.update(actualizado, (UsuarioComun) usuarioActual);
                mensajeLabel.setText(success ? "Usuario común actualizado con éxito." : "Error al actualizar usuario común.");
            } else if (usuarioActual instanceof UsuarioCreador) {
                UsuarioCreador actualizado = new UsuarioCreador(nombre, apellido, usuarioActual.getEmail(), nuevaPassword);
                DAOUsuarioCreador dao = new DAOUsuarioCreador();
                boolean success = dao.update(actualizado, (UsuarioCreador) usuarioActual);
                mensajeLabel.setText(success ? "Usuario creador actualizado con éxito." : "Error al actualizar usuario creador.");
            } else {
                mensajeLabel.setText("Tipo de usuario no válido.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            mensajeLabel.setText("Error al actualizar el usuario.");
        }
    }

    public void initData(Usuario usuario) {
        this.usuarioActual = usuario;

        nombreField.setText(usuario.getNombre());
        apellidoField.setText(usuario.getApellidos());
        emailField.setText(usuario.getEmail());
        emailField.setEditable(false); // El email no debe cambiar
        passwordField.setText(""); // Campo vacío para evitar mostrar contraseña
        confirmarPasswordField.setText("");

        if (usuario instanceof UsuarioComun) {
            comboTipoUsuario.getSelectionModel().select("Común");
        } else if (usuario instanceof UsuarioCreador) {
            comboTipoUsuario.getSelectionModel().select("Creador");
        }
    }
}
