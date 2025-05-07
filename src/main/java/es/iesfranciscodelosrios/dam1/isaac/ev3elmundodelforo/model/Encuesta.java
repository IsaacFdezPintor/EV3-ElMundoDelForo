package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.util.List;

public class Encuesta extends Contenido{

    private int id_Contenido;
    private String pregunta;
    private List<String> respuestas;

    public Encuesta(String pregunta, List<String> respuestas) {
        this.pregunta = pregunta;
        this.respuestas = respuestas;
    }
    public Encuesta() {
    }
    public int getId_Contenido() {
        return id_Contenido;
    }
    public void setId_Contenido(int id_Contenido) {
        this.id_Contenido = id_Contenido;
    }
    public String getPregunta() {
        return pregunta;
    }
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    public List<String> getRespuestas() {
        return respuestas;
    }
    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }
    @Override
    public String toString() {
        return "Encuesta [id_Contenido=" + id_Contenido + ", pregunta=" + pregunta + ", respuestas=" + respuestas + "]";
    }
}
