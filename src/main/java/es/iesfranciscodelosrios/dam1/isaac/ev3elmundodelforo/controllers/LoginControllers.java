package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;

public class LoginControllers {

    @FXML
    private RadioButton radioCreador;

    @FXML
    private RadioButton radioComun;

    @FXML
    private ToggleGroup grupoTipoUsuario;

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
    private Button botonRegistrar;

    @FXML
    private Label mensajeLabel;

    @FXML
    private void onRegistrarUsuario() {
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmarPassword = confirmarPasswordField.getText();
        String tipoUsuario =  "";
        if (radioCreador.isSelected()) {
            tipoUsuario = "Creador";
        } else if (radioComun.isSelected()) {
            tipoUsuario = "Comun";
    }

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()
                || password.isEmpty() || confirmarPassword.isEmpty() || tipoUsuario.isEmpty()) {
            mensajeLabel.setText("Por favor, rellena todos los campos.");
            return;
        }

        if (!password.equals(confirmarPassword)) {
            mensajeLabel.setText("Las contraseñas no coinciden.");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            mensajeLabel.setText("Correo electrónico inválido.");
            return;
        }

        try {
            if (tipoUsuario.equals("Creador")) {
                DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
                if (daoUsuarioCreador.existsByEmail(email)) {
                    mensajeLabel.setText("Ya existe un usuario con ese correo.");

                }
                UsuarioCreador creador = new UsuarioCreador(nombre, apellido, email, password, Date.valueOf(LocalDate.now()));
                daoUsuarioCreador.insert(creador);
            } else {
                DAOUsuarioComun daoUsuarioComun = new DAOUsuarioComun();
                if (daoUsuarioComun.existsByEmail(email)) {
                    mensajeLabel.setText("Ya existe un usuario con ese correo.");
                }
                UsuarioComun comun = new UsuarioComun(nombre, apellido, email, password, Date.valueOf(LocalDate.now()));
                daoUsuarioComun.insert(comun);
            }

            mensajeLabel.setStyle("-fx-text-fill: green;");
            mensajeLabel.setText("Registro exitoso para " + nombre + " (" + tipoUsuario + ")");

        } catch (Exception e) {
            mensajeLabel.setText("Error al registrar el usuario: " + e.getMessage());
        }
    }
}
