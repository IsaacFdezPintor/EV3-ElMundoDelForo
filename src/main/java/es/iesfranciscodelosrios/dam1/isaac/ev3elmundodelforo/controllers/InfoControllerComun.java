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

/**
 * Controlador de la vista de información para usuarios comunes.
 * Permite visualizar información del usuario, su participación, y eliminar o actualizar sus datos.
 */
public class InfoControllerComun  {

    @FXML private Label labelNombre;
    @FXML private Label labelApellido;
    @FXML private Label labelCorreo;
    @FXML private Label labelTipoUsuario;
    @FXML private Button btnEliminar;
    @FXML private Label mensajeAlerta;
    @FXML private Label labelParticipacion;

    /**
     * Método que se ejecuta automáticamente al iniciar la vista.
     * Carga los datos del usuario común actual y los muestra.
     */
    public void initialize() {
        Usuario usuario = SesionUsuario.getUsuario();
        UsuarioComun usuarioComun = (UsuarioComun) usuario;
        DAOUsuarioComun daoUsuarioComun = new DAOUsuarioComun();

        if (usuario != null) {
            labelNombre.setText(usuario.getNombre());
            labelApellido.setText(usuario.getApellidos());
            labelCorreo.setText(usuario.getEmail());
            labelTipoUsuario.setText(usuario.getTipoUsuario());
            labelParticipacion.setText(daoUsuarioComun.obtenerParticipacion(usuario));
        } else {
            labelNombre.setText("Usuario no encontrado");
            labelApellido.setText("");
            labelCorreo.setText("");
            labelTipoUsuario.setText("");
        }
    }


    /**
     * Elimina el usuario actualmente logueado.
     * Si es exitoso, cierra la sesión y redirige al login.
     *
     * @param event Evento de acción generado al pulsar el botón eliminar
     */
    @FXML
    public void eliminarUsuario(ActionEvent event) {
        Usuario usuario = SesionUsuario.getUsuario();

        if (usuario == null) {
            mensajeAlerta.setText("No hay ningún usuario logeado.");
            return;
        }

        if (usuario.getTipoUsuario().equals("COMUN")) {
            DAOUsuarioComun dao = new DAOUsuarioComun();
            try {
                boolean eliminado = dao.delete((UsuarioComun) usuario);
                if (eliminado) {
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

    /**
     * Cierra la ventana actual y abre la pantalla de login.
     *
     * Este método se llama después de eliminar al usuario exitosamente.
     */
    private void cerrarVentanasYAbrirLogin() {
        try {
            Stage currentStage = (Stage) btnEliminar.getScene().getWindow();
            currentStage.close();  // Cierra la ventana actual
            ViewUtils.abrirNuevaVentanaFija("login.fxml", "Login");  // Abre la ventana de login
        } catch (IOException e) {
            e.printStackTrace();
            mensajeAlerta.setText("No se pudo abrir la ventana de login.");
        }
    }
}
