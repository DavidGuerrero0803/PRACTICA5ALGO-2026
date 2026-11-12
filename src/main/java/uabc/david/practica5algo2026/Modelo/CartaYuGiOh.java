package uabc.david.practica5algo2026.Modelo;

/**
 * Esta clase modela una carta de Yu-Gi-Oh!, la cual tiene las siguientes características:
 * Id, Nombre, Atributo, Nivel, Tipo, Ataque, Defensa, Rareza y Descripción.
 * Los valores numéricos que no aplican se guardan como -1.
 */
public class CartaYuGiOh {
    private int id;
    private String nombre;
    private String atributo;
    private int nivel;
    private String tipo;
    private int ataque;
    private int defensa;
    private String rareza;
    private String descripcion;

    public CartaYuGiOh(int id, String nombre, String atributo, int nivel, String tipo,
                       int ataque, int defensa, String rareza, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.atributo = atributo;
        this.nivel = nivel;
        this.tipo = tipo;
        this.ataque = ataque;
        this.defensa = defensa;
        this.rareza = rareza;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAtributo() {
        return atributo;
    }

    public int getNivel() {
        return nivel;
    }

    public String getTipo() {
        return tipo;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefensa() {
        return defensa;
    }

    public String getRareza() {
        return rareza;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean tieneNivel() {
        return nivel >= 0;
    }

    public boolean tieneAtaque() {
        return ataque >= 0;
    }

    public boolean tieneDefensa() {
        return defensa >= 0;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
