package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Esta clase proporciona una conexión única y global a la base de datos.
 * Utiliza el patrón Singleton para asegurar que solo se cree una instancia de la conexión.
 * La configuración de la conexión se obtiene desde un archivo XML (connection.xml).
 */
public class ConnectionBD {
    private static final String FILE = "connection.xml";
    private static Connection con;
    private static ConnectionBD _instance;

    /**
     * Constructor privado para inicializar la conexión con la base de datos.
     * El constructor se lee desde un archivo XML y se establece la conexión utilizando las propiedades obtenidas.
     */
    private ConnectionBD() {
        ConnectionProperties properties = XMLManager.readXML(new ConnectionProperties(), FILE);
        System.out.println(properties.getURL());
        System.out.println(properties.getUser());
        System.out.println(properties.getPassword());
        try {
            con = DriverManager.getConnection(properties.getURL(), properties.getUser(), properties.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            con = null;
        }
    }

    /**
     * Obtiene la instancia única de la clase ConnectionBD.
     * Si la instancia aún no ha sido creada, se crea y se establece la conexión.
     *
     * @return La conexión a la base de datos. Si no se puede establecer la conexión, retorna null.
     */
    public static Connection getConnection() {
        if (_instance == null) {
            _instance = new ConnectionBD();
        }
        return con;
    }
}

