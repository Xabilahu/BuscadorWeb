package packPruebas;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;

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
        try {
        	  BufferedReader buffer = new BufferedReader(new FileReader("index.txt"));                      	 
        	  String web;
        	  for( int i = 0 ;  i <= ListaWebs.getListaWebs().longitud() - 1 ; i ++) {
        		  web = buffer.readLine();
        		  web = web.split("\\s+")[0];
        		  assertTrue(ListaWebs.getListaWebs().id2String(i).equals(web));
        	  }  	     		  
        	  buffer.close();
		} catch (Exception e) {
			// TODO: handle exception
		}             
        assertEquals(ListaWebs.getListaWebs().enlacesSalientes("010-golf.co.jp").size(),0);

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