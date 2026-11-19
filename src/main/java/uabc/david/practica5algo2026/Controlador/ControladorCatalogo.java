package uabc.david.practica5algo2026.Controlador;

import uabc.david.practica5algo2026.Modelo.*;
import uabc.david.practica5algo2026.Vista.ReproductorMusica;
import uabc.david.practica5algo2026.Vista.CarruselGUI;
import uabc.david.practica5algo2026.Vista.PanelControl;

public class ControladorCatalogo {
    private CatalogoCartas modelo;
    private PanelControl panel;
    private CarruselGUI vista;
    private ReproductorMusica musica;
    private CarruselCartas carrusel;

    public ControladorCatalogo(CatalogoCartas modelo, PanelControl panel, CarruselGUI vista,
                               ReproductorMusica musica) {
        this.modelo = modelo;
        this.panel = panel;
        this.vista = vista;
        this.musica = musica;

        llenarCombos();
        mostrarTodas();
        conectarEventos();
    }

    private void llenarCombos() {
        ListaCircular<Integer> niveles = modelo.getNiveles();
        if (!niveles.estaVacia()) {
            Nodo<Integer> r = niveles.getInicio();
            do {
                panel.getComboNivel().getItems().add(r.getInfo());
                r = r.getSig();
            } while (r != niveles.getInicio());
        }

        ListaCircular<String> atributos = modelo.getAtributos();
        if (!atributos.estaVacia()) {
            Nodo<String> r = atributos.getInicio();
            do {
                panel.getComboAtributo().getItems().add(r.getInfo());
                r = r.getSig();
            } while (r != atributos.getInicio());
        }
    }

    private void mostrarTodas() {
        cambiarLista(modelo.getTodas(), "All cards");
    }

    private void cambiarLista(ListaDobleCircular<CartaYuGiOh> lista, String titulo) {
        carrusel = new CarruselCartas(lista);
        vista.setTitulo(titulo);
        actualizarVista();
    }

    private void actualizarVista() {
        if (!carrusel.estaVacio()) {
            vista.mostrarCarrusel(carrusel);
        }
    }

    private void mostrarPorNivel() {
        Integer nivel = panel.getComboNivel().getValue();
        if (nivel == null) {
            if (!panel.getComboNivel().getItems().isEmpty()) {
                panel.getComboNivel().getSelectionModel().selectFirst();
            }
            return;
        }
        cambiarLista(modelo.porNivel(nivel), "Level " + nivel);
    }

    private void mostrarPorAtributo() {
        String atributo = panel.getComboAtributo().getValue();
        if (atributo == null) {
            if (!panel.getComboAtributo().getItems().isEmpty()) {
                panel.getComboAtributo().getSelectionModel().selectFirst();
            }
            return;
        }
        cambiarLista(modelo.porAtributo(atributo), "Attribute: " + atributo);
    }

    private void mostrarPorAtaque() {
        boolean menorAMayor = panel.getCheckAtaque().isSelected();
        cambiarLista(modelo.porAtaque(menorAMayor),
                "Attack points: " + (menorAMayor ? "low to high" : "high to low"));
    }

    private void mostrarPorDefensa() {
        boolean menorAMayor = panel.getCheckDefensa().isSelected();
        cambiarLista(modelo.porDefensa(menorAMayor),
                "Defense points: " + (menorAMayor ? "low to high" : "high to low"));
    }

    private void conectarEventos() {
        panel.getBotonTodas().setOnAction(e -> mostrarTodas());
        panel.getBotonNivel().setOnAction(e -> mostrarPorNivel());
        panel.getBotonAtributo().setOnAction(e -> mostrarPorAtributo());
        panel.getBotonAtaque().setOnAction(e -> mostrarPorAtaque());
        panel.getBotonDefensa().setOnAction(e -> mostrarPorDefensa());

        panel.getComboNivel().setOnAction(e -> mostrarPorNivel());
        panel.getComboAtributo().setOnAction(e -> mostrarPorAtributo());
        panel.getCheckAtaque().setOnAction(e -> mostrarPorAtaque());
        panel.getCheckDefensa().setOnAction(e -> mostrarPorDefensa());

        vista.getLadoSiguiente().setOnMouseClicked(e -> siguiente());
        vista.getLadoAnterior().setOnMouseClicked(e -> anterior());

        panel.getBotonSonido().silenciadoProperty().addListener(
                (obs, antes, silenciado) ->
                        musica.setSilenciado(silenciado));
    }

    private void siguiente() {
        carrusel.avanzarDeCarta();
        actualizarVista();
    }

    private void anterior() {
        carrusel.retrocederCarta();
        actualizarVista();
    }
}
