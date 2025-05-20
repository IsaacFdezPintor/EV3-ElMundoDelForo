package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils.Utils;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Controlador para la vista de registro de usuarios.
 * Permite registrar tanto usuarios comunes como creadores,
 * validando los campos de entrada y almacenando los datos en la base de datos.
 */
public class RegistrarControllers {

    @FXML private ComboBox<String> comboTipoUsuario;
    @FXML private TextField nombreField;
    @FXML private TextField apellidoField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmarPasswordField;
    @FXML private Button botonRegistrar;
    @FXML private Label mensajeLabel;

    /**
     * Método invocado al hacer clic en el botón de registrar.
     * Realiza las validaciones necesarias y registra el usuario
     * si los datos son válidos y el correo no existe en la base de datos.
     *
     * @throws Exception si ocurre un error inesperado durante el proceso
     */
    @FXML
    private void onRegistrarUsuario() {
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmarPassword = confirmarPasswordField.getText();
        String tipoUsuario = comboTipoUsuario.getValue();

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty()
                || password.isEmpty() || confirmarPassword.isEmpty() || tipoUsuario == null) {
            mensajeLabel.setText("Por favor, rellena todos los campos.");
            return;
        }

        if (!password.equals(confirmarPassword)) {
            mensajeLabel.setText("Las contraseñas no coinciden.");
            return;
        }

        if (!Utils.EmailValido(email)) {
            mensajeLabel.setText("Correo electrónico inválido.");
            return;
        }

        if (!Utils.ContraseñaValida(password)) {
            mensajeLabel.setText("La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número.");
            return;
        }

        try {
            boolean existe;

            if (tipoUsuario.equalsIgnoreCase("Creador")) {
                DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
                existe = daoUsuarioCreador.existsByEmail(email);

                if (!existe) {
                    UsuarioCreador creador = new UsuarioCreador(nombre, apellido, email, password);
                    daoUsuarioCreador.insert(creador);

                        Stage currentStage = (Stage) botonRegistrar.getScene().getWindow();
                        currentStage.close();

                }
            } else if (tipoUsuario.equalsIgnoreCase("Común")) {
                DAOUsuarioComun daoUsuarioComun = new DAOUsuarioComun();
                existe = daoUsuarioComun.existsByEmail(email);

                if (!existe) {
                    UsuarioComun comun = new UsuarioComun(nombre, apellido, email, password);
                    daoUsuarioComun.insert(comun);
                    Stage currentStage = (Stage) botonRegistrar.getScene().getWindow();
                    currentStage.close();
                }
            } else {
                mensajeLabel.setText("Tipo de usuario no válido.");
                return;
            }

            if (existe) {
                mensajeLabel.setText("Ya existe un usuario con ese correo.");
            } else {
                mensajeLabel.setText("Registro exitoso para " + nombre + " (" + tipoUsuario + ")");

            }

        } catch (Exception e) {
            mensajeLabel.setText("Error al registrar el usuario: " + e.getMessage());
        }
    }

}
