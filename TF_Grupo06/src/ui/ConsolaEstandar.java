package ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

/** Única clase que conoce Scanner y System.out; el resto del programa usa la interfaz Consola. */
public final class ConsolaEstandar implements Consola, AutoCloseable {

    private final Scanner scanner;
    private final PrintStream salida;

    public ConsolaEstandar(InputStream entrada, PrintStream salida) {
        this.scanner = new Scanner(entrada);
        this.scanner.useLocale(Locale.US); // el punto (.) es el separador decimal
        this.salida = salida;
    }

    @Override
    public void escribir(String texto) {
        salida.print(texto);
        salida.flush();
    }

    @Override
    public void escribirLinea(String texto) {
        salida.println(texto);
    }

    @Override
    public int leerEntero() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next(); // descarta el texto inválido para no entrar en un bucle infinito
            throw new EntradaInvalidaException("Se esperaba un número entero.", e);
        }
    }

    @Override
    public double leerDecimal() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.next();
            throw new EntradaInvalidaException("Se esperaba un número.", e);
        }
    }

    @Override
    public String leerPalabra() {
        return scanner.next().trim();
    }

    @Override
    public void esperarEnter() {
        scanner.nextLine(); // consume el salto de línea que dejó la lectura anterior
        scanner.nextLine(); // espera el Enter del usuario
    }

    @Override
    public void esperar(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void close() {
        scanner.close();
    }
}
