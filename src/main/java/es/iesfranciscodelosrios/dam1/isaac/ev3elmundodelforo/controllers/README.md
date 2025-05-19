# Carpeta `controllers` - Controlador de la vista
Este directorio contiene todo aquellos controladores que gestionan la interacción entre la vista y el modelo de datos. Los controladores son responsables de recibir las acciones del usuario, procesar los datos y actualizar la vista en consecuencia.
## Clases y Métodos

### 1. **`ActualizarForoController.java`**
Controlador de la pantalla para actualizar un foro ya creado por el usuario.

- **Método: `initialize() `**
Método de inicialización que se ejecuta al cargar la vista. Configura la lista de foros y carga los foros del usuario actual.
- **Método: `configurarListaForos()`**  
Configura la lista de foros para mostrar solo los títulos de los mismos.
- **Método: `cargarForos()`**  
Carga todos los foros creados por el usuario actual en la lista desplegable.
- **Método: `actualizarForo()`**  
Método llamado cuando el usuario presiona el botón de actualizar foro. Actualiza el foro seleccionado con los nuevos datos (título y descripción).

### 2. **`CrearForoController.java`**
Controlador de la pantalla para crear un nuevo foro.

- **Método: `CrearForo()`**
Método llamado cuando el usuario hace clic en el botón para crear un foro. Este método verifica que los campos no estén vacíos, crea el foro en la base de datos y cierra la ventana de creación del foro.

### 3. **`ForoControllersComun.java`**
Controlador de la pantalla principal del foro para usuarios comunes.

- **Método: `initialize()`**
Inicializa el controlador cargando la lista de foros disponibles.
- **Método: `configurarListaForos()`**
Configura cómo se mostrarán los foros en la ListView (solo el título).
- **Método: `cargarForos()`**
Carga todos los foros desde la base de datos.
- **Método: `cargarComentarios(Foro foro)`**
Carga los comentarios asociados a un foro específico.
- **Método: `PublicarComentario`**
Publica un comentario nuevo en el foro seleccionado.
- **Método: `mostrarForoSeleccionado(MouseEvent mouseEvent)`**
Muestra la descripción y creador del foro seleccionado.
- **Método: `miInformacion(ActionEvent actionEvent) `**
Abre la ventana de información personal del usuario.
- **Método: `cerrarSesion(ActionEvent actionEvent)`**
Cierra la sesión del usuario y redirige al login.
- **Método: `actualizarListaForos()`**
Actualiza la lista de foros visibles en pantalla.

### 4. **`ForoControllersCreador.java`**
Controlador de la pantalla principal del foro para usuarios creadores.

- **Método: `initialize()`**
Inicializa el controlador cargando la lista de foros disponibles.
- **Método: `configurarListaForos()`**
Configura cómo se mostrarán los foros en la ListView (solo el título).
- **Método: `cargarForos()`**
Carga todos los foros desde la base de datos.
- **Método: `cargarComentarios(Foro foro)`**
Carga los comentarios asociados a un foro específico.
- **Método: `mostrarForoSeleccionado(MouseEvent mouseEvent)`**
Muestra la descripción y creador del foro seleccionado.
- **Metodo: `eliminarItemSeleccionado`**
Elimina el foro o comentario seleccionado por el usuario.
- **Método: `anadirNuevoForo(ActionEvent actionEvent)`**
Abre una ventana para añadir un nuevo foro.
- **Método: `actualizarForo(ActionEvent actionEvent)`**
Abre una ventana para actualizar un foro.
- **Método: `miInformacion(ActionEvent actionEvent) `**
Abre la ventana de información personal del usuario.
- **Método: `cerrarSesion(ActionEvent actionEvent)`**
Cierra la sesión del usuario y redirige al login.
- **Método: `actualizarListaForos()`**
Actualiza la lista de foros visibles en pantalla.

### 5. **`InfoController.java`**
Controlador de la vista de información del usuario.

- **Método: `initialize()`**
Este método recupera el usuario en sesión y muestra sus datos en los labels correspondientes.
- **Método: `eliminarUsuario(ActionEvent event)`**
Elimina el usuario actualmente logueado dependiendo de su tipo.
- **Método: `cerrarVentanasYAbrirLogin()`**
Cierra la ventana actual y abre la ventana de login.

### 6. **`InfoControllerComun.java`**
Controlador de la vista de información para usuarios comunes.

- **Método: `initialize()`**
  Este método recupera el usuario en sesión y muestra sus datos en los labels correspondientes.
- **Método: `eliminarUsuario(ActionEvent event)`**
Elimina el usuario actualmente logueado.
- **Método: `cerrarVentanasYAbrirLogin()`**
  Cierra la ventana actual y abre la ventana de login.

### 7. **`InfoControllerComun.java`**
Controlador de la vista de información para usuarios comunes.

- **Método: `initialize()`**
  Este método recupera el usuario en sesión y muestra sus datos en los labels correspondientes.
- **Método: `eliminarUsuario(ActionEvent event)`**
  Elimina el usuario actualmente logueado.
- **Método: `cerrarVentanasYAbrirLogin()`**
  Cierra la ventana actual y abre la ventana de login.

### 8. **`LoginController`**
Controlador para la vista de inicio de sesión.

- **Método: `onLogin()`**
Método llamado cuando el usuario hace clic en el botón de iniciar sesión. Este método valida los campos, comprueba las credenciales del usuario, y redirige a la vista correspondiente según el tipo de usuario.- **Método: `eliminarUsuario(ActionEvent event)`**

- **Método: `setRegistrar(ActionEvent actionEvent)`**
Método que se ejecuta al hacer clic en el botón de "Registrar". Abre la ventana del formulario de registro de usuario

### 9. **`RegistrarControllers`**
Controlador para la vista de registro de usuarios.

- **Método: `onRegistrarUsuario()`**
Método invocado al hacer clic en el botón de registrar. Realiza las validaciones necesarias y registra el usuario si los datos son válidos y el correo no existe en la base de datos.
  
   

    
  