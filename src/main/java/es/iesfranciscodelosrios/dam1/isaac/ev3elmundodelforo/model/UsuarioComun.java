package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.util.List;

// La clase UsuarioComun extiende la clase Usuario.
// Representa un usuario de tipo "COMUN" que tiene un número de comentarios y un nivel de participación.
public class UsuarioComun extends Usuario {

    private static int num_Comentarios = 0;

    private Participacion nivel_Participacion;

    private List<Texto> textos;

    /**
     * Constructor vacío de la clase UsuarioComun.
     * Llama al constructor de la clase base Usuario.
     */
    public UsuarioComun() {
        super();
    }

    /**
     * Constructor de la clase UsuarioComun con parámetros.
     *
     * @param nombre El nombre del usuario.
     * @param apellidos Los apellidos del usuario.
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     */
    public UsuarioComun(String nombre, String apellidos, String email, String password) {
        super(nombre, apellidos, email, password);
    }

    /**
     * Obtiene el número de comentarios realizados por el usuario.
     *
     * @return El número de comentarios realizados por el usuario.
     */
    public int getNum_Comentarios() {
        return num_Comentarios;
    }

    /**
     * Obtiene el nivel de participación del usuario.
     *
     * @return El nivel de participación del usuario como un valor de tipo Participacion.
     */
    public Participacion getNivel_Participacion() {
        return nivel_Participacion;
    }

    /**
     * Incrementa el número de comentarios realizados por el usuario.
     *
     * @return El nuevo número de comentarios después de la actualización.
     */
    public void setNum_Comentarios(int num_Comentarios) {
        this.num_Comentarios = num_Comentarios;
    }

    public void incrementarNum_Comentarios() {
        this.num_Comentarios++;
    }


    /**
     * Devuelve el tipo de usuario como un String.
     *
     * @return El tipo de usuario, en este caso siempre devuelve "COMUN".
     */
    @Override
    public String getTipoUsuario() {
        return "COMUN";
    }

    /**
     * Devuelve una representación en cadena del objeto UsuarioComun.
     *
     * @return Una cadena que representa al usuario, mostrando su nombre.
     */
    @Override
    public String toString() {
        return "UsuarioComun{" +
                "Contenido=" + getNombre() +
                '}';
    }

    public List<Texto> getTextos() {
        return textos;
    }
}
