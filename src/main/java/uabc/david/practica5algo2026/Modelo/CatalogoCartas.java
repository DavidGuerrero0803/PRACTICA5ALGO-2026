package uabc.david.practica5algo2026.Modelo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;

/**
 * Esta clase guarda todas las cartas y sus clasificaciones
 * (nivel, atributo, ataque y defensa).
 */
public class CatalogoCartas {
    private ListaDobleCircular<CartaYuGiOh> todasLasCartas = new ListaDobleCircular<>();
    private ListaCircular<CartaYuGiOh> ataqueOrdenados = new ListaCircular<>();
    private ListaCircular<CartaYuGiOh> defensaOrdenados = new ListaCircular<>();
    private ListaCircular<Integer> niveles = new ListaCircular<>();
    private ListaCircular<String> atributos = new ListaCircular<>();

    public void cargarArchivo(InputStream csv) throws IOException {
        todasLasCartas = new LectorCSV().leerArchivo(csv);
        clasificarCartas();
    }

    private void clasificarCartas() {
        niveles = new ListaCircular<>();
        atributos = new ListaCircular<>();
        ataqueOrdenados = new ListaCircular<>();
        defensaOrdenados = new ListaCircular<>();

        if (todasLasCartas.estaVacia()) {
            return;
        }

        NodoDoble<CartaYuGiOh> r = todasLasCartas.getInicio();
        do {
            CartaYuGiOh carta = r.getInfo();

            if (carta.tieneNivel() && !existeNivel(carta.getNivel())) {
                niveles.insertarFin(carta.getNivel());
            }
            if (!carta.getAtributo().isEmpty() && !existeAtributo(carta.getAtributo())) {
                atributos.insertarFin(carta.getAtributo());
            }
            if (carta.tieneAtaque()) {
                ataqueOrdenados.insertarFin(carta);
            }
            if (carta.tieneDefensa()) {
                defensaOrdenados.insertarFin(carta);
            }

            r = r.getSig();
        } while (r != todasLasCartas.getInicio());

        // Primero se compara el ataque, y si empatan, el nombre (por orden alfabético).
        Comparator<CartaYuGiOh> comparadorAtaque = (a, b) -> {
            if (a.getAtaque() != b.getAtaque()) {
                return Integer.compare(a.getAtaque(), b.getAtaque());
            }
            return a.getNombre().compareTo(b.getNombre());
        };

        // Igual que el anterior, pero con la defensa.
        Comparator<CartaYuGiOh> comparadorDefensa = (a, b) -> {
            if (a.getDefensa() != b.getDefensa()) {
                return Integer.compare(a.getDefensa(), b.getDefensa());
            }
            return a.getNombre().compareTo(b.getNombre());
        };

        Comparator<Integer> comparadorNivel = (a, b) -> Integer.compare(a, b);
        Comparator<String> comparadorAtributo = (a, b) -> a.compareTo(b);

        niveles.ordenarLista(comparadorNivel);
        atributos.ordenarLista(comparadorAtributo);
        ataqueOrdenados.ordenarLista(comparadorAtaque);
        defensaOrdenados.ordenarLista(comparadorDefensa);
    }

    private boolean existeNivel(int nivel) {
        if (niveles.estaVacia()) {
            return false;
        }
        Nodo<Integer> r = niveles.getInicio();
        do {
            if (r.getInfo() == nivel) {
                return true;
            }
            r = r.getSig();
        } while (r != niveles.getInicio());
        return false;
    }

    private boolean existeAtributo(String atributo) {
        if (atributos.estaVacia()) {
            return false;
        }
        Nodo<String> r = atributos.getInicio();
        do {
            if (r.getInfo().equals(atributo)) {
                return true;
            }
            r = r.getSig();
        } while (r != atributos.getInicio());
        return false;
    }
}
