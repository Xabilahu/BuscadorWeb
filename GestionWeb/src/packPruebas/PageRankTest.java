package packPruebas;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import packModelo.Fichero;
import packModelo.PageRank;
import packModelo.Par;

import java.io.File;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

public class PageRankTest {

    private PageRank pg;

    @Before
    public void setUp() throws Exception {
        Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator + "/src/ficherosPrueba/websPageRankMin.txt", System.getProperty("user.dir") + File.separator + "/src/ficherosPrueba/enlacPageRankMin.txt");
        this.pg = PageRank.getPageRank();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testPageRank() {
        HashMap<String,Double> resultado = new HashMap<>();
        resultado.put("a1", 0.12686953125000003);
        resultado.put("b1", 0.04812500000000001);
        resultado.put("c01", 0.06857812500000002);
        resultado.put("d0", 0.037500000000000006);
        assertEquals(resultado, this.pg.pageRank());
    }

    @Test
    public void testBuscar() {
        Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator + "/src/ficherosPrueba/dicPageRankMin.txt");
        Par p1 = new Par("a1", 0.12686953125000003);
        Par p2 = new Par("b1", 0.04812500000000001);
        Par p3 = new Par("c01", 0.06857812500000002);
        Par p4 = new Par("d0", 0.037500000000000006);

        for (Par p : this.pg.buscar("a"))
            assertEquals(p1, p);

        Iterator<Par> itr = this.pg.buscar("1").iterator();
        assertEquals(p1, itr.next());
        assertEquals(p3, itr.next());
        assertEquals(p2, itr.next());
        assertFalse(itr.hasNext());

        itr = this.pg.buscar("0").iterator();
        assertEquals(p3, itr.next());
        assertEquals(p4, itr.next());
        assertFalse(itr.hasNext());
    }
}