package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import operaciones.Operacion;
import ui.Ansi;
import ui.Cajas;
import ui.Consola;
import ui.EntradaInvalidaException;

/**
 * Menú principal. Se construye a partir de la lista de operaciones recibida:
 * agregar una operación nueva NO requiere modificar esta clase (Abierto/Cerrado).
 * Las opciones se numeran solas y "Salir" siempre queda al final.
 */
public final class Menu {

    private static final int PUNTOS_DE_ESPERA = 5;
    private static final long MILISEGUNDOS_POR_PUNTO = 500;

    private final Consola consola;
    private final Operacion instrucciones;
    private final List<Operacion> operaciones;

    public Menu(Consola consola, Operacion instrucciones, List<Operacion> operaciones) {
        this.consola = consola;
        this.instrucciones = instrucciones;
        this.operaciones = new ArrayList<>(operaciones);
    }

    public void iniciar() {
        consola.escribirLinea(Cajas.titulo(Ansi.AMARILLO, "CALCULADORA DE MATRICES", 38));

        boolean activo = true;
        while (activo) {
            mostrarMenu();
            consola.escribir("\n" + Ansi.ROJO + "Ingrese el número de la operación que desea realizar: " + Ansi.RESET);

            int opcion;
            try {
                opcion = consola.leerEntero();
            } catch (EntradaInvalidaException e) {
                consola.escribirLinea(Cajas.aviso(Ansi.ROJO, "¡Entrada no válida! Intente de nuevo"));
                continue;
            }

            int opcionSalir = operaciones.size() + 1;
            if (opcion == 0) {
                instrucciones.ejecutar();
            } else if (opcion >= 1 && opcion <= operaciones.size()) {
                operaciones.get(opcion - 1).ejecutar();
            } else if (opcion == opcionSalir) {
                despedirse();
                activo = false;
            } else {
                consola.escribirLinea(Cajas.aviso(Ansi.ROJO, "¡Opción no válida! Intente de nuevo"));
            }
        }
    }

    private void mostrarMenu() {
        List<String> lineasOperaciones = new ArrayList<>();
        for (int i = 0; i < operaciones.size(); i++) {
            lineasOperaciones.add((i + 1) + ". " + operaciones.get(i).titulo());
        }

        List<List<String>> secciones = new ArrayList<>();
        secciones.add(Collections.singletonList("0. " + instrucciones.titulo()));
        secciones.add(lineasOperaciones);
        secciones.add(Collections.singletonList((operaciones.size() + 1) + ". Salir"));

        consola.escribirLinea("\n" + Cajas.menu(Ansi.VERDE, "MENÚ PRINCIPAL", secciones));
    }

    private void despedirse() {
        consola.escribir(Ansi.AMARILLO + "Saliendo");
        for (int i = 0; i < PUNTOS_DE_ESPERA; i++) {
            consola.esperar(MILISEGUNDOS_POR_PUNTO);
            consola.escribir(".");
        }
        consola.escribirLinea("\n¡Hasta pronto!" + Ansi.RESET);
    }
}
