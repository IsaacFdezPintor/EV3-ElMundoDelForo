package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Clase utilitaria para trabajar con archivos XML mediante JAXB.
 * Permite serializar (escribir) y deserializar (leer) objetos Java en/desde archivos XML.
 */
public class XMLManager {

    /**
     * Serializa un objeto genérico a un archivo XML.
     * Usa JAXB para convertir el objeto a XML y lo guarda en el archivo especificado.
     *
     * @param <T> El tipo del objeto a serializar.
     * @param c El objeto que se quiere guardar en formato XML.
     * @param filename Nombre del archivo XML donde se guardará el objeto.
     * @return {@code true} si el proceso fue exitoso, {@code false} si ocurrió una excepción.
     */
    public static <T> boolean writeXML(T c, String filename) {
        boolean result = false;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(c.getClass());
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Formato legible
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");      // Codificación
            m.marshal(c, new File(filename));                      // Escritura en archivo
            result = true;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Deserializa un archivo XML y convierte su contenido en un objeto Java.
     * Usa JAXB para leer el archivo XML y crear una instancia del objeto con los datos.
     *
     * @param <T> El tipo del objeto a deserializar.
     * @param c Una instancia vacía del tipo de objeto esperado (solo se usa para obtener su clase).
     * @param filename Nombre del archivo XML desde donde se leerá el objeto.
     * @return Una nueva instancia del objeto con los datos leídos del XML, o el objeto original si ocurre un error.
     */
    public static <T> T readXML(T c, String filename) {
        T result = c;
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(c.getClass());
            Unmarshaller um = context.createUnmarshaller();
            result = (T) um.unmarshal(new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return result;
    }
}
