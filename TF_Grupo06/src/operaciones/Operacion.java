package operaciones;

/**
 * Patrón Command: cada opción del menú es un objeto con su título y su acción.
 * El menú no conoce los detalles de ninguna operación; solo las ejecuta.
 */
public interface Operacion {

    /** Texto que aparece en el menú principal. */
    String titulo();

    void ejecutar();
}
