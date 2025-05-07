package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;

public class Texto extends Contenido {

    private String texto;

    public Texto(String texto, int id_contenido, Date fecha, int id_usuario, int id_foro) {
        super(id_contenido, fecha, id_usuario, id_foro);
        this.texto = texto;
    }
    public Texto() {
    }
    public String getTexto() {
        return texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    @Override

    public String toString() {
        return "Texto{" +
                "texto='" + texto + '\'' +
                ", id_contenido=" + getId_contenido() +
                ", fecha=" + getFecha() +
                ", id_usuario=" + getId_usuario() +
                ", id_foro=" + getId_foro() +
                '}';
    }
}
