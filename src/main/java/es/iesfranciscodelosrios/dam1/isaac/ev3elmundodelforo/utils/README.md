# Carpeta `utils` - Herramientas Comunes para la Aplicación

La carpeta `utils` contiene clases y métodos utilitarios diseñados para proporcionar funcionalidades comunes y reutilizables dentro de la aplicación. Estas clases se utilizan para facilitar el manejo de tareas repetitivas, como la validación de correos electrónicos, contraseñas y la gestión de ventanas.

## Clases y Métodos

### 1. **`Utils`**
La clase `Utils` contiene métodos estáticos que ayudan en tareas comunes como la validación de correos electrónicos y contraseñas.

- **Método: `EmailValido(String email)`**  
Verifica si un correo electrónico tiene un formato válido. Este método utiliza una expresión regular para comprobar que el correo electrónico sigue un formato estándar. El formato correcto es algo como: nombre@dominio.com
- **Método: `ContraseñaValida(String contraseña)`**  
Valida si la contraseña cumple con los criterios de seguridad establecidos.
  - Los criterios incluyen:
  - Minimo 8 caracteres
  - Maximo 15
  - Al menos una letra mayúscula
  - Al menos una letra minucula
  - Al menos un dígito
  - No espacios en blanco
  - Al menos 1 caracter especial ( NO # )

### 2. **`ViewUtils`**
La clase `ViewUtils` proporciona métodos para gestionar los metodos comunes de la vista 

- **Método: `abrirNuevaVentana(String fxml, String titulo)`**  
  Este método carga un archivo FXML, crea una nueva escena a partir de este archivo y abre una nueva ventana con el título proporcionado.
- **Método: `abrirNuevaVentanaFija(String fxml, String titulo)`**  
  Similar al método anterior, pero esta ventana no es redimensionable. Esto es útil si se quiere que la ventana tenga un tamaño fijo.

