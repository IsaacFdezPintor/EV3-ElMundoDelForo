package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.baseDatos;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
     * Esta clase proporciona métodos estáticos para escribir y leer objetos desde archivos XML.
     * Se utiliza JAXB (Java Architecture for XML Binding) para la serialización y deserialización de objetos.
     */
    public class XMLManager {

        /**
         * Escribe un objeto genérico en un archivo XML.
         * Utiliza JAXB para convertir el objeto a formato XML y guardarlo en el archivo especificado.
         *
         * @param c El objeto que se desea serializar a XML.
         * @param filename El nombre del archivo donde se almacenará el objeto en formato XML.
         * @return true si la escritura del XML fue exitosa, false en caso contrario.
         */
        public static <T> boolean writeXML(T c, String filename) {
            boolean result = false;
            JAXBContext context;
            try {
                context = JAXBContext.newInstance(c.getClass());
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
                m.marshal(c, new File(filename));
                result = true;
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return result;
        }

        /**
         * Lee un objeto genérico desde un archivo XML y lo convierte en un objeto de la clase especificada.
         * Utiliza JAXB para deserializar el contenido del archivo XML a un objeto de la clase indicada.
         *
         * @param c Una instancia del objeto que se desea deserializar, usada para obtener la clase.
         * @param filename El nombre del archivo XML desde donde se leerá el objeto.
         * @return El objeto deserializado del archivo XML. Si ocurre un error, retorna el objeto proporcionado.
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

