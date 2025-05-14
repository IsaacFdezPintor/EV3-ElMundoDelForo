package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * La clase Texto representa un mensaje o contenido escrito en un foro.
 * Contiene los atributos y métodos necesarios para definir un texto en el contexto de un foro.
 */
public class Texto {

    private int id_contenido;
    private Date fecha = Date.valueOf(LocalDate.now());
    private int id_usuario;
    private int id_foro;
    private String texto;

    /**
     * Constructor que recibe todos los atributos y los inicializa.
     * Este constructor se utiliza cuando se desea crear un objeto Texto con todos los datos.
     *
     * @param id_contenido El identificador único del contenido.
     * @param fecha La fecha en la que se creó el contenido.
     * @param id_usuario El id del usuario que publicó el contenido.
     * @param id_foro El id del foro donde se publicó el contenido.
     */
    public Texto(int id_contenido, Date fecha, int id_usuario, int id_foro) {
        this.id_contenido = id_contenido;
        this.fecha = fecha;
        this.id_usuario = id_usuario;
        this.id_foro = id_foro;
    }

    /**
     * Constructor vacío. Este constructor se utiliza para crear un objeto Texto sin inicializar los atributos.
     */
    public Texto() {
    }

    /**
     * Obtiene el id del contenido (mensaje).
     *
     * @return El id del contenido.
     */
    public int getId_contenido() {
        return id_contenido;
    }

    /**
     * Establece el id del contenido (mensaje).
     *
     * @param id_contenido El id del contenido a establecer.
     */
    public void setId_contenido(int id_contenido) {
        this.id_contenido = id_contenido;
    }

    /**
     * Obtiene la fecha de creación del contenido.
     *
     * @return La fecha en que se creó el contenido.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de creación del contenido.
     *
     * @param fecha La fecha a establecer.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene el id del usuario que publicó el contenido.
     *
     * @return El id del usuario que publicó el contenido.
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el id del usuario que publicó el contenido.
     *
     * @param id_usuario El id del usuario que publicó el contenido.
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el id del foro en el que se publicó el contenido.
     *
     * @return El id del foro donde se publicó el contenido.
     */
    public int getId_foro() {
        return id_foro;
    }

    /**
     * Establece el id del foro en el que se publicó el contenido.
     *
     * @param id_foro El id del foro donde se publicó el contenido.
     */
    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }

    /**
     * Obtiene el texto (contenido) del mensaje.
     *
     * @return El texto del mensaje o contenido.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Establece el texto (contenido) del mensaje.
     *
     * @param texto El texto del mensaje a establecer.
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Sobrescribe el método toString para representar el objeto Texto como una cadena.
     * Este método devuelve el texto del mensaje, útil para mostrarlo de manera amigable.
     *
     * @return El texto del mensaje.
     */
    @Override
    public String toString() {
        return texto;
    }
}
