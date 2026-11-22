import java.util.Arrays;
import java.util.List;

import app.Menu;
import operaciones.DeterminanteMatriz2x2;
import operaciones.InversaMatriz2x2;
import operaciones.Operacion;
import operaciones.ProductoPorEscalar;
import operaciones.SumaResta;
import operaciones.VerInstrucciones;
import ui.ConsolaEstandar;
import ui.LectorDatos;
import ui.MatrizVista;

/**
 * Calculadora de matrices - Trabajo Final (Grupo 06).
 *
 * Esta clase es la "raíz de composición": el único lugar donde se crean los objetos
 * y se inyectan sus dependencias. Para agregar una operación nueva solo se crea su
 * clase y se añade a la lista de abajo.
 */
public class TF_Grupo06 {

    public static void main(String[] args) {
        try (ConsolaEstandar consola = new ConsolaEstandar(System.in, System.out)) {
            MatrizVista vista = new MatrizVista();
            LectorDatos lector = new LectorDatos(consola);

            List<Operacion> operaciones = Arrays.asList(
                    new SumaResta(consola, vista, lector),
                    new ProductoPorEscalar(consola, vista, lector),
                    new DeterminanteMatriz2x2(consola, vista, lector),
                    new InversaMatriz2x2(consola, vista, lector));

            new Menu(consola, new VerInstrucciones(consola, vista), operaciones).iniciar();
        }
    }
}
