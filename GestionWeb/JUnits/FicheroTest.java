import static org.junit.Assert.*;

public class FicheroTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testCargarListaWeb() {
        Fichero.getFichero().cargarListaWeb("smallindex.txt","smallpld-arcs-1-N.txt");
        assertEquals(Busqueda.getBusqueda().longitud(),1000);
        assertEquals(Busqueda.getBusqueda().enlacesSalientes("010-golf.co.jp").get(2),"010bjzs.com");

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