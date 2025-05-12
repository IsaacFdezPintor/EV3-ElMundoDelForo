package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

public class SesionUsuario {
    private static Usuario usuarioActual;

    public static void setUsuario(Usuario usuario) {
        usuarioActual = usuario;
    }

    public static Usuario getUsuario() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }
}

