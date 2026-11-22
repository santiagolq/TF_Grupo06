package ui;

/** Se lanza cuando el usuario escribe algo que no se puede interpretar (ej. letras donde va un número). */
public class EntradaInvalidaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EntradaInvalidaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
