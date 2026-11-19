package uabc.david.practica5algo2026.Vista;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.AudioClip;

import java.net.URL;

/**
 * Clase de botón personalizado que hereda de botón con dos estados.
 * Cada clic alterna entre la imagen de bocina encendida (ON) y la de bocina
 * tachada (MUTE).
 */
public class SoundImageButton extends Button {
    private ImageView vistaDeLaImagen;
    private BooleanProperty silenciado;
    private Image imagenOn;
    private Image imagenMute;
    private AudioClip sonidoHover;
    private AudioClip sonido;

    private static final String IMAGEN_ON = "/imagenes/bocina_on.png";
    private static final String IMAGEN_MUTE = "/imagenes/bocina_mute.png";
    private static final String SONIDO_HOVER = "/audio/hover.wav";
    private static final String SONIDO_CLIC = "/audio/clic.wav";

    public SoundImageButton() {
        super();
        silenciado = new SimpleBooleanProperty(false);
        inicializarComponentes();
        configurarEfectosDeSonido();
        aplicarEventos();
    }

    private void inicializarComponentes() {
        imagenOn = new Image(getClass().getResource(IMAGEN_ON).toExternalForm());
        imagenMute = new Image(getClass().getResource(IMAGEN_MUTE).toExternalForm());

        vistaDeLaImagen = new ImageView(imagenOn);
        vistaDeLaImagen.setFitWidth(34);
        vistaDeLaImagen.setFitHeight(34);
        vistaDeLaImagen.setPreserveRatio(true);

        setGraphic(vistaDeLaImagen);
        setPrefSize(52, 52);
        setStyle("-fx-background-color: transparent;");
        setCursor(Cursor.HAND);
        setFocusTraversable(false);
    }

    private void configurarEfectosDeSonido() {
        sonidoHover = cargarSonido(SONIDO_HOVER);
        sonido = cargarSonido(SONIDO_CLIC);
    }

    private AudioClip cargarSonido(String ruta) {
        try {
            URL url = getClass().getResource(ruta);
            if (url != null) {
                AudioClip clip = new AudioClip(url.toExternalForm());
                clip.setVolume(0.5);
                return clip;
            }
        } catch (Exception e) {
            // sin sonido, el botón sigue funcionando.
        }
        return null;
    }

    private void aplicarEventos() {
        setOnMouseEntered(e -> reproducirSonidoHover());
        // Cada clic rota entre ON y MUTE.
        setOnAction(e -> {
            setSilenciado(!isSilenciado());
            reproducirSonido();
        });
    }

    public void reproducirSonido() {
        if (sonido != null) {
            sonido.play();
        }
    }

    public void reproducirSonidoHover() {
        if (sonidoHover != null && !isSilenciado()) {
            sonidoHover.play();
        }
    }

    public boolean isSilenciado() {
        return silenciado.get();
    }

    public void setSilenciado(boolean valor) {
        silenciado.set(valor);
        vistaDeLaImagen.setImage(valor ? imagenMute : imagenOn);
    }

    public BooleanProperty silenciadoProperty() {
        return silenciado;
    }
}
