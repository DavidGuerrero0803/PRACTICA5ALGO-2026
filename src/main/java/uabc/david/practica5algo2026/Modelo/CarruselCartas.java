package uabc.david.practica5algo2026.Modelo;

/**
 * Realiza un recorrido de una lista doble circular de cartas
 * (lleva la carta que se ve al centro y su posición, avanzar usa
 * el apuntador sig del nodo y retroceder usa el apuntador ant).
 */
public class CarruselCartas {
    private NodoDoble<CartaYuGiOh> cartaActual;
    private int posicion;
    private int cartasTotales;

    public CarruselCartas(ListaDobleCircular<CartaYuGiOh> listaCartas) {
        this.cartaActual = listaCartas.getInicio();
        this.cartasTotales = listaCartas.contarNodos();
        this.posicion = (cartasTotales == 0) ? 0 : 1;
    }

    public void avanzarDeCarta() {
        if (cartasTotales > 1) {
            cartaActual = cartaActual.getSig();
            posicion = (posicion == cartasTotales) ? 1 : posicion + 1;
        }
    }

    public void retrocederCarta() {
        if (cartasTotales > 1) {
            cartaActual = cartaActual.getAnt();
            posicion = (posicion == 1) ? cartasTotales : posicion - 1;
        }
    }

    public boolean estaVacio() {
        return cartasTotales == 0;
    }

    public CartaYuGiOh getActual() {
        return cartaActual.getInfo();
    }

    public CartaYuGiOh getAnterior() {
        return cartaActual.getAnt().getInfo();
    }

    public CartaYuGiOh getSiguiente() {
        return cartaActual.getSig().getInfo();
    }

    public int getPosicion() {
        return posicion;
    }

    public int getTotal() {
        return cartasTotales;
    }

}
