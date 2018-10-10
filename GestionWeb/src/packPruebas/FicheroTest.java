package packPruebas;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;

import packModelo.DiccionarioPC;
import packModelo.ListaWebs;
import packModelo.Fichero;
import packModelo.Web;

public class FicheroTest {

    @org.junit.BeforeClass
    public static void setUp() throws Exception {
        Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator +"index.txt",System.getProperty("user.dir")+File.separator+"pld-arcs-1-N.txt");
        Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator +"words.txt");
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testCargarListaWeb() {
        try {
        	  BufferedReader buffer = new BufferedReader(new FileReader("index.txt"));
        	  BufferedReader bufferEnlaces = new BufferedReader(new FileReader("pld-arcs-1-N.txt"));
        	  String web;
        	  String enlaces;
        	  int longitudEnlaces;
        	  Iterator<Web> itr = ListaWebs.getListaWebs().getIterator();
        	  for( int i = 0 ;  i <= ListaWebs.getListaWebs().longitud() - 1 ; i ++) {
        		  web = buffer.readLine();
        		  web = web.split("\\s+")[0];
        		  enlaces = bufferEnlaces.readLine();
        		  longitudEnlaces = enlaces.split("\\s+").length;
        		  assertTrue(itr.next().enlacesSalientes().size() == (longitudEnlaces - 2));
        		  assertTrue(ListaWebs.getListaWebs().id2String(i).equals(web));
        	  }  	     		  
        	  buffer.close();
        	  bufferEnlaces.close();
        	  assertEquals(ListaWebs.getListaWebs().enlacesSalientes("010-golf.co.jp").size(),0);
		} catch (Exception e) {
            System.out.println("No existen los ficheros index.txt y pld-arcs-1-N.txt.");
		}
    }

    @org.junit.Test
    public void testCargarDiccionarioPC() {
        try{
            BufferedReader buffer = new BufferedReader(new FileReader("words.txt"));
            for (int i = 0; i < DiccionarioPC.getDiccionarioPC().longitud(); i++){
                assertTrue(DiccionarioPC.getDiccionarioPC().get(i).equals(buffer.readLine()));
            }
        } catch(Exception e){
            System.out.println("No existe el fichero words.txt.");
        }
    }

    @org.junit.Test
    public void testEscribirWebs() {
        Fichero.getFichero().escribirWebs("TestWebs.txt", "TestEnlaces.txt");
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("TestWebs.txt"));
            BufferedReader bufferEnlaces = new BufferedReader(new FileReader("TestEnlaces.txt"));
            String web;
            String enlaces;
            int longitudEnlaces;
            Iterator<Web> itr = ListaWebs.getListaWebs().getIterator();
            for( int i = 0 ;  i <= ListaWebs.getListaWebs().longitud() - 1 ; i ++) {
                web = buffer.readLine();
                web = web.split("\\s+")[0];
                enlaces = bufferEnlaces.readLine();
                longitudEnlaces = enlaces.split("\\s+").length;
                assertTrue(itr.next().enlacesSalientes().size() == (longitudEnlaces - 2));
                assertTrue(ListaWebs.getListaWebs().id2String(i).equals(web));
            }
            buffer.close();
            bufferEnlaces.close();
            new File(System.getProperty("user.dir") + File.separator +"TestWebs.txt").delete();
            new File(System.getProperty("user.dir") + File.separator +"TestEnlaces.txt").delete();
        } catch (Exception e) {
            System.out.println("No existen los ficheros TestWebs.txt y TestEnlaces.txt.");
        }
    }

    @org.junit.Test
    public void testEscribirDiccionarioPC() {
        Fichero.getFichero().escribirDiccionarioPC("TestPalabras.txt");
        try{
            BufferedReader buffer = new BufferedReader(new FileReader("TestPalabras.txt"));
            for (int i = 0; i < DiccionarioPC.getDiccionarioPC().longitud(); i++){
                assertTrue(DiccionarioPC.getDiccionarioPC().get(i).equals(buffer.readLine()));
            }
            new File(System.getProperty("user.dir") + File.separator +"TestPalabras.txt").delete();
        } catch(Exception e){
            System.out.println("No existe el fichero TestPalabras.txt.");
        }
    }
}