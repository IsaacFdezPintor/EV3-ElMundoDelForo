package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un foro en la aplicación.
 * Contiene los atributos y métodos necesarios para definir un foro,
 * como el título, la descripción, la fecha de creación y el id del creador.
 */
public class Foro {

    private int id_foro;
    private String titulo;
    private String descripcion;
    private Date fecha_creacion = Date.valueOf(LocalDate.now());
    private UsuarioCreador creador;
    private List<Texto> textos;
    /**
     * Constructor principal de la clase Foro.
     *
     * @param titulo        Título del foro.
     * @param descripcion   Descripción del foro.
     * @param fecha_creacion Fecha de creación del foro.
     * @param creador    Usuario  creador del foro.
     */
    public Foro(String titulo, String descripcion, Date fecha_creacion, UsuarioCreador creador) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.creador = creador;
        this.textos = new ArrayList<>();

    }

    /**
     * Constructor con id_foro.
     *
     * @param id_foro       Identificador único del foro.
     * @param titulo        Título del foro.
     * @param descripcion   Descripción del foro.
     * @param fecha_creacion Fecha de creación del foro.
     * @param creador    Usuario creador del foro.
     */
    public Foro(int id_foro, String titulo, String descripcion, Date fecha_creacion, UsuarioCreador creador) {
        this.id_foro = id_foro;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_creacion = fecha_creacion;
        this.creador = creador;
        this.textos = new ArrayList<>();

    }

    /**
     * Constructor vacío.
     * Se utiliza para crear un objeto de la clase Foro sin parámetros.
     */
    public Foro() {
    }


    /**
     * Obtiene el id del foro.
     *
     * @return El id del foro.
     */
    public int getId_foro() {
        return id_foro;
    }

    /**
     * Establece el id del foro.
     *
     * @param id_foro El id a establecer.
     */
    public void setId_foro(int id_foro) {
        this.id_foro = id_foro;
    }

    /**
     * Obtiene el título del foro.
     *
     * @return El título del foro.
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Establece el título del foro.
     *
     * @param titulo El título a establecer.
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Obtiene la descripción del foro.
     *
     * @return La descripción del foro.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del foro.
     *
     * @param descripcion La descripción a establecer.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la fecha de creación del foro.
     *
     * @return La fecha de creación del foro.
     */
    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    /**
     * Establece la fecha de creación del foro.
     *
     * @param fecha_creacion La fecha de creación a establecer.
     */
    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }


    /**
     * Método toString que devuelve una representación en cadena del foro.
     * En este caso, devuelve solo el título del foro.
     *
     * @return El título del foro como una cadena.
     */
    @Override
    public String toString() {
        return titulo;
    }

    public void agregarTexto(Texto texto) {
        textos.add(texto);
    }

    public List<Texto> getTextos() {
        return textos;
    }
    /**
     * Obtiene el creador del foro.
     *
     * @return El usuario creador del foro.
     */
    public UsuarioCreador getCreador() {
        return creador;
    }
    /**
     * Establece el creador del foro.
     *
     * @param creador El usuario creador a establecer.
     */
    public void setCreador(UsuarioCreador creador) {
        this.creador = creador;
    }
}
