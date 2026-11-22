package ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import modelo.Matriz;
import modelo.Signo;

/**
 * Responsable ÚNICO de convertir matrices en texto.
 * Antes existían tres métodos casi idénticos (mostrarMatriz, mostrarMatrizString y
 * obtenerLineasMatriz); ahora todo parte de un solo dibujo: dibujarCeldas().
 * Esta clase no imprime: devuelve líneas, así que es fácil de reutilizar y de probar.
 */
public final class MatrizVista {

    private static final String ESPACIO_ENTRE_MATRICES = "       ";
    private static final String ESPACIO_ANTES_DEL_RESULTADO = "     ";
    private static final String SANGRIA_SIGNO = "         ";

    private static final Map<Signo, String> COLORES = Map.of(
            Signo.SUMA, Ansi.VERDE,
            Signo.RESTA, Ansi.ROJO);

    public String colorDe(Signo signo) {
        return COLORES.getOrDefault(signo, Ansi.CIAN);
    }

    /** Dibuja una matriz numérica con 2 decimales. */
    public List<String> dibujar(Matriz matriz) {
        String[][] celdas = new String[matriz.filas()][matriz.columnas()];
        for (int i = 0; i < matriz.filas(); i++) {
            for (int j = 0; j < matriz.columnas(); j++) {
                celdas[i][j] = String.format("%.2f", matriz.get(i, j));
            }
        }
        return dibujarCeldas(celdas);
    }

    /** Dibuja cualquier tabla de textos con corchetes de matriz; cada columna se ajusta a su celda más ancha. */
    public List<String> dibujarCeldas(String[][] celdas) {
        int columnas = celdas[0].length;
        int[] anchos = new int[columnas];
        for (String[] fila : celdas) {
            for (int j = 0; j < columnas; j++) {
                anchos[j] = Math.max(anchos[j], fila[j].length());
            }
        }

        int anchoInterior = 0;
        for (int ancho : anchos) {
            anchoInterior += ancho + 4;
        }
        String vacio = " ".repeat(anchoInterior);

        List<String> lineas = new ArrayList<>();
        lineas.add("┌" + vacio + "┐");
        for (String[] fila : celdas) {
            StringBuilder sb = new StringBuilder("│");
            for (int j = 0; j < columnas; j++) {
                sb.append(" ").append(String.format("%" + anchos[j] + "s", fila[j])).append("   ");
            }
            lineas.add(sb.append("│").toString());
        }
        lineas.add("└" + vacio + "┘");
        return lineas;
    }

    /** Operación en curso: matrices apiladas con su signo entre ellas. */
    public List<String> dibujarProgreso(List<Matriz> operandos, List<Signo> signos) {
        List<String> lineas = new ArrayList<>();
        for (int i = 0; i < operandos.size(); i++) {
            lineas.addAll(dibujar(operandos.get(i)));
            if (i < signos.size()) {
                Signo signo = signos.get(i);
                lineas.add(colorDe(signo) + SANGRIA_SIGNO + signo.simbolo() + Ansi.RESET);
                lineas.add("");
            }
        }
        return lineas;
    }

    /** Operación completa en horizontal:  A + B - C = Resultado. */
    public List<String> dibujarOperacionCompleta(List<Matriz> operandos, List<Signo> signos, Matriz resultado) {
        List<List<String>> bloques = new ArrayList<>();
        for (Matriz operando : operandos) {
            bloques.add(dibujar(operando));
        }
        List<String> bloqueResultado = dibujar(resultado);
        int filaCentral = operandos.get(0).filas() / 2 + 1;

        List<String> lineas = new ArrayList<>();
        for (int linea = 0; linea < bloqueResultado.size(); linea++) {
            boolean esCentral = linea == filaCentral;
            StringBuilder sb = new StringBuilder();

            for (int k = 0; k < bloques.size(); k++) {
                sb.append(bloques.get(k).get(linea));
                if (k < signos.size()) {
                    Signo signo = signos.get(k);
                    sb.append(esCentral
                            ? "   " + colorDe(signo) + signo.simbolo() + Ansi.RESET + "   "
                            : ESPACIO_ENTRE_MATRICES);
                }
            }
            sb.append(esCentral ? Ansi.AZUL + " =" + Ansi.RESET + "   " : ESPACIO_ANTES_DEL_RESULTADO);
            sb.append(bloqueResultado.get(linea));
            lineas.add(sb.toString());
        }
        return lineas;
    }
}
