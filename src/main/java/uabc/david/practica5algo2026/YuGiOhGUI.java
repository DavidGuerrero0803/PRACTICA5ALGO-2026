package uabc.david.practica5algo2026;

import uabc.david.practica5algo2026.Modelo.CatalogoCartas;
import uabc.david.practica5algo2026.Vista.CarruselGUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;

/**
 * Esta es la clase principal, en donde se arma la ventana de juego.
 */
public class YuGiOhGUI extends Application {
    private BorderPane raiz;
    private CarruselGUI carrusel;

    @Override
    public void start(Stage escenario) {
        CatalogoCartas modelo = new CatalogoCartas();
        try (InputStream csv = getClass().getResourceAsStream("/datos/cartas_yugioh.csv")) {
            modelo.cargarArchivo(csv);
        } catch (IOException e) {
            Alert alerta = new Alert(Alert.AlertType.ERROR, "No se pudo leer el archivo de cartas.");
            alerta.showAndWait();
        }

        carrusel = new CarruselGUI();

        Image fondo = new Image(getClass().getResource("/imagenes/fondo.png").toExternalForm());
        raiz = new BorderPane();
        raiz.setBackground(new Background(new BackgroundImage(fondo,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, false, true))));
        raiz.setCenter(carrusel);

        Scene escena = new Scene(raiz, 1200, 740);
        escena.getStylesheets().add(getClass().getResource("/estilos/estilos.css").toExternalForm());

        escenario.setTitle("Yu-Gi-Oh! - 25th Anniversary Tin");
        escenario.setScene(escena);
        escenario.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
