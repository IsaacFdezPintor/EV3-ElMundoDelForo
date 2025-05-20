# Carpeta `baseDatos` - Conexión con la Base de Datos

Este directorio contiene las clases relacionadas con la gestión de la base de datos . Estas clases se encargan de gestionar la conexión con la base de datos utilizando el patrón **Singleton**, leer y escribir configuraciones desde un archivo XML La base de datos utilizada es MySQL.

## Clases y Métodos

### 1. **`ConnectionBD.java`**
Clase Singleton que gestiona una única conexión global a la base de datos.  La configuración de la conexión se extrae desde un archivo XML (connection.xml) usando la clase XMLManager.
- **Método: ` ConnectionBD() `**
Constructor privado que inicializa la conexión a la base de datos. Utiliza los datos obtenidos desde el archivo XML para conectarse.
- **Método: `getConnection() `**  
Método público y estático para obtener la conexión a la base de datos. Garantiza que se utilice una única instancia de la clase para mantener una única conexión activa (patrón Singleton).

### 2. **`ConnectionProperties.java`**
Esta clase representa los datos necesarios para conectarse a una base de datos. Es utilizada para leer o escribir esta información desde/hacia un archivo XML usando JAXB .
- **Método: `ConnectionProperties()`**
Constructor vacío requerido por JAXB para deserializar desde XML. Este constructor vacío es necesario para que JAXB pueda crear una instancia de la clase.
- **Método: `ConnectionProperties(String server, String port, String dataBase, String user, String password)`**
Constructor con parámetros para inicializar manualmente una instancia.
- **Método: `getUser()`**
Devuelve el nombre de usuario para la conexión.
- **Método: `getPassword()`**
Devuelve la contraseña para la conexión.
- **Método: `getURL()`**
Construye y devuelve la URL JDBC para conectarse a la base de datos. La URL tiene el formato: jdbc:mysql://[servidor]:[puerto]/[baseDeDatos]

### 3. **`XMLManager.java`**
Clase utilizada para trabajar con archivos XML mediante JAXB.

- **Método: `readXML(String filePath)`**  
  Lee un archivo XML y convierte su contenido en un objeto Java. 
- **Método: `writeXML(Object obj, String filePath)`**  
Escribe un objeto genérico a un archivo XML. Usa JAXB para convertir el objeto a XML y lo guarda en el archivo especificado.
  