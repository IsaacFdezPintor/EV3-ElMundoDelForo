package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;
import java.time.LocalDate;

/**
 * La clase abstracta `Usuario` representa la estructura básica de un usuario en la aplicación.
 * Contiene atributos comunes como nombre, apellidos, email, contraseña, y la fecha de registro.
 * Esta clase será extendida por otras clases como `UsuarioComun` y `UsuarioCreador`.
 */
public abstract class Usuario {

    private int id_Usuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private Date fechaDeRegistro = Date.valueOf(LocalDate.now());
    private String tipoUsuario;

    /**
     * Constructor de la clase Usuario que recibe nombre, apellidos, email y contraseña.
     *
     * @param nombre Nombre del usuario.
     * @param apellidos Apellidos del usuario.
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     */
    public Usuario(String nombre, String apellidos, String email, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

    /**
     * Constructor vacío de la clase Usuario. Se usa para inicializar un objeto de tipo Usuario
     * sin establecer valores en el constructor.
     */
    public Usuario() {
    }

    /**
     * Obtiene el ID del usuario.
     *
     * @return El ID del usuario.
     */
    public int getId_Usuario() {
        return id_Usuario;
    }

    /**
     * Establece el ID del usuario.
     *
     * @param id_Usuario El ID del usuario a establecer.
     */
    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return El nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre El nombre del usuario a establecer.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene los apellidos del usuario.
     *
     * @return Los apellidos del usuario.
     */
    public String getApellidos() {
        return apellidos;
    }

    /**
     * Establece los apellidos del usuario.
     *
     * @param apellidos Los apellidos del usuario a establecer.
     */
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param email El correo electrónico del usuario a establecer.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     *
     * @param password La contraseña del usuario a establecer.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la fecha de registro del usuario.
     *
     * @return La fecha de registro del usuario.
     */
    public Date getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    /**
     * Establece la fecha de registro del usuario.
     *
     * @param fechaDeRegistro La fecha de registro del usuario a establecer.
     */
    public void setFechaDeRegistro(Date fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }

    /**
     * Obtiene el tipo de usuario (por ejemplo, "COMUN", "CREADOR").
     *
     * @return El tipo de usuario.
     */
    public String getTipoUsuario() {
        return tipoUsuario;
    }

    /**
     * Establece el tipo de usuario.
     *
     * @param tipoUsuario El tipo de usuario a establecer.
     */
    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    /**
     * Devuelve una representación en cadena de este objeto Usuario.
     *
     * @return Una cadena con los detalles del usuario, como su id, nombre, apellidos, email y fecha de registro.
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "id_Usuario=" + id_Usuario +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fechaDeRegistro=" + fechaDeRegistro +
                '}';
    }
}
