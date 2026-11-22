package operaciones;

import modelo.Matriz;
import ui.Consola;
import ui.LectorDatos;
import ui.MatrizVista;

public final class DeterminanteMatriz2x2 extends OperacionBase {

    public DeterminanteMatriz2x2(Consola consola, MatrizVista vista, LectorDatos lector) {
        super(consola, vista, lector);
    }

    @Override
    public String titulo() {
        return "Determinante de matriz 2x2";
    }

    @Override
    protected void realizar() {
        Matriz matriz = lector.matriz(2, 2);

        consola.escribirLinea("\nMatriz ingresada:");
        mostrarMatriz(matriz);

        consola.escribirLinea(String.format("%nSu determinante es : %.2f", matriz.determinante2x2()));
    }
}
