package es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.utils;

import es.iesfranciscodelosrios.dam1.isaac.ev3elmundodelforo.HelloApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewUtils {
    public static void abrirNuevaVentana(String fxml, String titulo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle(titulo);
        stage.show();
    }
    public static void abrirNuevaVentanaFija(String fxml, String titulo) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle(titulo);


        stage.show();
    }

}
