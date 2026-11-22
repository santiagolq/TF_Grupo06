package modelo;

import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * Patrón Strategy: cada signo encapsula SU algoritmo.
 * Para agregar otra operación binaria basta con añadir una constante aquí;
 * no hay que tocar ningún if/else en el flujo de la calculadora.
 */
public enum Signo {
    SUMA("+", Matriz::sumar),
    RESTA("-", Matriz::restar);

    private final String simbolo;
    private final BinaryOperator<Matriz> operacion;

    Signo(String simbolo, BinaryOperator<Matriz> operacion) {
        this.simbolo = simbolo;
        this.operacion = operacion;
    }

    public String simbolo() {
        return simbolo;
    }

    public Matriz aplicar(Matriz izquierda, Matriz derecha) {
        return operacion.apply(izquierda, derecha);
    }

    public static Optional<Signo> desdeSimbolo(String texto) {
        for (Signo signo : values()) {
            if (signo.simbolo.equals(texto)) {
                return Optional.of(signo);
            }
        }
        return Optional.empty();
    }
}
