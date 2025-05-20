package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.model;

/**
 * Enum que representa los niveles de participación en un foro.
 * Los niveles disponibles son: BAJA, MEDIA, ALTA.
 */
public enum Participacion {
    BAJA(1),
    MEDIA(2),
    ALTA(3);

    private final int nivel;

    /**
     * Constructor de la clase enum, que recibe el nivel numérico de participación.
     *
     * @param nivel El nivel numérico de la participación (1 para BAJA, 2 para MEDIA, 3 para ALTA).
     */
    Participacion(int nivel) {
        this.nivel = nivel;
    }

    /**
     * Método que devuelve el nombre de la participación en forma de cadena.
     *
     * @return El nombre de la participación como cadena ("Baja", "Media" o "Alta").
     */
    public String getNombre() {
        String participacion;
        switch (this) {
            case BAJA:
                participacion = "Baja";
                break;
            case MEDIA:
                participacion = "Media";
                break;
            case ALTA:
                participacion = "Alta";
                break;
            default:
                participacion = null;
        }
        return participacion;
    }

    /**
     * Sobrescritura del método toString(), que proporciona una representación de cadena del objeto.
     * Este método devuelve el nombre de la participación, de acuerdo al nivel asignado.
     *
     * @return Una cadena que representa el nivel de participación ("Baja", "Media", "Alta").
     */
    @Override
    public String toString() {
        switch (this) {
            case BAJA:
                return "Baja";
            case MEDIA:
                return "Media";
            case ALTA:
                return "Alta";
            default:
                return super.toString();
        }
    }
}
