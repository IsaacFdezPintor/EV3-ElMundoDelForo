# Carpeta `utils` - Herramientas Comunes para la Aplicación

La carpeta `utils` contiene clases y métodos utilitarios diseñados para proporcionar funcionalidades comunes y reutilizables dentro de la aplicación. Estas clases se utilizan para facilitar el manejo de tareas repetitivas, como la validación de correos electrónicos, contraseñas y la gestión de ventanas.

## Clases y Métodos

### 1. **`Utils`**
La clase `Utils` contiene métodos estáticos que ayudan en tareas comunes como la validación de correos electrónicos y contraseñas.

- **Método: `EmailValido(String email)`**  
  Valida si una cadena de texto tiene el formato correcto de un correo electrónico. Utiliza una expresión regular para comprobar que el correo electrónico cumple con los estándares comunes de formato.

- **Método: `ContraseñaValida(String contraseña)`**  
  Valida si una contraseña cumple con ciertos criterios de seguridad. Los requisitos incluyen al menos:
    - 8 caracteres.
    - Al menos una letra mayúscula.
    - Al menos una letra minúscula.
    - Al menos un número.
    - Al menos un carácter especial (por ejemplo: `@$!%*?&`).

### 2. **`ViewUtils`**
La clase `ViewUtils` proporciona métodos para gestionar la apertura de nuevas ventanas . Permite abrir ventanas redimensionales o fijas.

- **Método: `abrirNuevaVentana(String fxml, String titulo)`**  
  Abre una nueva ventana con el archivo FXML especificado y el título indicado. La ventana es redimensionable.

- **Método: `abrirNuevaVentanaFija(String fxml, String titulo)`**  
  Similar al método anterior, pero esta ventana es fija y no puede ser redimensionada. 


