package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;

public class ComunForo {

    private int id_Usuario;
    private int id_Foro;
    private Date fecha_union;

    public ComunForo(int id_Usuario, int id_Foro, Date fecha_union) {
        this.id_Usuario = id_Usuario;
        this.id_Foro = id_Foro;
        this.fecha_union = fecha_union;
    }
    public ComunForo() {
    }
    public int getId_Usuario() {
        return id_Usuario;
    }
    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }
    public int getId_Foro() {
        return id_Foro;
    }
    public void setId_Foro(int id_Foro) {
        this.id_Foro = id_Foro;
    }
    public Date getFecha_union() {
        return fecha_union;
    }
    public void setFecha_union(Date fecha_union) {
        this.fecha_union = fecha_union;
    }
    @Override
    public String toString() {
        return "ComunForo [id_Usuario=" + id_Usuario + ", id_Foro=" + id_Foro + ", fecha_union=" + fecha_union + "]";
    }
}
