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
}
