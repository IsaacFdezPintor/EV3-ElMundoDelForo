package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;
import java.time.LocalDate;

public abstract class Contenido {
   private int id_contenido;
    private Date fecha = Date.valueOf(LocalDate.now());
    private int id_usuario;
    private int id_foro;

    public Contenido(int id_contenido, Date fecha, int id_usuario, int id_foro) {
        this.id_contenido = id_contenido;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        this.id_foro = id_foro;
    }
    public Contenido() {
    }
    public int getId_contenido() {
        return id_contenido;
    }
    public void setId_contenido(int id_contenido) {
        this.id_contenido = id_contenido;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public int getId_usuario() {
        return id_usuario;
    }
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }
    public int getId_foro() {
        return id_foro;
    }
    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }

    @Override
    public String toString() {
        return "Contenido [id_contenido=" + id_contenido + ", fecha=" + fecha + ", id_usuario=" + id_usuario
                + ", id_foro=" + id_foro + "]";
    }
}
