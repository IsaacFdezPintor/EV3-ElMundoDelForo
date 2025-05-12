package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.HelloApplication;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
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
        String email = textEmail.getText();
        String password = textPassword.getText().trim();
        DAOUsuarioCreador daoUsuarioCreador = new DAOUsuarioCreador();
        DAOUsuarioComun daoUsuarioComun = new DAOUsuarioComun();

        if (email.isEmpty() || password.isEmpty()) {
            errorMensaje.setText("Ambos campos son obligatorios.");
        } else {
            try {
                if (daoUsuarioCreador.check(email, password)) {
                    UsuarioCreador usuarioCreador = daoUsuarioCreador.findByCorreo(email);
                    SesionUsuario.setUsuario(usuarioCreador);
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forocreador.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Mundo del Foro - Creador");
                        stage.show();

                        // Cerrar la ventana de login
                        Stage loginStage = (Stage) textEmail.getScene().getWindow();
                        loginStage.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                        errorMensaje.setText("Error al cargar la vista.");
                    }

                } else if (daoUsuarioComun.check(email, password)) {
                    UsuarioComun usuarioComun = daoUsuarioComun.findByCorreo(email);
                    SesionUsuario.setUsuario(usuarioComun);
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("forocomun.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.setTitle("Mundo del Foro - Usuario");
                        stage.show();

                        // Cerrar la ventana de login
                        Stage loginStage = (Stage) textEmail.getScene().getWindow();
                        loginStage.close();

                    } catch (IOException e) {
                        e.printStackTrace();
                        errorMensaje.setText("Error al cargar la vista.");
                    }
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
    public void setRegistrar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("registrar.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Registrar Usuario");
        stage.setResizable(false);
        stage.show();
    }

}

