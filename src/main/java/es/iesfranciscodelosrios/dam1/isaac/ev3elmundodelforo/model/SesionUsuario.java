package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

// La clase SesionUsuario gestiona la sesión del usuario actual en la aplicación.
// Se utiliza para almacenar el usuario que está actualmente logueado.
public class SesionUsuario {

    private static Usuario usuarioActual;

    /**
     * Establece el usuario actual.
     * Este método se utiliza para asignar un objeto Usuario a la variable estática
     * `usuarioActual`, que representa el usuario que está logueado en la sesión.
     *
     * @param usuario El usuario que se va a establecer como el usuario actual.
     */
    public static void setUsuario(Usuario usuario) {
        usuarioActual = usuario;
    }

    /**
     * Obtiene el usuario actual.
     * Este método devuelve el objeto `Usuario` que está almacenado en la variable
     * estática `usuarioActual`, que representa el usuario logueado en la sesión.
     *
     * @return El usuario actualmente logueado, o null si no hay usuario logueado.
     */
    public static Usuario getUsuario() {
        return usuarioActual;
    }

    /**
     * Cierra la sesión del usuario actual.
     * Este método elimina el usuario que está actualmente logueado, lo que se
     * logra asignando null a la variable estática `usuarioActual`. Esto indica
     * que no hay ningún usuario logueado en la aplicación.
     */
    public static void cerrarSesion() {
        usuarioActual = null;
    }
}
