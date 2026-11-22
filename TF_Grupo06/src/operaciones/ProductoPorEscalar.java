package operaciones;

import modelo.Matriz;
import ui.Consola;
import ui.LectorDatos;
import ui.MatrizVista;

public final class ProductoPorEscalar extends OperacionBase {

    public ProductoPorEscalar(Consola consola, MatrizVista vista, LectorDatos lector) {
        super(consola, vista, lector);
    }

    @Override
    public String titulo() {
        return "Producto por escalar";
    }

    @Override
    protected void realizar() {
        int filas = lector.filas();
        int columnas = lector.columnas();
        Matriz matriz = lector.matriz(filas, columnas);
        double escalar = lector.decimal("Ingrese el valor escalar: ");

        Matriz resultado = matriz.multiplicarPorEscalar(escalar);

        mostrarTituloResultado();
        consola.escribirLinea("El producto de la matriz por el escalar " + escalar + " es:");
        mostrarMatriz(resultado);
    }
}
