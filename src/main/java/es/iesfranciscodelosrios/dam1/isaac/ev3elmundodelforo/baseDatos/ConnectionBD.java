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

    private static final String FILE = "connection.xml"; // Archivo de configuración XML
    private static Connection con;                       // Conexión JDBC
    private static ConnectionBD _instance;               // Instancia Singleton

    /**
     * Constructor privado que inicializa la conexión a la base de datos.
     * Utiliza los datos obtenidos desde el archivo XML para conectarse.
     *
     * No tiene parámetros, ya que la configuración se obtiene internamente del XML.
     */
    private ConnectionBD() {
        ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);
        properties.getURL();
        properties.getUser();
        properties.getPassword();

        try {
            con = DriverManager.getConnection(
                    properties.getURL(),
                    properties.getUser(),
                    properties.getPassword()
            );
        } catch (SQLException e) {
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
            // Si no hay instancia o la conexión está cerrada, se crea una nueva
            if (_instance == null || con == null || con.isClosed()) {
                _instance = new ConnectionBD();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return con;
    }
}
