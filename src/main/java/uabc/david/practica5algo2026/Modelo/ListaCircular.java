package uabc.david.practica5algo2026.Modelo;

import java.util.Comparator;

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

    public boolean estaVacia() {
        return inicio == null;
    }

    public void ordenarLista(Comparator<T> comparador) {
        if (inicio == null || inicio == fin) {
            return;
        }

        Nodo<T> i = inicio;
        do {
            Nodo<T> menor = i;
            Nodo<T> j = i.getSig();

            while (j != inicio) {
                if (comparador.compare(j.getInfo(), menor.getInfo()) < 0) {
                    menor = j;
                }
                j = j.getSig();
            }

            if (menor != i) {
                T temp = i.getInfo();
                i.setInfo(menor.getInfo());
                menor.setInfo(temp);
            }

            i = i.getSig();
        } while (i != fin);
    }

    public Nodo<T> getInicio() {
        return inicio;
    }
}
