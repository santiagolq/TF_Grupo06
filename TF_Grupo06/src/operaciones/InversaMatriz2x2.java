package operaciones;

import java.util.Optional;

import modelo.Matriz;
import ui.Ansi;
import ui.Consola;
import ui.LectorDatos;
import ui.MatrizVista;

public final class InversaMatriz2x2 extends OperacionBase {

    public InversaMatriz2x2(Consola consola, MatrizVista vista, LectorDatos lector) {
        super(consola, vista, lector);
    }

    @Override
    public String titulo() {
        return "Inversa de matriz 2x2";
    }

    @Override
    protected void realizar() {
        Matriz matriz = lector.matriz(2, 2);

        consola.escribirLinea("\nMatriz ingresada:");
        mostrarMatriz(matriz);

        Optional<Matriz> inversa = matriz.inversa2x2();
        if (!inversa.isPresent()) {
            consola.escribirLinea(Ansi.ROJO + "\n¡La matriz no tiene inversa porque su determinante es 0!" + Ansi.RESET);
            return;
        }

        consola.escribirLinea(String.format(Ansi.VERDE + "%nDeterminante: " + Ansi.RESET + "%.2f",
                matriz.determinante2x2()));
        consola.escribirLinea(Ansi.VERDE + "\nMatriz Inversa:" + Ansi.RESET);
        mostrarMatriz(inversa.get());
    }
}
