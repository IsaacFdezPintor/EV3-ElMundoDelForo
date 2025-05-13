package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils;

import java.util.regex.Pattern;

public class Utils {

    /**
     * Verifica si un correo electrónico tiene un formato válido.
     *
     * Este método utiliza una expresión regular para comprobar que el correo electrónico sigue un formato estándar.
     * El formato correcto es algo como: nombre@dominio.com.
     *
     * @param email Correo electrónico a validar.
     * @return true si el email tiene formato válido, false en caso contrario.
     */
    public static boolean EmailValido(String email) {
        // Expresión regular para validar un correo electrónico.
        // El patrón permite letras, números, guiones, puntos y otros caracteres especiales en la parte local,
        // y verifica que haya un dominio válido con al menos un punto y un dominio de nivel superior.
        // Información extraída de la página https://es.stackoverflow.com/questions/142/validar-un-email-en-javascript-que-acepte-todos-los-caracteres-latinos
        String regex = "^[-\\w.%+]{1,64}@(?!-)(?:[A-Za-z0-9-]{1,63}\\.){1,125}[A-Za-z]{2,63}$";

        // Verifica si el correo no es nulo y si coincide con el formato de la expresión regular
        return email != null && email.matches(regex);
    }

    /**
     * Valida si la contraseña cumple con los criterios de seguridad establecidos.
     * Los criterios incluyen:
     * Minimo 8 caracteres
     * Maximo 15
     * Al menos una letra mayúscula
     * Al menos una letra minucula
     * Al menos un dígito
     * No espacios en blanco
     * Al menos 1 caracter especial
     *
     * Este método asegura que la contraseña no solo tenga una longitud mínima,
     * sino que también cumpla con un nivel adecuado de complejidad.
     *
     * @param contraseña La contraseña a validar.
     * @return true si la contraseña cumple con todos los requisitos de seguridad, false en caso contrario.
     */
    public static boolean ContraseñaValida(String contraseña) {
        // Expresión regular que valida los criterios de seguridad de la contraseña.
        // Información extraída de la página https://es.stackoverflow.com/questions/4300/expresiones-regulares-para-contrase%C3%B1a-en-base-a-una-politica
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$@$!%*?&])[A-Za-z\\d$@$!%*?&]{8,15}$";

        // Verifica si la contraseña no es nula y si coincide con la expresión regular
        return contraseña != null && contraseña.matches(regex);
    }
}
