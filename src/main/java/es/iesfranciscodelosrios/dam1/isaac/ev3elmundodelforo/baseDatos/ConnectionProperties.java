package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Esta clase representa los datos necesarios para conectarse a una base de datos.
 * Es utilizada para leer o escribir esta información desde/hacia un archivo XML
 * usando JAXB .
 */
@XmlRootElement(name = "connection") // Define el nombre del elemento raíz del XML como "connection"
@XmlAccessorType(XmlAccessType.FIELD) // Indica que se accederá a los campos directamente, sin necesidad de métodos getter/setter
public class ConnectionProperties implements Serializable {

    private static final long serialVersionUID = 1L; // Versión de serialización, para asegurar la compatibilidad entre versiones

    // Atributos que se asignarán automáticamente desde el XML
    private String server;     // Dirección del servidor de base de datos
    private String port;       // Puerto del servidor
    private String dataBase;   // Nombre de la base de datos
    private String user;       // Usuario para autenticación
    private String password;   // Contraseña del usuario

    /**
     * Constructor vacío requerido por JAXB para deserializar desde XML.
     * Este constructor vacío es necesario para que JAXB pueda crear una instancia de la clase.
     */
    public ConnectionProperties() {}

    /**
     * Constructor con parámetros para inicializar manualmente una instancia.
     *
     * @param server   Dirección IP o nombre del servidor de base de datos.
     * @param port     Puerto donde está escuchando el servidor.
     * @param dataBase Nombre de la base de datos a la que se desea conectar.
     * @param user     Nombre del usuario para autenticarse.
     * @param password Contraseña correspondiente al usuario.
     */
    public ConnectionProperties(String server, String port, String dataBase, String user, String password) {
        this.server = server;
        this.port = port;
        this.dataBase = dataBase;
        this.user = user;
        this.password = password;
    }

    /**
     * Devuelve el nombre de usuario para la conexión.
     *
     * @return El usuario de la base de datos.
     */
    public String getUser() {
        return user;
    }

    /**
     * Devuelve la contraseña para la conexión.
     *
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Construye y devuelve la URL JDBC para conectarse a la base de datos.
     * La URL tiene el formato: jdbc:mysql://[servidor]:[puerto]/[baseDeDatos]
     *
     * @return La cadena de conexión JDBC generada.
     */
    public String getURL() {
        return "jdbc:mysql://" + server + ":" + port + "/" + dataBase;
    }
}
