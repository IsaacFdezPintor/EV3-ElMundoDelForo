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
import java.util.ResourceBundle;

/**
 * Controlador de la vista de información del usuario.
 * Muestra información del usuario actual y permite su eliminación.
 */
public class InfoController  {

    @FXML private Label labelNombre;
    @FXML private Label labelApellido;
    @FXML private Label labelCorreo;
    @FXML private Label labelTipoUsuario;
    @FXML private Button btnEliminar;
    @FXML private Label mensajeAlerta;

    /**
     * Método que se ejecuta automáticamente al inicializar la vista.
     *
     * Este método recupera el usuario en sesión y muestra sus datos en los labels correspondientes.
     */
    public void initialize() {
        Usuario usuario = SesionUsuario.getUsuario();

        if (usuario != null) {
            labelNombre.setText(usuario.getNombre());
            labelApellido.setText(usuario.getApellidos());
            labelCorreo.setText(usuario.getEmail());
            labelTipoUsuario.setText(usuario.getTipoUsuario());
        } else {
            labelNombre.setText("Usuario no encontrado");
            labelApellido.setText("");
            labelCorreo.setText("");
            labelTipoUsuario.setText("");
        }
    }

    /**
     * Elimina el usuario actualmente logueado dependiendo de su tipo.
     *
     * @param event Evento de acción generado por el botón
     *
     * Este método identifica si el usuario es COMUN o CREADOR, y llama al DAO correspondiente para eliminarlo.
     * En caso de éxito, cierra sesión y abre la vista de login. En caso de error, muestra mensaje en pantalla.
     */
    @FXML
    public void eliminarUsuario(ActionEvent event) {
        Usuario usuario = SesionUsuario.getUsuario();

        if (usuario == null) {
            mensajeAlerta.setText("No hay ningún usuario logeado.");
            return;
        }

        if (usuario.getTipoUsuario().equals("CREADOR")) {
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
     * Cierra la ventana actual y abre la ventana de login.
     *
     * Este método no recibe parámetros ni devuelve valor.
     * Muestra un mensaje de error si no puede abrir la vista de login.
     */
    private void cerrarVentanasYAbrirLogin() {
        try {
            Stage currentStage = (Stage) btnEliminar.getScene().getWindow();
            currentStage.close(); // Cierra la ventana actual
            ViewUtils.abrirNuevaVentanaFija("login.fxml", "Login");
        } catch (IOException e) {
            e.printStackTrace();
            mensajeAlerta.setText("No se pudo abrir la ventana de login.");
        }
    }
}
