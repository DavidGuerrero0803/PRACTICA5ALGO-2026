package uabc.david.practica5algo2026.Modelo;

/**
 * Representa una variante de una lista en donde cada nodo tiene dos punteros,
 * el último nodo apunta al primero, y el primero apunta al último,
 * formando un anillo cerrado capaz de recorrer tanto hacia adelante como atrás.
 */
public class ListaDobleCircular<T> {
    private NodoDoble<T> inicio;
    private NodoDoble<T> fin;

    public ListaDobleCircular() {
        this.inicio = null;
        this.fin = null;
    }

    public void insertarInicio(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);

        if (inicio == null) {
            inicio = n;
            fin = n;
            n.setSig(inicio);
            n.setAnt(inicio);
        } else {
            n.setSig(inicio);
            inicio.setAnt(n);
            inicio = n;
            fin.setSig(inicio);
            n.setAnt(fin);
        }
    }

    public void insertarFin(T dato) {
        NodoDoble<T> n = new NodoDoble<>(dato);

        if (inicio == null) {
            inicio = n;
            fin = n;
            n.setSig(inicio);
            n.setAnt(inicio);
        } else {
            n.setSig(inicio);
            inicio.setAnt(n);
            fin.setSig(n);
            n.setAnt(fin);
            fin = n;
        }
    }

    public T eliminarInicio() {
        if (inicio == null) {
            System.out.println("Lista vacia");
            return null;
        }

        T dato = inicio.getInfo();
        if (inicio == fin) {
            inicio = null;
            fin = null;
        } else {
            fin.setSig(inicio.getSig());
            inicio = inicio.getSig();
            inicio.setAnt(fin);
        }

        return dato;
    }

    public T eliminarFin() {
        if (inicio == null) {
            System.out.println("Lista vacia");
            return null;
        }

        T dato = fin.getInfo();
        if (inicio == fin) {
            inicio = null;
            fin = null;
        } else {
            NodoDoble<T> r = fin.getAnt();
            r.setSig(inicio);
            inicio.setAnt(r);
            fin = r;
        }

        return dato;
    }

    public int contarNodos() {
        if (inicio == null) return 0;
        int contador = 0;
        NodoDoble<T> r = inicio;
        do {
            contador++;
            r = r.getSig();
        } while (r != inicio);
        return contador;
    }

    public NodoDoble<T> getInicio() {
        return inicio;
    }
}
