package packPruebas;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import packModelo.ListaWebs;
import packModelo.Fichero;
import packModelo.Web;

public class ListaWebsTest {

	@BeforeClass
	public static void setUp() throws Exception {
		Fichero.getFichero().cargarListaWeb( System.getProperty("user.dir") + File.separator +"index.txt", System.getProperty("user.dir") + File.separator +"pld-arcs-1-N.txt");
		Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator +"words.txt");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testId2String() {
		String web = ListaWebs.getListaWebs().id2String(0);
		assertEquals(ListaWebs.getListaWebs().getIterator().next().getNombre(), web);
		web = ListaWebs.getListaWebs().id2String(ListaWebs.getListaWebs().longitud());
		assertEquals("No existen webs con ese indice", web);
		web = ListaWebs.getListaWebs().id2String(-1);
		assertEquals("No existen webs con ese indice", web);
		Web w = new Web("0--14XabiAndresAlvaro.org", ListaWebs.getListaWebs().longitud(), null);
		ListaWebs.getListaWebs().insertarWeb(w);
		String URLAnterior = ListaWebs.getListaWebs().id2String(ListaWebs.getListaWebs().longitud() - 1);
		ListaWebs.getListaWebs().webOrdenada();
		assertEquals(URLAnterior, ListaWebs.getListaWebs().id2String(ListaWebs.getListaWebs().longitud() - 1));
	}
	
	@Test
	public void testString2Id() {
	    Web w = new Web("pepitogrillo123.info", ListaWebs.getListaWebs().longitud(), null);
	    ListaWebs.getListaWebs().insertarWeb(w);
		int indice = ListaWebs.getListaWebs().string2Id("pepitogrillo123.info");
		assertEquals(ListaWebs.getListaWebs().longitud() - 1, indice);
		indice = ListaWebs.getListaWebs().string2Id("SFSSADFDG097435678.com");
		assertEquals(-1, indice);
	}
	
	@Test
	public void testEnlacesSalientes() {
		ArrayList<String> salientes = ListaWebs.getListaWebs().enlacesSalientes("0-24.ro");
		ArrayList<String> enlaces = new ArrayList<String>();
		enlaces.add("poker-ca-la-aparate.ro");
		assertEquals(enlaces, salientes);
		enlaces.clear();
		salientes = ListaWebs.getListaWebs().enlacesSalientes("0-5.co.il");
		assertEquals(enlaces, salientes);
		salientes = ListaWebs.getListaWebs().enlacesSalientes("SFSSADFDG097435678.com");
		assertNull(salientes);
	}
	
	@Test
	public void testWebOrdenada() {
		ArrayList<String> ordenada = ListaWebs.getListaWebs().webOrdenada();		
		for (int i = 1; i <= ordenada.size() - 1; i ++) {
			assertTrue(ordenada.get(i-1).compareTo(ordenada.get(i)) < 0);
		}
	}
		
	@Test
	public void testWord2Webs() {
		ArrayList<String> listaWebs = ListaWebs.getListaWebs().word2Webs("zythem");
		ArrayList<String> webs = new ArrayList<String>();
		assertEquals(webs, listaWebs);
		listaWebs = ListaWebs.getListaWebs().word2Webs("tsunamic");
		webs.add("tsunamichannel.com");
		webs.add("tsunamicockers.com");
		webs.add("tsunamicompany.com");
		assertEquals(webs, listaWebs);	
		listaWebs = ListaWebs.getListaWebs().word2Webs("tsunamic company");
		webs.clear();
		webs.add("tsunamicompany.com");
		assertEquals(webs, listaWebs);					
		listaWebs = ListaWebs.getListaWebs().word2Webs("zythemaet");
		assertNull(listaWebs);	
		listaWebs = ListaWebs.getListaWebs().word2Webs("tsunamic zythemaet");
		assertNull(listaWebs);
	}
	
	
	@Test
	public void testWeb2Words() {
		ArrayList<String> listaPalabra = ListaWebs.getListaWebs().web2Words("0-00.pl");
		ArrayList<String> palabras = new ArrayList<String>();
		assertEquals(palabras, listaPalabra);
		listaPalabra = ListaWebs.getListaWebs().web2Words("tsunamichannel.com");
		palabras.add("ami");
		palabras.add("amic");
		palabras.add("ann");
		palabras.add("anne");
		palabras.add("cha");
		palabras.add("chan");
		palabras.add("channel");
		palabras.add("com");
		palabras.add("han");
		palabras.add("ich");
		palabras.add("nam");
		palabras.add("sun");
		palabras.add("tsun");		
		palabras.add("tsunami");
		palabras.add("tsunamic");
		palabras.add("una");		
		assertEquals(palabras, listaPalabra);					
		listaPalabra = ListaWebs.getListaWebs().web2Words("SFSSADFDG097435678.com");
		assertNull(listaPalabra);	
	}	
	
}
