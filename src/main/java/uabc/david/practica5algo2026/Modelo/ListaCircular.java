package uabc.david.practica5algo2026.Modelo;

/**
 * Esta clase representa una Lista Circular simple,
 * en donde el último nodo no apunta a null, sino que
 * apunta de vuelta al primer nodo, formando un círculo cerrado.
 */
public class ListaCircular<T> {
    private Nodo<T> inicio;
    private Nodo<T> fin;

    public ListaCircular() {
        this.inicio = null;
        this.fin = null;
    }

    public void insertarInicio(T dato) {
        Nodo<T> n = new Nodo<>(dato, null);
        if (inicio == null) {
            inicio = n;
            fin = n;
            n.setSig(inicio);
        } else {
            n.setSig(inicio);
            inicio = n;
            fin.setSig(inicio);
        }
    }

    public void insertarFin(T dato) {
        Nodo<T> n = new Nodo<>(dato, null);
        if (inicio == null) {
            inicio = n;
            fin = n;
            n.setSig(inicio);
        } else {
            n.setSig(inicio);
            fin.setSig(n);
            fin = n;
        }
    }

    public T eliminarInicio() {
        if (inicio == null) {
            return null;
        }

        T dato = inicio.getInfo();
        if (inicio == fin) {
            inicio = null;
            fin = null;
        } else {
            fin.setSig(inicio.getSig());
            inicio = inicio.getSig();
        }

        return dato;
    }

    public T eliminarFin() {
        if (inicio == null) {
            return null;
        }

        T dato = fin.getInfo();
        if (inicio == fin) {
            inicio = null;
            fin = null;
        } else {
            Nodo<T> r = inicio;
            while (r.getSig() != fin) {
                r = r.getSig();
            }
            fin = r;
            fin.setSig(inicio);
        }

        return dato;
    }
}
