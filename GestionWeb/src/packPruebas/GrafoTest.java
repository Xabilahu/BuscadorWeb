package packPruebas;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import packModelo.Fichero;
import packModelo.Grafo;
import packModelo.ListaWebs;
import packTools.Stopwatch;

import static org.junit.Assert.*;

public class GrafoTest {

    Grafo gf = new Grafo();

    @BeforeClass
    public static void setUp() throws Exception {
        Fichero.getFichero().cargarListaWeb("index.txt", "pld-arcs-1-N.txt");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testCrearGrafo() {
    }

    @Test
    public void testEstanConectados() {
    }

    @Test
    public void testEstanConectadosCamino() {
    }

    @Test
    public void testTiempo() {
        int numPruebas = 10;
        gf.crearGrafo(ListaWebs.getListaWebs());
        Stopwatch stp = new Stopwatch();
        for (int i = 0; i < numPruebas; i++) {
            int id1 = (int) (Math.random() * ListaWebs.getListaWebs().longitud());
            int id2 = (int) (Math.random() * ListaWebs.getListaWebs().longitud());
            gf.estanConectados(ListaWebs.getListaWebs().id2String(id1), ListaWebs.getListaWebs().id2String(id2));
        }
        System.out.println("Tiempo medio de cada prueba: " + (stp.elapsedTime() / numPruebas) + " segundos.");
    }
}