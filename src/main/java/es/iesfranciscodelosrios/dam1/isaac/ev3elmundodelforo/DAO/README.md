# Carpeta `DAO` - Clases de Acceso a Datos

La carpeta `DAO` contiene clases que gestionan la comunicación entre la aplicación y la base de datos. Estas clases son responsables de realizar las operaciones de inserción, actualización, eliminación y consulta sobre los datos.

## Clases y Métodos

### 1. **`DAOForo`**
La clase `DAOForo` gestiona las operaciones de acceso a los datos relacionados con los foros en la base de datos. Esta clase incluye métodos para insertar, actualizar, eliminar y recuperar foros y sus respectivos creadores.

- **Método: `insert(Foro foro, Usuario creador)`**  
  Inserta un nuevo foro en la base de datos con los datos proporcionados por el foro y el creador.

- **Método: `update(Foro foroNuevo, Foro foroViejo, Usuario creador)`**  
  Actualiza un foro existente si el usuario es el creador del foro.

- **Método: `delete(Foro foro)`**  
  Elimina un foro de la base de datos si el usuario es el creador del foro.

- **Método: `findAll()`**  
  Recupera todos los foros existentes en la base de datos.

- **Método: `findForosByID(int idCreador)`**  
  Recupera todos los foros creados por un usuario específico.

- **Método: `findCreador(Foro foro)`**  
  Recupera el creador de un foro dado, devolviendo un objeto `UsuarioCreador` con sus detalles.

### 2. **`DAOTexto`**
La clase `DAOTexto` gestiona las operaciones de acceso a los datos relacionados con los textos (mensajes) en los foros. Esta clase incluye métodos para insertar, eliminar y recuperar textos en la base de datos.

- **Método: `insert(Usuario user, Foro foro, Texto texto)`**  
  Inserta un nuevo texto en la base de datos. Recibe un objeto `Usuario`, un objeto `Foro` y un objeto `Texto` como parámetros.

- **Método: `findAllByForoId(int idForo)`**  
  Recupera todos los textos de un foro dado.

- **Método: `delete(Texto texto)`**  
  Elimina un texto de la base de datos.  

### 3. **`DAOUsuarioComun`**

La clase `DAOUsuarioComun` es responsable de gestionar las operaciones de acceso a los datos relacionados con los usuarios de tipo "Comun" en la base de datos. Incluye métodos para insertar, actualizar, eliminar y recuperar usuarios, así como para gestionar sus comentarios y participación en el foro.


- **Método: `insert(UsuarioComun usuario)`**
Inserta un nuevo usuario de tipo Comun en la base de datos si no existe un usuario con el mismo correo electrónico.

- **Método: `update(UsuarioComun usuarioNuevo, UsuarioComun usuarioActual)`**
Actualiza los datos de un usuario de tipo Comun en la base de datos.

- **Método: `delete(UsuarioComun usuario)`**
Elimina un usuario de tipo Comun de la base de datos.


- **Método: `findByCorreo(String email)`**
Busca un usuario de tipo Comun por su correo electrónico.

- **Método: `findAll()`**
Recupera todos los usuarios de tipo Comun de la base de datos.

- **Método: `check(String email, String password)`**
Verifica si un usuario de tipo Comun existe en la base de datos con las credenciales proporcionadas (correo electrónico y contraseña).

- **Método: `existsByEmail(String email)`**
Verifica si un usuario con el correo electrónico proporcionado ya existe en la base de datos.


- **Método: `updateNumComentarios(UsuarioComun comun)`**
Actualiza el número de comentarios realizados por un usuario de tipo Comun.

- **Método: `obtenerNumeroComentarios(UsuarioComun usuarioComun)`**
Obtiene el número de comentarios realizados por un usuario de tipo Comun.


- **Método: `updateParticipacion(UsuarioComun usuarioComun, Participacion participacion)`**
Actualiza el nivel de participación de un usuario de tipo Comun.


- **Método: `obtenerParticipacion(Usuario usuarioComun)`**
Obtiene el nivel de participación de un usuario de tipo Comun.

### 4. **`DAOUsuarioCreador`**

La clase `DAOUsuarioCreador` proporciona los métodos necesarios para gestionar las operaciones CRUD (crear, leer, actualizar, eliminar) de los usuarios tipo "CREADOR" en la base de datos. Además, incluye funciones para la autenticación y validación de usuarios creadores.


- **Método: `insert(UsuarioCreador usuario)`**
Inserta un nuevo usuario creador en la base de datos.
- **Método: `update(UsuarioCreador usuarioNuevo, UsuarioCreador usuarioActual)`**
Actualiza los datos de un usuario creador existente.
- **Método: `delete(UsuarioCreador usuario)`**
Elimina un usuario creador de la base de datos.
- **Método: `findByCorreo(String email)`**
Busca un usuario creador por su correo electrónico.
- **Método: `findAll()`**
Recupera todos los usuarios creadores registrados en la base de datos.
- **Método: `check(String email, String password)`**
Verifica si un usuario con el correo electrónico y la contraseña proporcionados existe en la base de datos.
- **Método: `existsByEmail(String email)`**
Verifica si ya existe un usuario con el correo electrónico proporcionado.
- **Método: `updateNumForos(UsuarioCreador creador)`**
Actualiza el número de foros creados por un usuario creador.
