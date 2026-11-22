package operaciones;

import ui.Ansi;
import ui.Cajas;
import ui.Consola;
import ui.MatrizVista;

/** Muestra cómo usar la calculadora. No hereda de OperacionBase porque no lee datos ni pide pausa. */
public final class VerInstrucciones implements Operacion {

    private final Consola consola;
    private final MatrizVista vista;

    public VerInstrucciones(Consola consola, MatrizVista vista) {
        this.consola = consola;
        this.vista = vista;
    }

    @Override
    public String titulo() {
        return "Ver instrucciones";
    }

    @Override
    public void ejecutar() {
        consola.escribirLinea(Cajas.titulo(Ansi.AMARILLO, "INSTRUCCIONES DE USO", 38));
        consola.escribirLinea("1. Seleccione una operación del menú principal.");
        consola.escribirLinea("2. Tome en cuenta las condiciones de la operación seleccionada.");
        consola.escribirLinea("3. Ingrese las dimensiones de la matriz (filas , columnas).");
        consola.escribirLinea("4. Ingrese los elementos de la matriz cuando se le solicite.");
        consola.escribirLinea(Cajas.aviso(Ansi.CIAN,
                "Nota: Estos valores deben ser numéricos, y se debe utilizar el punto (.) como separador decimal"));

        consola.escribirLinea("   La siguiente matriz muestra la posición de los elementos (i,j) de la matriz:");
        consola.escribirLineas(vista.dibujarCeldas(posiciones(3, 3)));

        consola.escribirLinea();
        consola.escribirLinea("5. Siga las instrucciones en pantalla para completar la operación.");
        consola.escribirLinea("6. Para salir de la calculadora, seleccione la opción \"Salir\" del menú principal.");
    }

    private String[][] posiciones(int filas, int columnas) {
        String[][] celdas = new String[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j] = i + "," + j;
            }
        }
        return celdas;
    }
}
