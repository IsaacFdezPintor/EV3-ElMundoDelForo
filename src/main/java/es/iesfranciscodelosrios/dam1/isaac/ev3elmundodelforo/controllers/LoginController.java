package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {

    @FXML
    private TextField textEmail;  // Campo de texto para el correo electrónico

    @FXML
    private PasswordField textPassword;  // Campo de texto para la contraseña

    @FXML
    private Button login;  // Botón de inicio de sesión

    @FXML
    private Button registrar;  // Botón de registro

    @FXML
    private Label errorMensaje;  // Etiqueta para mostrar errores



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

                if (daoUsuarioCreador.check(email, password)) {
                    errorMensaje.setText("Iniciando sesion... con Ususario Creador");
                } if (daoUsuarioComun.check(email, password)) {
                    errorMensaje.setText("Credenciales incorrectas con Usuario Comun");
                } else {
                    errorMensaje.setText("Credenciales incorrectas.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                errorMensaje.setText("Error al conectar con la base de datos.");
            }
        }
    }

    @FXML
    private void setRegistrar(ActionEvent event) throws IOException {
        // Cargar la vista del formulario de registro
        AnchorPane registrarLayout = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("registrar.fxml")));

        // Crear una nueva escena con el nuevo layout
        Scene registrarScene = new Scene(registrarLayout);

        // Obtener el Stage actual (la ventana)
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Cambiar la escena a la del registro
        currentStage.setScene(registrarScene);
    }

}

