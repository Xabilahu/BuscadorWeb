package packPruebas;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import packModelo.DiccionarioPC;
import packModelo.Fichero;
import packModelo.ListaWebs;

public class DiccionarioPCTest {

	@Before
	public void setUp() throws Exception {
		Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator +"index.txt", System.getProperty("user.dir") + File.separator +"pld-arcs-1-N.txt");
		Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator +"words.txt");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testWeb2Words() {
		ArrayList<String> listaPalabra = DiccionarioPC.getDiccionarioPC().web2Words("0-00.pl");
		ArrayList<String> palabras = new ArrayList<String>();
		assertEquals(palabras, listaPalabra);
		listaPalabra = DiccionarioPC.getDiccionarioPC().web2Words("tsunamichannel.com");
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
	}

}
