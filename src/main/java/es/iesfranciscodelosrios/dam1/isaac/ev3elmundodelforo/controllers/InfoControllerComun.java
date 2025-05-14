package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.controllers;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.DAO.DAOUsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.SesionUsuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.Usuario;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioComun;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model.UsuarioCreador;
import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils.ViewUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class InfoControllerComun implements Initializable {

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelApellido;

    @FXML
    private Label labelCorreo;


    @FXML
    private Label labelTipoUsuario;

    @FXML
    private Button btnEliminar;

    @FXML
    private Label mensajeAlerta;

    @FXML
    private Label labelParticipacion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Comprobamos si el usuario está logueado
        Usuario usuario = SesionUsuario.getUsuario();
        UsuarioComun usuarioComun = (UsuarioComun) usuario;
        DAOUsuarioComun daoUsuarioComun = new DAOUsuarioComun();

        if (usuario != null) {
            labelNombre.setText(usuario.getNombre());
            labelApellido.setText(usuario.getApellidos());
            labelCorreo.setText(usuario.getEmail());
            labelTipoUsuario.setText(usuario.getTipoUsuario());  // Mostrar el tipo de usuario (COMUN/CREADOR)
            labelParticipacion.setText (daoUsuarioComun.obtenerParticipacion(usuario));
        } else {
            labelNombre.setText("Usuario no encontrado");
            labelApellido.setText("");
            labelCorreo.setText("");
            labelTipoUsuario.setText("");
        }
    }

    @FXML
    public void actualizarUsuario(ActionEvent actionEvent) throws IOException {
        // Abrir la ventana para actualizar el usuario
        ViewUtils.abrirNuevaVentanaFija("actualizarUsuario.fxml", "Actualizar Usuario");
    }

    @FXML
    public void eliminarUsuario(ActionEvent event) {
        // Obtener el usuario de la sesión
        Usuario usuario = SesionUsuario.getUsuario();

        // Verificar si el usuario está en sesión
        if (usuario == null) {
            mensajeAlerta.setText("No hay ningún usuario logeado.");
            return;
        }

        // Dependiendo del tipo de usuario, realizamos la eliminación
        if (usuario.getTipoUsuario().equals("COMUN")) {
            DAOUsuarioComun dao = new DAOUsuarioComun();
            try {
                boolean eliminado = dao.delete((UsuarioComun) usuario);
                if (eliminado) {
                    // Si el usuario fue eliminado, cerramos sesión y redirigimos al login
                    SesionUsuario.cerrarSesion();
                    cerrarVentanasYAbrirLogin();
                } else {
                    mensajeAlerta.setText("No se pudo eliminar el usuario.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                mensajeAlerta.setText("Error al intentar eliminar el usuario.");
            }
        } else if (usuario.getTipoUsuario().equals("CREADOR")) {
            DAOUsuarioCreador dao = new DAOUsuarioCreador();
            try {
                boolean eliminado = dao.delete((UsuarioCreador) usuario);
                if (eliminado) {
                    // Si el usuario fue eliminado, cerramos sesión y redirigimos al login
                    SesionUsuario.cerrarSesion();
                    cerrarVentanasYAbrirLogin();
                } else {
                    mensajeAlerta.setText("No se pudo eliminar el usuario creador.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                mensajeAlerta.setText("Error al intentar eliminar el usuario creador.");
            }
        } else {
            mensajeAlerta.setText("Tipo de usuario desconocido.");
        }
    }

    private void cerrarVentanasYAbrirLogin() {
        try {
            // Cerrar todas las ventanas (excepto la ventana de login)
            Stage currentStage = (Stage) btnEliminar.getScene().getWindow();
            currentStage.close();
            currentStage.close();
            ViewUtils.abrirNuevaVentanaFija("login.fxml", "Login");
        } catch (IOException e) {
            e.printStackTrace();
            mensajeAlerta.setText("No se pudo abrir la ventana de login.");
        }
    }
}
