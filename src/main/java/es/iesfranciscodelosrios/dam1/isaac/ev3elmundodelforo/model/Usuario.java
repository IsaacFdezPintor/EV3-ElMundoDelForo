package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;

public  abstract class Usuario {

    private int id_Usuario;
    private String nombre;
    private String apellidos;
    private String email;
    private String password;
    private Date fechaDeRegistro;

    public Usuario(String nombre, String apellidos, String email, String password, Date fechaDeRegistro) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
        this.fechaDeRegistro = fechaDeRegistro;
    }
    public Usuario() {
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaDeRegistro() {
        return fechaDeRegistro;
    }

    public void setFechaDeRegistro(Date fechaDeRegistro) {
        this.fechaDeRegistro = fechaDeRegistro;
    }


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
