package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

import java.sql.Date;
import java.util.List;

/**
 * La clase UsuarioCreador extiende la clase Usuario.
 * Representa un usuario de tipo "CREADOR" que tiene un número de foros creados.
 */
public class UsuarioCreador extends Usuario {

    private int Num_ForosCreados;
    private List<Foro> foros;


    /**
     * Constructor vacío de la clase UsuarioCreador.
     * Llama al constructor de la clase base Usuario.
     */
    public UsuarioCreador() {
        super();
    }

    /**
     * Constructor de la clase UsuarioCreador con parámetros.
     *
     * @param nombre El nombre del usuario.
     * @param apellidos Los apellidos del usuario.
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     */
    public UsuarioCreador(String nombre, String apellidos, String email, String password) {
        super(nombre, apellidos, email, password);
    }

    /**
     * Obtiene el número de foros creados por el usuario.
     *
     * @return El número de foros creados por el usuario.
     */
    public int getNum_ForosCreados() {
        return Num_ForosCreados;
    }

    /**
     * Incrementa el número de foros creados por el usuario.
     *
     * @return El nuevo número de foros creados después de la actualización.
     */
    public void setNum_ForosCreados() {
        Num_ForosCreados = Num_ForosCreados + 1;
    }

    /**
     * Devuelve el tipo de usuario como un String.
     *
     * @return El tipo de usuario, en este caso siempre devuelve "CREADOR".
     */
    @Override
    public String getTipoUsuario() {
        return "CREADOR";
    }

    /**
     * Devuelve una representación en cadena del objeto UsuarioCreador.
     *
     * @return Una cadena con el número de foros creados por el usuario.
     */
    @Override
    public String toString() {
        return "UsuarioCreador{" +
                "Num_ForosCreados=" + Num_ForosCreados +
                '}';
    }

    public List<Foro> getForos() {
        return foros;
    }
}
