package uabc.david.practica5algo2026.Vista;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;

/**
 * Esta clase representa la música de fondo en ciclo continuo.
 */
public class ReproductorMusica {
    private MediaPlayer reproductor;

    public ReproductorMusica(URL archivo) {
        if (archivo != null) {
            try {
                reproductor = new MediaPlayer(new Media(archivo.toExternalForm()));
                reproductor.setCycleCount(MediaPlayer.INDEFINITE);
                reproductor.setVolume(0.35);
            } catch (Exception e) {
                reproductor = null;
            }
        }
    }

    public void reproducirMusica() {
        if (reproductor != null) {
            reproductor.play();
        }
    }

    public void setSilenciado(boolean silenciado) {
        if (reproductor != null) {
            reproductor.setMute(silenciado);
        }
    }

    public void detenerMusica() {
        if (reproductor != null) {
            reproductor.stop();
        }
    }
}
