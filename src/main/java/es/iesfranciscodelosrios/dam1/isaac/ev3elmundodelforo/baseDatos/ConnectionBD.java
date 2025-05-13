package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase Singleton que gestiona una única conexión global a la base de datos.
 * La configuración de la conexión se extrae desde un archivo XML (connection.xml)
 * usando la clase XMLManager.
 */
public class ConnectionBD {

    private static final String FILE = "connection.xml"; // Archivo de configuración XML que contiene los datos de conexión
    private static Connection con;                       // Instancia de la conexión a la base de datos
    private static ConnectionBD _instance;               // Instancia de la clase ConnectionBD (Singleton)

    /**
     * Constructor privado que inicializa la conexión a la base de datos.
     * Utiliza los datos obtenidos desde el archivo XML para conectarse.
     *
     * No tiene parámetros, ya que la configuración se obtiene internamente del archivo XML.
     */
    private ConnectionBD() {
        // Lee los datos de configuración (URL, usuario, y contraseña) desde el archivo XML
        ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);

        // Obtiene las propiedades de conexión del archivo XML
        properties.getURL();
        properties.getUser();
        properties.getPassword();

        try {
            // Intenta establecer la conexión a la base de datos usando los valores obtenidos del XML
            con = DriverManager.getConnection(
                    properties.getURL(),
                    properties.getUser(),
                    properties.getPassword()
            );
        } catch (SQLException e) {
            // En caso de error, imprime el stack trace y establece la conexión como nula
            e.printStackTrace();
            con = null;
        }
    }

    /**
     * Método público y estático para obtener la conexión a la base de datos.
     * Garantiza que se utilice una única instancia de la clase para mantener
     * una única conexión activa (patrón Singleton).
     *
     * @return Un objeto {@link Connection} si la conexión se establece correctamente.
     *         Devuelve {@code null} si ocurre un error o no se puede establecer la conexión.
     */
    public static Connection getConnection() {
        try {
            // Verifica si la instancia de ConnectionBD no existe o si la conexión está cerrada
            if (_instance == null || con == null || con.isClosed()) {
                // Si la conexión no está activa, crea una nueva instancia de ConnectionBD
                _instance = new ConnectionBD();
            }
        } catch (SQLException e) {
            // En caso de error, imprime el stack trace y devuelve null
            e.printStackTrace();
            return null;
        }

        // Devuelve la conexión activa
        return con;
    }
}
