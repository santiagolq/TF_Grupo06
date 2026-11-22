package operaciones;

import java.util.Locale;

import modelo.Matriz;
import ui.Ansi;
import ui.Cajas;
import ui.Consola;
import ui.LectorDatos;
import ui.MatrizVista;

/**
 * Patrón Template Method: ejecutar() fija el esqueleto común a toda operación
 * (banner → lógica propia → pausa) y las subclases solo implementan realizar().
 * Así el banner y la pausa dejan de estar copiados en cada método.
 */
public abstract class OperacionBase implements Operacion {

    protected static final int ANCHO_TITULO = 36;

    protected final Consola consola;
    protected final MatrizVista vista;
    protected final LectorDatos lector;

    protected OperacionBase(Consola consola, MatrizVista vista, LectorDatos lector) {
        this.consola = consola;
        this.vista = vista;
        this.lector = lector;
    }

    @Override
    public final void ejecutar() {
        consola.escribirLinea();
        consola.escribirLinea(Cajas.titulo(Ansi.VERDE, titulo().toUpperCase(Locale.ROOT), ANCHO_TITULO));
        realizar();
        pausar();
    }

    /** Lógica propia de cada operación. */
    protected abstract void realizar();

    protected void mostrarTituloResultado() {
        consola.escribirLinea();
        consola.escribirLinea(Cajas.titulo(Ansi.VERDE, "RESULTADO", ANCHO_TITULO));
    }

    protected void mostrarMatriz(Matriz matriz) {
        consola.escribirLineas(vista.dibujar(matriz));
    }

    private void pausar() {
        consola.escribirLinea();
        consola.escribirLinea(Ansi.AMARILLO + "Presione Enter para continuar..." + Ansi.RESET);
        consola.esperarEnter();
    }
}
