package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;
import java.util.List;

public class UsuarioComun extends Usuario{
    private List<Contenido> Contenido;

    public UsuarioComun() {
        super();
    }

    public UsuarioComun(String nombre, String apellidos, String email, String password, Date fechaDeRegistro) {
        super(nombre, apellidos, email, password, fechaDeRegistro);
    }

    public List<Contenido> getContenido() {
        return Contenido;
    }

    public void setContenido(List<Contenido> contenido) {
        Contenido = contenido;
    }

    @Override
    public String toString() {
        return "UsuarioComun{" +
                "Contenido=" + Contenido +
                '}';
    }
}
