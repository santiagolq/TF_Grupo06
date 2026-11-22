package ui;

import java.util.List;

import static ui.Ansi.RESET;

/**
 * Dibuja los recuadros de texto. Reemplaza las decenas de println con bordes
 * escritos a mano (que además quedaban desalineados): el ancho se calcula solo.
 */
public final class Cajas {

    private Cajas() {
    }

    /** Recuadro de doble línea con el texto centrado (títulos y banners). */
    public static String titulo(String color, String texto, int anchoInterior) {
        int ancho = Math.max(anchoInterior, texto.length() + 2);
        String horizontal = "═".repeat(ancho);
        return color + "╔" + horizontal + "╗\n"
                + "║" + centrar(texto, ancho) + "║\n"
                + "╚" + horizontal + "╝" + RESET;
    }

    /** Recuadro de una línea ajustado al texto (avisos y errores). */
    public static String aviso(String color, String texto) {
        String horizontal = "─".repeat(texto.length() + 2);
        return color + "┌" + horizontal + "┐\n"
                + "│ " + texto + " │\n"
                + "└" + horizontal + "┘" + RESET;
    }

    /** Menú con un título y secciones separadas por líneas. */
    public static String menu(String color, String titulo, List<List<String>> secciones) {
        int ancho = Math.max(36, titulo.length() + 2);
        for (List<String> seccion : secciones) {
            for (String linea : seccion) {
                ancho = Math.max(ancho, linea.length() + 2);
            }
        }

        String horizontal = "─".repeat(ancho);
        StringBuilder sb = new StringBuilder(color);
        sb.append("┌").append(horizontal).append("┐\n");
        sb.append("│").append(centrar(titulo, ancho)).append("│\n");
        for (List<String> seccion : secciones) {
            sb.append("├").append(horizontal).append("┤\n");
            for (String linea : seccion) {
                sb.append("│ ").append(rellenarDerecha(linea, ancho - 1)).append("│\n");
            }
        }
        sb.append("└").append(horizontal).append("┘").append(RESET);
        return sb.toString();
    }

    private static String centrar(String texto, int ancho) {
        int izquierda = (ancho - texto.length()) / 2;
        int derecha = ancho - texto.length() - izquierda;
        return " ".repeat(izquierda) + texto + " ".repeat(derecha);
    }

    private static String rellenarDerecha(String texto, int ancho) {
        return texto + " ".repeat(Math.max(0, ancho - texto.length()));
    }
}
