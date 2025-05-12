package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class InfoController  {

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelCorreo;

    @FXML
    private Label labelTipoUsuario;

    public void initialize() {

        Usuario usuario = SesionUsuario.getUsuario();

        if (usuario != null) {
            labelNombre.setText(usuario.getNombre());
            labelApellido.setText(usuario.getApellidos());
            labelCorreo.setText(usuario.getEmail());
        } else {
            labelNombre.setText("Usuario no encontrado");
        }
    }
}

