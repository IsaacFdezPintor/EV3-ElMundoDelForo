package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Esta clase proporciona métodos para gestionar de los metodos comunes de las vistas
 */

public class ViewUtils {

    /**
     * Abre una nueva ventana con el archivo FXML y el título proporcionado.
     *
     * Este método carga un archivo FXML, crea una nueva escena a partir de este archivo
     * y abre una nueva ventana con el título proporcionado.
     *
     * @param fxml Nombre del archivo FXML que describe la interfaz de la nueva ventana.
     * @param titulo El título que se mostrará en la barra de la ventana.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    public static void abrirNuevaVentana(String fxml, String titulo) throws IOException {
        // Carga el archivo FXML utilizando el cargador de recursos de la clase 'Start'
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource(fxml));
        // Crea una nueva escena a partir del archivo FXML cargado
        Scene scene = new Scene(fxmlLoader.load());
        // Crea una nueva instancia de Stage (la ventana) y asigna la escena
        Stage stage = new Stage();
        stage.setScene(scene);
        // Asigna el título de la ventana
        stage.setTitle(titulo);
        // Muestra la ventana
        stage.show();
    }

    /**
     * Abre una nueva ventana fija con el archivo FXML y el título proporcionado.
     *
     * Similar al método anterior, pero esta ventana no es redimensionable.
     * Esto es útil si se quiere que la ventana tenga un tamaño fijo.
     *
     * @param fxml Nombre del archivo FXML que describe la interfaz de la nueva ventana.
     * @param titulo El título que se mostrará en la barra de la ventana.
     * @throws IOException Si ocurre un error al cargar el archivo FXML.
     */
    public static void abrirNuevaVentanaFija(String fxml, String titulo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        // Hace la ventana no redimensionable (tamaño fijo)
        stage.setResizable(false);
        stage.setTitle(titulo);
        stage.show();
    }
}
