package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOForo;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Foro;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CrearForoController {

    @FXML
    private TextField tituloField;

    @FXML
    private TextArea descripcionField;

    @FXML
    private Button btnCrearForo;

    @FXML
    private Label mensajeLabel;




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

