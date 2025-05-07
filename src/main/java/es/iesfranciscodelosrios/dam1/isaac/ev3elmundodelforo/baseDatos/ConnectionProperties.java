package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
//
/**
 * Esta clase representa las propiedades de conexión para una base de datos.
 * Se utiliza para almacenar la información necesaria para establecer una conexión,
 * como el servidor, el puerto, la base de datos, el usuario y la contraseña.
 */
@XmlRootElement(name="connection")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConnectionProperties implements Serializable {
    private static final long serialVersionUID = 1L;
    private String server;
    private String port;
    private String dataBase;
    private String user;
    private String password;

    /**
     * Constructor vacío. Inicializa un objeto de ConnectionProperties sin valores.
     */
    public ConnectionProperties() {}

    /**
     * Constructor con parámetros. Inicializa un objeto de ConnectionProperties con los valores proporcionados.
     *
     * @param server El servidor de la base de datos.
     * @param port El puerto donde la base de datos está escuchando.
     * @param dataBase El nombre de la base de datos.
     * @param user El nombre de usuario para la conexión.
     * @param password La contraseña del usuario para la conexión.
     */
    public ConnectionProperties(String server, String port, String dataBase, String user, String password) {
        this.server = server;
        this.port = port;
        this.dataBase = dataBase;
        this.user = user;
        this.password = password;
    }

    /**
     * Obtiene el nombre de usuario utilizado para la conexión.
     *
     * @return El nombre de usuario.
     */
    public String getUser() {
        return user;
    }

    /**
     * Obtiene la contraseña del usuario utilizada para la conexión.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Genera y devuelve la URL de conexión a la base de datos en formato JDBC.
     * Esta URL es usada para conectar con la base de datos en un cliente o servidor.
     *
     * @return La URL de conexión JDBC a la base de datos.
     */
    public String getURL() {
        return "jdbc:mysql://" + server + ":" + port + "/" + dataBase;
    }
}
