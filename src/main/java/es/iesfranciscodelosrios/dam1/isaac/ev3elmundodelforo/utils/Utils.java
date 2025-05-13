package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
    /**
     * Verifica si un correo electrónico tiene un formato válido.
     *
     * @param email Correo electrónico a validar.
     * @return true si el email tiene formato válido, false en caso contrario.
     */
    public static boolean EmailValido(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email != null && email.matches(regex);
    }


    /**
     * Genera un hash SHA-256 para una contraseña.
     *
     * @param password La contraseña en texto plano.
     * @return El hash hexadecimal de la contraseña.
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al generar hash SHA-256", e);
        }
    }

}
