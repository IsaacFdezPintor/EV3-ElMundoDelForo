package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Controlador para la vista de inicio de sesión.
 * Este controlador gestiona la autenticación de usuarios comunes y creadores,
 * y la navegación hacia las respectivas vistas si el inicio de sesión es exitoso.
 */
public class LoginController {
    @FXML private TextField textEmail;
    @FXML private PasswordField textPassword;
    @FXML private Button login;
    @FXML private Button registrar;
    @FXML private Label errorMensaje;

    /**
     * Método llamado cuando el usuario hace clic en el botón de iniciar sesión.
     * Este método valida los campos, comprueba las credenciales del usuario,
     * y redirige a la vista correspondiente según el tipo de usuario.
     *
     * @throws SQLException Si hay un error al conectar o consultar la base de datos.
     * @throws RuntimeException Si ocurre un error de carga de vista (IOException).
     */
    @FXML
    private void onLogin() {
        String email = textEmail.getText().trim();
        String password = textPassword.getText().trim();

        DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
        DAOUsuarioComun daoUsuarioComun = new DAOUsuarioComun();

        if (email.isEmpty() || password.isEmpty()) {
            errorMensaje.setText("Ambos campos son obligatorios.");
        } else {
            try {
                Stage cerrarVentana = (Stage) login.getScene().getWindow();

                if (daoUsuarioCreador.check(email, password)) {
                    UsuarioCreador usuarioCreador = daoUsuarioCreador.findByCorreo(email);
                    SesionUsuario.setUsuario(usuarioCreador);
                    ViewUtils.abrirNuevaVentana("forocreador.fxml", "Mundo del Foro - Creador");
                    cerrarVentana.close();
                }

                else if (daoUsuarioComun.check(email, password)) {
                    UsuarioComun usuarioComun = daoUsuarioComun.findByCorreo(email);
                    SesionUsuario.setUsuario(usuarioComun);
                    ViewUtils.abrirNuevaVentana("forocomun.fxml", "Mundo del Foro - Usuario");
                    cerrarVentana.close();
                }

                else {
                    errorMensaje.setText("Credenciales incorrectas.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                errorMensaje.setText("Error al conectar con la base de datos.");
            } catch (IOException e) {
                throw new RuntimeException("No se pudo cargar la vista");
            }
        }
    }

    /**
     * Método que se ejecuta al hacer clic en el botón de "Registrar".
     * Abre la ventana del formulario de registro de usuario.
     *
     * @param actionEvent Evento de acción del botón.
     * @throws IOException Si no se puede cargar la vista de registro.
     */
    @FXML
    public void setRegistrar(ActionEvent actionEvent) throws IOException {
        ViewUtils.abrirNuevaVentanaFija("registrar.fxml", "Registrar Usuario");
    }
}
