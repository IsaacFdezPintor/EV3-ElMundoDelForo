# Carpeta `model` - Modelo de Clases

La carpeta `model` contiene clases y métodos que representan el modelo de datos de la aplicación. Estas clases son responsables de definir la estructura de los datos y las operaciones que se pueden realizar sobre ellos.

## Clases y Métodos

### 1. **`Foro`**
La clase `Foro` representa un foro en la aplicación. Contiene los atributos y métodos necesarios para definir un foro, como el título, la descripción, la fecha de creación y el id del creador.

- **Constructor sin IDForo: `Foro(String titulo, String descripcion, Date fecha_creacion, int id_creador)`**  
  Constructor que recibe el título, la descripción, la fecha de creación y el id del creador para inicializar un foro.

- **Constructor vacío: `Foro()`**  
  Constructor vacío, utilizado para crear un objeto `Foro` sin inicializar ningún atributo.

- **Constructor completo: `Foro(int id_foro, String titulo, String descripcion, Date fecha_creacion, int id_creador)`**  
  Constructor que recibe todos los atributos del foro, incluyendo el id del foro, para inicializar completamente el objeto.

- **Método: `getId_foro()`**  
  Obtiene el id del foro.

- **Método: `setId_foro(int id_foro)`**  
  Establece el id del foro.

- **Método: `getTitulo()`**  
  Obtiene el título del foro.

- **Método: `setTitulo(String titulo)`**  
  Establece el título del foro.

- **Método: `getDescripcion()`**  
  Obtiene la descripción del foro.

- **Método: `setDescripcion(String descripcion)`**  
  Establece la descripción del foro.

- **Método: `getFecha_creacion()`**  
  Obtiene la fecha de creación del foro.

- **Método: `setFecha_creacion(Date fecha_creacion)`**  
  Establece la fecha de creación del foro.

- **Método: `getId_creador()`**  
  Obtiene el id del creador del foro.

- **Método: `setId_creador(int id_creador)`**  
  Establece el id del creador del foro.

- **Método: `toString()`**  
  Método `toString` que devuelve una representación en cadena del foro. En este caso, devuelve solo el título del foro.

### 2. **`Participacion`**
La clase `Participacion` es un `enum` que representa los niveles de participación en un foro. Los posibles niveles son `BAJA`, `MEDIA` y `ALTA`.

- **Constructor: `Participacion(int nivel)`**  
  Constructor que recibe el nivel de participación (un valor entero) y lo asigna a la instancia.

- **Método: `getNivel()`**  
  Obtiene el nivel de participación.

- **Método: `getNombre()`**  
  Método que devuelve el nombre de la participación en forma de cadena.

- **Método: `toString()`**  
  Sobrescribe el método `toString()` para proporcionar una representación de cadena del objeto `Participacion`.

### 3. **`SesionUsuario`**
La clase `SesionUsuario` gestiona la sesión del usuario actual en la aplicación. Se utiliza para almacenar el usuario que está actualmente logueado.

- **Método: `setUsuario(Usuario usuario)`**  
  Establece el usuario actual.

- **Método: `getUsuario()`**  
  Obtiene el usuario actual.

- **Método: `cerrarSesion()`**  
  Cierra la sesión del usuario, es decir, establece `usuarioActual` a `null`.

### 4. **`Usuario`**
La clase `Usuario` es abstracta y representa los atributos comunes a todos los tipos de usuario. Esta clase es la base para los usuarios comunes y creadores.

- **Constructor: `Usuario(String nombre, String apellidos, String email, String password)`**  
  Constructor que recibe el nombre, apellidos, email y contraseña para crear un nuevo usuario.

- **Método: `getId_Usuario()`**  
  Obtiene el id del usuario.

- **Método: `setId_Usuario(int id_Usuario)`**  
  Establece el id del usuario.

- **Método: `getNombre()`**  
  Obtiene el nombre del usuario.

- **Método: `setNombre(String nombre)`**  
  Establece el nombre del usuario.

- **Método: `getApellidos()`**  
  Obtiene los apellidos del usuario.

- **Método: `setApellidos(String apellidos)`**  
  Establece los apellidos del usuario.

- **Método: `getEmail()`**  
  Obtiene el correo electrónico del usuario.

- **Método: `setEmail(String email)`**  
  Establece el correo electrónico del usuario.

- **Método: `getPassword()`**  
  Obtiene la contraseña del usuario.

- **Método: `setPassword(String password)`**  
  Establece la contraseña del usuario.

- **Método: `getFechaDeRegistro()`**  
  Obtiene la fecha de registro del usuario.

- **Método: `setFechaDeRegistro(Date fechaDeRegistro)`**  
  Establece la fecha de registro del usuario.

- **Método: `getTipoUsuario()`**  
  Obtiene el tipo de usuario (por ejemplo, "COMUN", "CREADOR").

- **Método: `setTipoUsuario(String tipoUsuario)`**  
  Establece el tipo de usuario.

- **Método: `toString()`**  
  Sobrescribe el método `toString()` para devolver una representación de cadena del objeto `Usuario`.

### 5. **`UsuarioComun`**
La clase `UsuarioComun` hereda de `Usuario` y representa a un usuario común que puede interactuar en el foro.

- **Método: `getNum_Comentarios()`**  
  Obtiene el número de comentarios realizados por el usuario.

- **Método: `getNivel_Participacion()`**  
  Obtiene el nivel de participación del usuario (BAJA, MEDIA, ALTA).

- **Método: `setNum_Comentarios()`**  
  Incrementa el número de comentarios realizados por el usuario.

- **Método: `getTipoUsuario()`**  
  Devuelve el tipo de usuario como "COMUN".

- **Método: `toString()`**  
  Devuelve una representación en cadena del usuario común.

### 6. **`UsuarioCreador`**
La clase `UsuarioCreador` hereda de `Usuario` y representa a un usuario creador de foros.

- **Método: `getNum_ForosCreados()`**  
  Obtiene el número de foros creados por el usuario.

- **Método: `setNum_ForosCreados()`**  
  Incrementa el número de foros creados por el usuario.

- **Método: `getTipoUsuario()`**  
  Devuelve el tipo de usuario como "CREADOR".

- **Método: `toString()`**  
  Devuelve una representación en cadena del usuario creador.


### 7. **`Texto`**
La clase `Texto` representa un mensaje o contenido escrito en un foro. Contiene los atributos y métodos necesarios para definir un texto en el contexto de un foro.
- **Constructor: `Texto(int id_contenido, Date fecha, int id_usuario, int id_foro)`**  
  Este constructor se utiliza cuando se desea crear un objeto `Texto` con todos los datos.
- **Constructor vacío: `Texto()`**  
  Este constructor se utiliza para crear un objeto `Texto` sin inicializar los atributos.
- **Método: `getId_contenido()`**  
  Obtiene el id del contenido (mensaje).
- **Método: `setId_contenido(int id_contenido)`**  
  Establece el id del contenido (mensaje).
- **Método: `getFecha()`**  
  Obtiene la fecha de creación del contenido.
- **Método: `setFecha(Date fecha)`**  
  Establece la fecha de creación del contenido.
- **Método: `getId_usuario()`**  
  Obtiene el id del usuario que publicó el contenido.
- **Método: `setId_usuario(int id_usuario)`**  
  Establece el id del usuario que publicó el contenido.
- **Método: `getId_foro()`**  
  Obtiene el id del foro en el que se publicó el contenido.
- **Método: `setId_foro(int id_foro)`**  
  Establece el id del foro en el que se publicó el contenido.
- **Método: `getTexto()`**  
  Obtiene el texto (contenido) del mensaje.
- **Método: `setTexto(String texto)`**  
  Establece el texto (contenido) del mensaje.
- **Método: `toString()`**  
  Sobrescribe el método toString para representar el objeto `Texto` como una cadena. Este método devuelve el texto del mensaje, útil para mostrarlo de manera amigable.


