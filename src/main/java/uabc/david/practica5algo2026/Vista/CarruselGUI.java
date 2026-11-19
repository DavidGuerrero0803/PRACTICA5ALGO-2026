package uabc.david.practica5algo2026.Vista;

import uabc.david.practica5algo2026.Modelo.CarruselCartas;
import uabc.david.practica5algo2026.Modelo.CartaYuGiOh;

import javafx.beans.binding.Bindings;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.net.URL;

/**
 * Representa visualmente el carrusel de tres cartas (anterior pequeña,
 * actual grande, siguiente pequeña), los datos de la carta actual e indicador
 * de posición.
 */
public class CarruselGUI extends VBox {
    private ImageView imagenAnterior;
    private ImageView imagenCentral;
    private ImageView imagenSiguiente;

    private VBox ladoAnterior;
    private VBox ladoSiguiente;

    private Label titulo;
    private Label posicion;
    private Label nombre;
    private Label atributo;
    private Label nivel;
    private Label tipo;
    private Label rareza;
    private Label ataque;
    private Label defensa;

    private Text descripcion;

    public CarruselGUI() {
        setPadding(new Insets(20, 26, 16, 26));
        setSpacing(12);

        titulo = new Label();
        titulo.getStyleClass().add("titulo");
        posicion = new Label();
        posicion.getStyleClass().add("posicion");
        Region relleno = new Region();
        HBox.setHgrow(relleno, Priority.ALWAYS);
        HBox encabezado = new HBox(titulo, relleno, posicion);
        encabezado.setAlignment(Pos.BOTTOM_LEFT);

        imagenAnterior = crearImagen(170);
        imagenCentral = crearImagen(360);
        imagenSiguiente = crearImagen(170);
        ladoAnterior = crearLado("< Anterior", imagenAnterior);
        ladoSiguiente = crearLado("Siguiente >", imagenSiguiente);

        HBox fila = new HBox(40, ladoAnterior, imagenCentral, ladoSiguiente);
        fila.setAlignment(Pos.CENTER);

        nombre = new Label();
        nombre.getStyleClass().add("nombre-carta");
        atributo = new Label();
        nivel = new Label();
        tipo = new Label();
        rareza = new Label();
        ataque = new Label();
        defensa = new Label();

        descripcion = new Text();
        descripcion.getStyleClass().add("descripcion");
        ScrollPane scroll = new ScrollPane(descripcion);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scroll.setMaxHeight(130);
        descripcion.wrappingWidthProperty().bind(Bindings.max(0, scroll.widthProperty().subtract(36)));

        HBox fila1 = new HBox(30, atributo, nivel, tipo, rareza);
        HBox fila2 = new HBox(30, ataque, defensa);
        VBox info = new VBox(6, nombre, fila1, fila2, scroll);
        info.getStyleClass().add("panel-info");

        getChildren().addAll(encabezado, fila, info);
    }

    private ImageView crearImagen(double alto) {
        ImageView imagen = new ImageView();
        imagen.setPreserveRatio(true);
        imagen.setFitHeight(alto);
        return imagen;
    }

    private VBox crearLado(String texto, ImageView imagen) {
        VBox lado = new VBox(6, new Label(texto), imagen);
        lado.setAlignment(Pos.CENTER);
        lado.setCursor(Cursor.HAND);
        return lado;
    }

    public void mostrarCarrusel(CarruselCartas carrusel) {
        CartaYuGiOh actual = carrusel.getActual();

        imagenAnterior.setImage(cargarImagen(carrusel.getAnterior()));
        imagenCentral.setImage(cargarImagen(actual));
        imagenSiguiente.setImage(cargarImagen(carrusel.getSiguiente()));

        boolean hayVecinas = carrusel.getTotal() > 1;
        ladoAnterior.setVisible(hayVecinas);
        ladoSiguiente.setVisible(hayVecinas);

        posicion.setText(carrusel.getPosicion() + "/" + carrusel.getTotal());

        nombre.setText(actual.getNombre());
        atributo.setText("Attribute: " + actual.getAtributo());
        nivel.setText(actual.tieneNivel() ? "Level: " + actual.getNivel() : "");
        tipo.setText("Type: " + actual.getTipo());
        rareza.setText("Rarity: " + actual.getRareza());
        ataque.setText(actual.tieneAtaque() ? "ATK: " + actual.getAtaque() : "");
        defensa.setText(actual.tieneDefensa() ? "DEF: " + actual.getDefensa() : "");
        descripcion.setText(actual.getDescripcion());
    }

    /**
     * Imagen de la carta (su nombre de archivo es su Id), o null si no está el archivo.
     */
    private Image cargarImagen(CartaYuGiOh carta) {
        String ruta = "/imagenes/cartas/" + carta.getId() + ".jpg";
        URL url = getClass().getResource(ruta);
        if (url == null) {
            return null;
        }
        return new Image(url.toExternalForm());
    }

    public void setTitulo(String texto) {
        titulo.setText(texto);
    }

    public VBox getLadoAnterior() {
        return ladoAnterior;
    }

    public VBox getLadoSiguiente() {
        return ladoSiguiente;
    }
}

