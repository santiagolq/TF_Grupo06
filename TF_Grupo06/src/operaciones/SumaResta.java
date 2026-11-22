package operaciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import modelo.Matriz;
import modelo.Signo;
import ui.Ansi;
import ui.Cajas;
import ui.Consola;
import ui.LectorDatos;
import ui.MatrizVista;

public final class SumaResta extends OperacionBase {

    private static final String SIGNO_IGUAL = "=";

    public SumaResta(Consola consola, MatrizVista vista, LectorDatos lector) {
        super(consola, vista, lector);
    }

    @Override
    public String titulo() {
        return "Suma / Resta de matrices";
    }

    @Override
    protected void realizar() {
        int filas = lector.filas();
        int columnas = lector.columnas();

        List<Matriz> operandos = new ArrayList<>();
        List<Signo> signos = new ArrayList<>();

        while (true) {
            consola.escribirLinea("\n" + Ansi.CIAN + "═══ Matriz " + (operandos.size() + 1) + " ═══" + Ansi.RESET);
            operandos.add(lector.matriz(filas, columnas));
            mostrarProgreso(operandos, signos);

            Optional<Signo> signo = pedirSigno();
            if (!signo.isPresent()) { // el usuario escribió "="
                break;
            }
            signos.add(signo.get());
        }

        Matriz resultado = operandos.get(0);
        for (int i = 0; i < signos.size(); i++) {
            resultado = signos.get(i).aplicar(resultado, operandos.get(i + 1)); // Strategy: el signo sabe operar
        }

        mostrarTituloResultado();
        consola.escribirLineas(vista.dibujarOperacionCompleta(operandos, signos, resultado));
    }

    private void mostrarProgreso(List<Matriz> operandos, List<Signo> signos) {
        consola.escribirLinea("\n" + Ansi.AZUL + "══════ OPERACIÓN ACTUAL ══════" + Ansi.RESET);
        consola.escribirLineas(vista.dibujarProgreso(operandos, signos));
    }

    /** @return el signo elegido, o vacío si el usuario escribió "=" para terminar. */
    private Optional<Signo> pedirSigno() {
        while (true) {
            consola.escribir(construirPrompt());
            String texto = consola.leerPalabra();

            if (SIGNO_IGUAL.equals(texto)) {
                return Optional.empty();
            }
            Optional<Signo> signo = Signo.desdeSimbolo(texto);
            if (signo.isPresent()) {
                return signo;
            }
            consola.escribirLinea(Cajas.aviso(Ansi.ROJO, "¡Signo no válido! Intente de nuevo"));
        }
    }

    /** El prompt se arma con los signos existentes: si se agrega uno nuevo al enum, aparece solo. */
    private String construirPrompt() {
        StringBuilder sb = new StringBuilder(Ansi.AMARILLO + "Ingrese el signo ");
        for (Signo signo : Signo.values()) {
            sb.append(vista.colorDe(signo)).append("(").append(signo.simbolo()).append(")");
        }
        return sb.append(Ansi.AZUL).append("(=): ").append(Ansi.RESET).toString();
    }
}
