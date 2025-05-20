# MUNDOFORO – Proyecto Final 1º DAM (3ª Evaluación)


## 1. Resumen del Proyecto

**MUNDOFORO** es una aplicación desarrollada en **Java** utilizando **JavaFX** como interfaz gráfica. Su objetivo es simular un sistema de foros, permitiendo el registro y autenticación de usuarios con diferentes roles:

- **Usuario Creador**: puede crear, modificar y eliminar sus propios foros. También puede eliminar su cuenta.
- **Usuario Común**: puede comentar en los foros existentes y eliminar su cuenta. Su nivel de participación se mide según los comentarios que realiza.

### Interfaz Gráfica

- **Panel izquierdo**: Lista de foros (filtrada según el tipo de usuario).
- **Panel derecho**: Comentarios del foro seleccionado, con opciones para añadir o visualizar.
- **Botones adicionales**: Para gestionar foros y ver la información del usuario.

---

## 2. Fase de Diseño

### Modelo Entidad-Relación (ER)
Incluye entidades como **Usuario**, **Foro**, **Texto**, con relaciones 1:N entre ellas.

###  Casos de Uso
- Registro y login
- Creación y gestión de foros (solo creadores)
- Comentar en foros (solo comunes)
- Visualización de información personal
- Eliminación de usuarios

###  Diagrama de Clases
- Clases principales: `Usuario`, `UsuarioCreador`, `UsuarioComun`, `Foro`, `Texto`
- Uso de herencia y polimorfismo

### Diagrama de Pantallas
Flujo entre las distintas interfaces:
1. Login / Registro
2. Interfaz según tipo de usuario
3. Ventanas emergentes: crear/actualizar foros, publicar comentario, ver perfil

---

## 3. Fase de Desarrollo

###  Temporalización por Sprints
- **Sprint 1**: Conexión a BD, modelo ER, GitHub
- **Sprint 2**: Desarrollo de clases y DAOs
- **Sprint 3**: Interfaz gráfica (FXML y controladores)
- **Sprint 4**: Depuración, documentación

###  Código Destacado
- Validación de correo y contraseña con expresiones regulares
- Carga dinámica de foros y comentarios según el usuario
- Gestión del nivel de participación (enum: BAJA, MEDIA, ALTA)

### Tecnologías y Herramientas
- **Java 11+**, **JavaFX**, **FXML**
- **MySQL** para persistencia de datos
- **Maven**, **GitHub**, **JAXB**
- Herramientas colaborativas: **Discord**, **ChatGPT**

### Mejoras Futuras
- Unirse a foros
- Clasificación por categorías
- Subforos
- Soporte multimedia

---

## 4. Lista de Comprobación

### Herencia
- Clase abstracta `Usuario`
- Subclases: `UsuarioCreador` y `UsuarioComun` con atributos específicos

### Relaciones 1:N
- `UsuarioCreador` ↔ `Foro`
- `Foro` ↔ `Texto`
- `UsuarioComun` ↔ `Texto`

### DAO
- DAOs implementados: `ForoDAO`, `TextoDAO`, `UsuarioComunDAO`, `UsuarioCreadorDAO`, `IGenericDAO`
- Ejemplo destacado: `updateParticipacion()` para actualizar el nivel de participación

### Mostrar
- Método `cargarComentario()` carga los comentarios asociados al foro seleccionado
- `mostrarForoSeleccionado()` gestiona el cambio de foro y actualiza la interfaz

### Insertar
- Método `crearForo()` añade un nuevo foro a la base de datos, actualiza el número de foros del usuario creador, y refresca la vista

### Eliminar
- Elimina foros o comentarios según la selección y el rol
- Validación previa y recarga de vistas tras eliminación

### Actualizar
- Solo disponible para foros propios del usuario creador
- Se reutiliza la vista de creación con campos precargados
- Método `updateForo()` actualiza la información en la BD y en la interfaz

---
Proyecto desarrollado por Isaac Fernández Pintor 

---

