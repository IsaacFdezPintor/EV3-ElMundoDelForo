package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * Clase utilizada para trabajar con archivos XML mediante JAXB.
 */
public class XMLManager {

    /**
     * Escribe un objeto genérico a un archivo XML.
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
            // Crea un contexto JAXB para el objeto de la clase del objeto 'c'
            context = JAXBContext.newInstance(c.getClass());
            // Crea el marshaller que será responsable de escribir el objeto en XML
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // Formato legible para humanos
            m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");      // Codificación UTF-8 para el archivo XML
            // Escribe el objeto en un archivo XML especificado por el nombre de archivo
            m.marshal(c, new File(filename));
            result = true; // Si no ocurre una excepción, marca como exitoso el proceso
        } catch (JAXBException e) {
            e.printStackTrace(); // Si ocurre un error, imprime la traza del error
        }
        return result; // Devuelve true si el proceso fue exitoso, false si hubo un error
    }

    /**
     * Lee un archivo XML y convierte su contenido en un objeto Java.
     *
     * @param <T> El tipo del objeto a deserializar.
     * @param c Una instancia vacía del tipo de objeto esperado (solo se usa para obtener su clase).
     * @param filename Nombre del archivo XML desde donde se leerá el objeto.
     * @return Una nueva instancia del objeto con los datos leídos del XML, o el objeto original si ocurre un error.
     */
    public static <T> T readXML(T c, String filename) {
        T result = c; // Inicializa el resultado como el objeto vacío proporcionado
        JAXBContext context;
        try {
            // Crea un contexto JAXB para el tipo de objeto que se deserializa
            context = JAXBContext.newInstance(c.getClass());
            // Crea el unmarshaller que será responsable de leer el XML y convertirlo en un objeto
            Unmarshaller um = context.createUnmarshaller();
            // Lee el archivo XML y lo convierte en una instancia del tipo T
            result = (T) um.unmarshal(new File(filename));
        } catch (JAXBException e) {
            e.printStackTrace(); // Si ocurre un error, imprime la traza del error
        }
        return result; // Devuelve el objeto deserializado o el objeto vacío en caso de error
    }
}
