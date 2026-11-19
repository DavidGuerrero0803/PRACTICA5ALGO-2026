package uabc.david.practica5algo2026.Controlador;

import uabc.david.practica5algo2026.Modelo.CarruselCartas;
import uabc.david.practica5algo2026.Modelo.CatalogoCartas;
import uabc.david.practica5algo2026.Vista.CarruselGUI;
import uabc.david.practica5algo2026.Vista.PanelControl;

public class ControladorCatalogo {
    private CatalogoCartas modelo;
    private PanelControl panel;
    private CarruselGUI vista;

    private CarruselCartas carrusel;

    public ControladorCatalogo(CatalogoCartas modelo, PanelControl panel, CarruselGUI vista) {
        this.modelo = modelo;
        this.panel = panel;
        this.vista = vista;

    }
}
