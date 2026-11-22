package ui;

import modelo.Matriz;

import static ui.Ansi.ROJO;

/**
 * Pide y valida datos al usuario. Concentra los bucles try/catch que antes
 * estaban copiados y pegados en SumaResta y ProductoPorEscalar.
 */
public final class LectorDatos {

    private final Consola consola;

    public LectorDatos(Consola consola) {
        this.consola = consola;
    }

    public int filas() {
        return enteroPositivo("Ingrese número de filas: ", "filas");
    }

    public int columnas() {
        return enteroPositivo("Ingrese número de columnas: ", "columnas");
    }

    public int enteroPositivo(String mensaje, String nombre) {
        while (true) {
            consola.escribir(mensaje);
            try {
                int valor = consola.leerEntero();
                if (valor > 0) {
                    return valor;
                }
                consola.escribirLinea(Cajas.aviso(ROJO, "¡Error! El número de " + nombre + " debe ser positivo."));
            } catch (EntradaInvalidaException e) {
                consola.escribirLinea(Cajas.aviso(ROJO, "¡Entrada no válida! Debe ingresar un número entero."));
            }
        }
    }

    public double decimal(String mensaje) {
        while (true) {
            consola.escribir(mensaje);
            try {
                return consola.leerDecimal();
            } catch (EntradaInvalidaException e) {
                consola.escribirLinea(Cajas.aviso(ROJO, "¡Entrada no válida! Debe ingresar un número válido."));
            }
        }
    }

    public Matriz matriz(int filas, int columnas) {
        double[][] datos = new double[filas][columnas];
        consola.escribirLinea("Ingrese los elementos de la matriz");
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                datos[i][j] = decimal(String.format("Elemento [%d,%d]: ", i + 1, j + 1));
            }
        }
        return new Matriz(datos);
    }
}
