package uabc.david.practica5algo2026.Vista;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * Esta clase representa el panel izquierdo la GUI:
 * botones de consulta, sus ComboBox/CheckBox y la bocina.
 */
public class PanelControl extends VBox {
    private Button botonTodas;
    private Button botonNivel;
    private Button botonAtributo;
    private Button botonAtaque;
    private Button botonDefensa;

    private ComboBox<Integer> comboNivel;
    private ComboBox<String> comboAtributo;
    private CheckBox checkAtaque;
    private CheckBox checkDefensa;

    private SoundImageButton botonSonido;

    public PanelControl() {
        setPadding(new Insets(26, 22, 18, 22));
        setSpacing(8);
        setPrefWidth(360);
        setMinWidth(360);

        Label titulo = new Label("YU-GI-OH!");
        titulo.getStyleClass().add("titulo");
        Label subtitulo = new Label("25th Anniversary Tin\nDueling Heroes Mega Pack");

        botonTodas = crearBoton("Show all cards");
        botonNivel = crearBoton("Show by level");
        botonAtributo = crearBoton("Show by attribute");
        botonAtaque = crearBoton("Show by attack points");
        botonDefensa = crearBoton("Show by defense points");

        comboNivel = new ComboBox<>();
        comboNivel.setPromptText("Level");
        comboNivel.setPrefWidth(110);
        comboNivel.setCursor(Cursor.HAND);

        comboAtributo = new ComboBox<>();
        comboAtributo.setPromptText("Attribute");
        comboAtributo.setPrefWidth(110);
        comboAtributo.setCursor(Cursor.HAND);

        checkAtaque = new CheckBox("Low to high");
        checkAtaque.setCursor(Cursor.HAND);
        checkDefensa = new CheckBox("Low to high");
        checkDefensa.setCursor(Cursor.HAND);

        GridPane opciones = new GridPane();
        opciones.setHgap(12);
        opciones.setVgap(22);
        opciones.add(botonTodas, 0, 0);
        opciones.add(botonNivel, 0, 1);
        opciones.add(conEtiqueta("Choose level", comboNivel), 1, 1);
        opciones.add(botonAtributo, 0, 2);
        opciones.add(conEtiqueta("Choose attribute", comboAtributo), 1, 2);
        opciones.add(botonAtaque, 0, 3);
        opciones.add(checkAtaque, 1, 3);
        opciones.add(botonDefensa, 0, 4);
        opciones.add(checkDefensa, 1, 4);

        Region separador = new Region();
        separador.setMinHeight(24);
        Region relleno = new Region();
        VBox.setVgrow(relleno, Priority.ALWAYS);

        botonSonido = new SoundImageButton();

        getChildren().addAll(titulo, subtitulo, separador, opciones, relleno, botonSonido);
    }

    private Button crearBoton(String texto) {
        Button boton = new Button(texto);
        boton.setPrefSize(190, 42);
        boton.getStyleClass().add("boton-consulta");
        boton.setCursor(Cursor.HAND);
        return boton;
    }

    private VBox conEtiqueta(String texto, ComboBox<?> combo) {
        return new VBox(2, new Label(texto), combo);
    }

    public Button getBotonTodas() {
        return botonTodas;
    }

    public Button getBotonNivel() {
        return botonNivel;
    }

    public Button getBotonAtributo() {
        return botonAtributo;
    }

    public Button getBotonAtaque() {
        return botonAtaque;
    }

    public Button getBotonDefensa() {
        return botonDefensa;
    }

    public ComboBox<Integer> getComboNivel() {
        return comboNivel;
    }

    public ComboBox<String> getComboAtributo() {
        return comboAtributo;
    }

    public CheckBox getCheckAtaque() {
        return checkAtaque;
    }

    public CheckBox getCheckDefensa() {
        return checkDefensa;
    }

    public SoundImageButton getBotonSonido() {
        return botonSonido;
    }
}

