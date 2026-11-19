package uabc.david.practica5algo2026.Modelo;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Esta clase se encarga de leer el archivo CSV de las cartas
 * y lo entrega como una lista doble circular.
 */
public class LectorCSV {

    public ListaDobleCircular<CartaYuGiOh> leerArchivo(InputStream entrada) throws IOException {
        ListaDobleCircular<CartaYuGiOh> cartas = new ListaDobleCircular<>();

        try (PushbackReader lector = new PushbackReader(
                new BufferedReader(new InputStreamReader(entrada, StandardCharsets.UTF_8)))) {

            String[] campos = new String[9];
            int indice = 0;
            StringBuilder campo = new StringBuilder();
            boolean enComillas = false;
            boolean esEncabezado = true;

            int c;
            while ((c = lector.read()) != -1) {
                char ch = (char) c;

                if (enComillas) {
                    if (ch == '"') {
                        int siguiente = lector.read();
                        if (siguiente == '"') {
                            campo.append('"');
                        } else {
                            enComillas = false;
                            if (siguiente != -1) {
                                lector.unread(siguiente);
                            }
                        }
                    } else {
                        campo.append(ch);
                    }
                } else if (ch == '"' && campo.length() == 0) {
                    enComillas = true;
                } else if (ch == ',') {
                    indice = cerrarCampo(campos, indice, campo);
                } else if (ch == '\n') {
                    indice = cerrarCampo(campos, indice, campo);
                    if (!esEncabezado) {
                        agregarCarta(cartas, campos, indice);
                    }
                    esEncabezado = false;
                    campos = new String[9];
                    indice = 0;
                } else if (ch != '\r' && ch != '﻿') {
                    campo.append(ch);
                }
            }

            if (campo.length() > 0 || indice > 0) {
                indice = cerrarCampo(campos, indice, campo);
                if (!esEncabezado) {
                    agregarCarta(cartas, campos, indice);
                }
            }
        }

        return cartas;
    }

    private int cerrarCampo(String[] campos, int indice, StringBuilder campo) {
        if (indice < campos.length) {
            campos[indice] = campo.toString();
        } else {
            campos[campos.length - 1] = campos[campos.length - 1] + "," + campo;
        }
        campo.setLength(0);
        return indice + 1;
    }

    private void agregarCarta(ListaDobleCircular<CartaYuGiOh> cartas, String[] campos, int leidos) {
        if (leidos < campos.length) {
            return;
        }

        String tipo = campos[4].trim();
        if (tipo.startsWith("[")) {
            tipo = tipo.substring(1);
        }
        if (tipo.endsWith("]")) {
            tipo = tipo.substring(0, tipo.length() - 1);
        }
        tipo = tipo.trim();

        // Las mágicas y trampas vienen sin atributo en el CSV, en el juego su
        // atributo es SPELL/TRAP, así también se pueden consultar por atributo.
        String atributo = campos[2].trim();
        if (atributo.isEmpty()) {
            if (tipo.startsWith("Spell")) {
                atributo = "SPELL";
            } else if (tipo.startsWith("Trap")) {
                atributo = "TRAP";
            }
        }

        cartas.insertarFin(new CartaYuGiOh(
                pasarAEntero(campos[0]),
                campos[1].trim(),
                atributo,
                pasarAEntero(campos[3]),
                tipo,
                pasarAEntero(campos[5]),
                pasarAEntero(campos[6]),
                campos[7].trim(),
                campos[8].trim()));
    }

    private int pasarAEntero(String texto) {
        String limpio = texto.trim();
        if (limpio.isEmpty()) {
            return -1;
        }
        try {
            return (int) Math.round(Double.parseDouble(limpio));
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
