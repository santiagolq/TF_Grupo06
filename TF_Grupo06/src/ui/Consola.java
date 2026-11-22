package ui;

/**
 * Abstracción de la entrada/salida. Las operaciones dependen de esta interfaz
 * y no de Scanner ni de System.out (Inversión de Dependencias), lo que permite
 * cambiar la consola o probar el programa sin teclado real.
 */
public interface Consola {

    void escribir(String texto);

    void escribirLinea(String texto);

    default void escribirLinea() {
        escribirLinea("");
    }

    default void escribirLineas(Iterable<String> lineas) {
        for (String linea : lineas) {
            escribirLinea(linea);
        }
    }

    /** @throws EntradaInvalidaException si lo escrito no es un entero. */
    int leerEntero();

    /** @throws EntradaInvalidaException si lo escrito no es un número. */
    double leerDecimal();

    String leerPalabra();

    /** Detiene el programa hasta que el usuario presione Enter. */
    void esperarEnter();

    void esperar(long milisegundos);
}
