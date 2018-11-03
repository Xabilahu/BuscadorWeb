package packPruebas;

import static org.junit.Assert.*;

import packModelo.ListaWebs;
import packModelo.Fichero;

public class FicheroTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testCargarListaWeb() {
        Fichero.getFichero().cargarListaWeb("index.txt","pld-arcs-1-N.txt");
        assertEquals(ListaWebs.getListaWebs().longitud(),2039805);
        assertEquals(ListaWebs.getListaWebs().enlacesSalientes("010-golf.co.jp").get(2),"010bjzs.com");

    }

    @org.junit.Test
    public void testCargarDiccionarioPC() {
    }

    @org.junit.Test
    public void testEscribirWebs() {
    }

    @org.junit.Test
    public void testEscribirDiccionarioPC() {
    }
}