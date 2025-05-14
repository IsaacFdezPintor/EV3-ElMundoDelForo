package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils.Utils;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils.ViewUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
private Label mensajeLabel;
@FXML
private Button botonActualizar;


    @FXML
    private void ActualizarUsuario() throws SQLException {
        String nombre = nombreField.getText().trim();  // Obtiene el título introducido
        String apellidos = apellidoField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmarPassword = confirmarPasswordField.getText().trim();

        Usuario usuarioActual = SesionUsuario.getUsuario();


        if (usuarioActual == null) {
            mensajeLabel.setText("Error: No hay un usuario logueado.");
            return;
        }
        if (usuarioActual.getTipoUsuario().equals("CREADOR")) {
            UsuarioCreador creadornuevo = new UsuarioCreador();
            if (!nombre.isEmpty()) {
                creadornuevo.setNombre(nombre);
            } else {
                creadornuevo.setNombre(usuarioActual.getNombre());

            }
            if (!apellidos.isEmpty()) {
                creadornuevo.setApellidos(apellidos);
            } else {
                creadornuevo.setApellidos(usuarioActual.getApellidos());
            }
            if (!email.isEmpty()) {
                if(!Utils.EmailValido(email)) {
                    mensajeLabel.setText("El correo electrónico no es válido.");
                    return;
                }
                creadornuevo.setEmail(email);
            } else {
                creadornuevo.setEmail(usuarioActual.getEmail());
            }
            if (!password.isEmpty()) {
                if (password.equals(confirmarPassword)) {
                    creadornuevo.setPassword(password);
                } else {
                    mensajeLabel.setText("Las contraseñas no coinciden.");
                    return;
                }
            } else {
                creadornuevo.setPassword(usuarioActual.getPassword());
            }

            DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
            UsuarioCreador usuarioCreador = (UsuarioCreador) usuarioActual;

            boolean actualizado = daoUsuarioCreador.update(usuarioCreador, creadornuevo);
            System.out.println(actualizado);

            if (actualizado) {
                mensajeLabel.setText("Usuario actualizado con éxito.");

                Stage stage = (Stage) botonActualizar.getScene().getWindow();
                stage.close();
            } else {
                mensajeLabel.setText("No se pudo actualizar el Usuario.");
            }
        }
    }
}
