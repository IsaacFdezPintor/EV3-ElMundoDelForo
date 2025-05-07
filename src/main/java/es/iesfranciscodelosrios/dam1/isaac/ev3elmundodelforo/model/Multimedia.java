package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;

public class Multimedia extends Contenido{

    private String URL;
    private String Tamano;
    private String Descripcion;

    public Multimedia(int id_contenido, String URL, String Tamano, String Descripcion, Date fecha, int id_usuario, int id_foro) {
        super(id_contenido,fecha, id_usuario, id_foro);
        this.URL = URL;
        this.Tamano = Tamano;
        this.Descripcion = Descripcion;
    }
    public Multimedia() {
    }
    public String getURL() {
        return URL;
    }
    public void setURL(String URL) {
        this.URL = URL;
    }
    public String getTamano() {
        return Tamano;
    }
    public void setTamano(String Tamano) {
        this.Tamano = Tamano;
    }
    public String getDescripcion() {
        return Descripcion;
    }
    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    @Override
    public String toString() {
        return "Multimedia [URL=" + URL + ", Tamano=" + Tamano + ", Descripcion=" + Descripcion + "]";
    }
}
