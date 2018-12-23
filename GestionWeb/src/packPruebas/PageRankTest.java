package packPruebas;

import org.junit.*;
import org.junit.runners.MethodSorters;
import packModelo.Fichero;
import packModelo.ListaWebs;
import packModelo.PageRank;
import packModelo.Par;
import packTools.Stopwatch;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PageRankTest {

    private static PageRank pg;

    @BeforeClass
    public static void setUp() throws Exception {
        Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator + "/src/ficherosPrueba/websPageRankMin.txt", System.getProperty("user.dir") + File.separator + "/src/ficherosPrueba/enlacPageRankMin.txt");
        pg = PageRank.getPageRank();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test1PageRank() {
        HashMap<String,Double> resultado = new HashMap<>();
        resultado.put("a1", 0.12686953125000003);
        resultado.put("b1", 0.04812500000000001);
        resultado.put("c01", 0.06857812500000002);
        resultado.put("d0", 0.037500000000000006);
        assertEquals(resultado, pg.pageRank());
    }

    @Test
    public void test2Buscar() {
        Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator + "/src/ficherosPrueba/dicPageRankMin.txt");
        Par p1 = new Par("a1", 0.12686953125000003);
        Par p2 = new Par("b1", 0.04812500000000001);
        Par p3 = new Par("c01", 0.06857812500000002);
        Par p4 = new Par("d0", 0.037500000000000006);

        for (Par p : pg.buscar("a"))
            assertEquals(p1, p);

        Iterator<Par> itr = pg.buscar("1").iterator();
        assertEquals(p1, itr.next());
        assertEquals(p3, itr.next());
        assertEquals(p2, itr.next());
        assertFalse(itr.hasNext());

        itr = pg.buscar("0").iterator();
        assertEquals(p3, itr.next());
        assertEquals(p4, itr.next());
        assertFalse(itr.hasNext());

        itr = pg.buscar("0", "1").iterator();
        assertEquals(p3, itr.next());
        assertFalse(itr.hasNext());
    }

    @Test
    public void test3FicheroGrande() {
        ListaWebs.getListaWebs().reset();
        Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator + "index.txt", System.getProperty("user.dir") + File.separator + "pld-arcs-1-N.txt");
        Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator + "words.txt");

        Stopwatch stp = new Stopwatch();
        PageRank.getPageRank().reset();
        pg = PageRank.getPageRank();
        double timePageRank = stp.elapsedTime();

        Iterable<Par> search = pg.buscar("face");
        System.out.println("Tiempo transcurrido en generar los PageRank: " + timePageRank + " segundos.");
        System.out.println("Tiempo transcurrido en hacer la busqueda por PageRank de \"book\": " + (stp.elapsedTime() - timePageRank) + " segundos.\n\n\n");

        if (search == null) System.out.println("No se ha encontrado ninguna web en la busqueda o la palabra introducida no existe en el diccionario.");
        else {
            for (Par p : search) {
                System.out.println(p.toString());
            }
        }
    }

}