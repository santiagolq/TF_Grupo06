package modelo;

import java.util.Optional;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;

/**
 * Matriz inmutable de números reales.
 * Contiene ÚNICAMENTE lógica matemática: no sabe nada de consola ni de colores.
 */
public final class Matriz {

    private final double[][] datos;

    public Matriz(double[][] datos) {
        if (datos == null || datos.length == 0 || datos[0].length == 0) {
            throw new IllegalArgumentException("La matriz debe tener al menos una fila y una columna.");
        }
        this.datos = new double[datos.length][];
        for (int i = 0; i < datos.length; i++) {
            if (datos[i].length != datos[0].length) {
                throw new IllegalArgumentException("Todas las filas deben tener el mismo número de columnas.");
            }
            this.datos[i] = datos[i].clone(); // copia defensiva: la matriz original no se puede alterar desde fuera
        }
    }

    public int filas() {
        return datos.length;
    }

    public int columnas() {
        return datos[0].length;
    }

    public double get(int fila, int columna) {
        return datos[fila][columna];
    }

    // ───────────── Operaciones (siempre devuelven una matriz nueva) ─────────────

    public Matriz sumar(Matriz otra) {
        return combinar(otra, (a, b) -> a + b);
    }

    public Matriz restar(Matriz otra) {
        return combinar(otra, (a, b) -> a - b);
    }

    public Matriz multiplicarPorEscalar(double escalar) {
        return transformar(x -> escalar * x);
    }

    public double determinante2x2() {
        exigir2x2();
        return datos[0][0] * datos[1][1] - datos[0][1] * datos[1][0];
    }

    /** @return la inversa, o vacío si el determinante es 0 (la matriz no es invertible). */
    public Optional<Matriz> inversa2x2() {
        double det = determinante2x2();
        if (det == 0) {
            return Optional.empty();
        }
        return Optional.of(new Matriz(new double[][] {
                { datos[1][1] / det, -datos[0][1] / det },
                { -datos[1][0] / det, datos[0][0] / det }
        }));
    }

    // ───────────── Utilitarios privados (eliminan el código duplicado) ─────────────

    private Matriz combinar(Matriz otra, DoubleBinaryOperator operador) {
        if (filas() != otra.filas() || columnas() != otra.columnas()) {
            throw new IllegalArgumentException("Las matrices deben tener las mismas dimensiones.");
        }
        double[][] r = new double[filas()][columnas()];
        for (int i = 0; i < filas(); i++) {
            for (int j = 0; j < columnas(); j++) {
                r[i][j] = operador.applyAsDouble(datos[i][j], otra.datos[i][j]);
            }
        }
        return new Matriz(r);
    }

    private Matriz transformar(DoubleUnaryOperator funcion) {
        double[][] r = new double[filas()][columnas()];
        for (int i = 0; i < filas(); i++) {
            for (int j = 0; j < columnas(); j++) {
                r[i][j] = funcion.applyAsDouble(datos[i][j]);
            }
        }
        return new Matriz(r);
    }

    private void exigir2x2() {
        if (filas() != 2 || columnas() != 2) {
            throw new IllegalStateException("Esta operación solo está definida para matrices 2x2.");
        }
    }
}
