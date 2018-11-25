package packPruebas;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import packModelo.Fichero;
import packModelo.Grafo;
import packModelo.ListaWebs;
import packModelo.Web;
import packTools.Stopwatch;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GrafoTest {

    private Grafo gf = Grafo.getGrafo();
    private ListaWebs lista = ListaWebs.getListaWebs();

    @BeforeClass
    public static void setUp() throws Exception {
        Fichero.getFichero().cargarListaWeb("index.txt", "pld-arcs-1-N.txt");
    }

    @Test
    public void testCrearGrafo() {
        HashMap<String,Integer> th = this.gf.getTh();
        String[] keys = this.gf.getKeys();
        ArrayList<Integer>[] adjList = this.gf.getAdjList();
        assertNull(th);
        assertNull(keys);
        assertNull(adjList);
        this.gf.crearGrafo(this.lista);
        th = this.gf.getTh();
        keys = this.gf.getKeys();
        adjList = this.gf.getAdjList();
        assertNotNull(th);
        assertNotNull(keys);
        assertNotNull(adjList);
        Iterator<Web> itr = this.lista.getIterator();
        Web w;

        for (int i = 0; i < this.lista.longitud(); i++) {
            w = itr.next();
            assertEquals(w.getNombre(), keys[i]);
            assertEquals(th.get(w.getNombre()), (Integer) w.getNumero());
            assertEquals(w.enlacesSalientes(), adjList[i]);
        }
    }

    @Test
    public void testEstanConectados() {
        gf.crearGrafo(this.lista);
        //Test webs no existentes
        try {
            gf.estanConectados("webInexistente1.com", "webInexistente2.com");
            fail();
        } catch (IllegalArgumentException e){}
        //Test webs no conectadas
        assertFalse(gf.estanConectados("cyberpatrol.com", "0-apr-creditcards.com"));
        //Test webs conectadas cercanas
        assertTrue(gf.estanConectados("0-apr-creditcards.com", "cyberpatrol.com"));
        //Test webs conectadas lejanas
        assertTrue(gf.estanConectados("sportstat.eu", "hertzfoundation.org"));
    }

    @Test
    public void testEstanConectadosCamino() {
        gf.crearGrafo(this.lista);
        //Test webs no existentes
        try {
            gf.estanConectadosCamino("webInexistente1.com", "webInexistente2.com");
            fail();
        } catch (IllegalArgumentException e){}
        ArrayList<Integer> conexion;
        Iterator<Integer> itr;
        //Test webs no conectadas
        conexion = gf.estanConectadosCamino("cyberpatrol.com", "0-apr-creditcards.com");
        assertEquals(conexion.size(), 0);
        //Test webs conectadas cercanas
        conexion = gf.estanConectadosCamino("0-apr-creditcards.com", "cyberpatrol.com");
        //0-apr-creditcards.com -> dateforhire.com.au -> cyberpatrol.com
        assertTrue(conexion.size() > 0);
        itr = conexion.iterator();
        assertEquals(this.lista.id2String(itr.next()), "0-apr-creditcards.com");
        assertEquals(this.lista.id2String(itr.next()), "dateforhire.com.au");
        assertEquals(this.lista.id2String(itr.next()), "cyberpatrol.com");
        assertFalse(itr.hasNext());
        //Test webs conectadas lejanas
        conexion = gf.estanConectadosCamino("sportstat.eu", "hertzfoundation.org");
        //sportstat.eu -> hardcore-team.com -> mybboard.net -> regular-expressions.info -> just-great-software.com -> acetext.com -> annerykiln.co.uk -> bsrlm.org.uk -> rss.org.uk -> significancemagazine.org -> culturomics.org -> erez.com -> hertzfoundation.org
        assertTrue(conexion.size() > 0);
        itr = conexion.iterator();
        assertEquals(this.lista.id2String(itr.next()), "sportstat.eu");
        assertEquals(this.lista.id2String(itr.next()), "hardcore-team.com");
        assertEquals(this.lista.id2String(itr.next()), "mybboard.net");
        assertEquals(this.lista.id2String(itr.next()), "regular-expressions.info");
        assertEquals(this.lista.id2String(itr.next()), "just-great-software.com");
        assertEquals(this.lista.id2String(itr.next()), "acetext.com");
        assertEquals(this.lista.id2String(itr.next()), "annerykiln.co.uk");
        assertEquals(this.lista.id2String(itr.next()), "bsrlm.org.uk");
        assertEquals(this.lista.id2String(itr.next()), "rss.org.uk");
        assertEquals(this.lista.id2String(itr.next()), "significancemagazine.org");
        assertEquals(this.lista.id2String(itr.next()), "culturomics.org");
        assertEquals(this.lista.id2String(itr.next()), "erez.com");
        assertEquals(this.lista.id2String(itr.next()), "hertzfoundation.org");
        assertFalse(itr.hasNext());
    }

    /**
     * ------------WEB PROPIA--------------
     *
     *   w0 --->  w1 --->  w2 ---> w3
     *   |       ^  |       ^
     *   |       |  |       |
     *   |--> w4 -  |--> w5 ---> w6 ---> w7
     *
     *
     *   w8 ---> w9
     *   |
     *   |
     *   |--> w10
     *
     *   ----------------------------------
     */

    @Test
    public void testEstanConectadosWebPropia() {
        this.lista.reset();
        Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator + "/src/ficherosPrueba/webs.txt", System.getProperty("user.dir") + File.separator + "/src/ficherosPrueba/enlaces.txt");
        gf.crearGrafo(this.lista);
        assertTrue(gf.estanConectados("w0", "w1"));
        assertTrue(gf.estanConectados("w4", "w3"));
        assertTrue(gf.estanConectados("w4", "w7"));
        assertFalse(gf.estanConectados("w3", "w7"));
        assertFalse(gf.estanConectados("w8", "w0"));
        assertFalse(gf.estanConectados("w0", "w8"));
        assertTrue(gf.estanConectados("w8", "w10"));
        assertFalse(gf.estanConectados("w9", "w10"));
    }

    @Test
    public void testEstanConectadosWebPropiaCamino(){
        ArrayList<Integer> camino = gf.estanConectadosCamino("w0", "w1");
        Iterator<Integer> itr = camino.iterator();
        assertEquals(itr.next(), (Integer) 0);
        assertEquals(itr.next(), (Integer) 1);
        assertFalse(itr.hasNext());
        camino = gf.estanConectadosCamino("w4", "w3");
        itr = camino.iterator();
        assertEquals(itr.next(), (Integer) 4);
        assertEquals(itr.next(), (Integer) 1);
        assertEquals(itr.next(), (Integer) 2);
        assertEquals(itr.next(), (Integer) 3);
        assertFalse(itr.hasNext());
        camino = gf.estanConectadosCamino("w4", "w7");
        itr = camino.iterator();
        assertEquals(itr.next(), (Integer) 4);
        assertEquals(itr.next(), (Integer) 1);
        assertEquals(itr.next(), (Integer) 5);
        assertEquals(itr.next(), (Integer) 6);
        assertEquals(itr.next(), (Integer) 7);
        assertFalse(itr.hasNext());
        camino = gf.estanConectadosCamino("w3", "w7");
        itr = camino.iterator();
        assertFalse(itr.hasNext());
        camino = gf.estanConectadosCamino("w8", "w0");
        itr = camino.iterator();
        assertFalse(itr.hasNext());
        camino = gf.estanConectadosCamino("w0", "w8");
        itr = camino.iterator();
        assertFalse(itr.hasNext());
        camino = gf.estanConectadosCamino("w8", "w10");
        itr = camino.iterator();
        assertEquals(itr.next(), (Integer) 8);
        assertEquals(itr.next(), (Integer) 10);
        assertFalse(itr.hasNext());
        camino = gf.estanConectadosCamino("w9", "w10");
        itr = camino.iterator();
        assertFalse(itr.hasNext());
    }

    @Test
    public void testDeTiempo() {
        int numPruebas = 100;
        this.gf.crearGrafo(this.lista);
        Stopwatch stp = new Stopwatch();
        for (int i = 0; i < numPruebas; i++) {
            int id1 = (int) (Math.random() * this.lista.longitud());
            int id2 = (int) (Math.random() * this.lista.longitud());
            this.gf.estanConectados(this.lista.id2String(id1), this.lista.id2String(id2));
        }
        double elapsedTime = stp.elapsedTime();
        System.out.println("Tiempo medio de cada prueba: " + (elapsedTime / numPruebas) + " segundos.");
        System.out.println("Tiempo total de ejecución para " + numPruebas + " pruebas: " + elapsedTime + " segundos.");
    }
}