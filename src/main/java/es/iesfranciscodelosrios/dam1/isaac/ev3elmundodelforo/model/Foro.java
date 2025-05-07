package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;

public class Foro {

    private int id_foro;
    private String titulo;
    private String descripcion;
    private Date fecha_creacion;
    private int id_creador;

    public Foro(String titulo, String descripcion, Date fecha_creacion, int id_creador) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.id_creador = id_creador;
    }

    public Foro() {
    }

    public int getId_foro() {
        return id_foro;
    }

    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public Date getFecha_creacion() {
        return fecha_creacion;
    }
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    public int getId_creador() {
        return id_creador;
    }
    public void setId_creador(int id_creador) {
        this.id_creador = id_creador;
    }
    @Override

    public String toString() {
        return "Foro{" +
                "id_foro=" + id_foro +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                ", id_creador=" + id_creador +
                '}';
    }
}
