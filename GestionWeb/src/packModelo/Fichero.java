package packModelo;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Fichero {

    private static Fichero miFichero = new Fichero();

    private Fichero(){}

    public static Fichero getFichero(){
        return miFichero;
    }

    private void contarNumeroWebs(String pNom){

        try{
            BufferedReader entradaWeb = new BufferedReader(new FileReader(pNom));
            int numWebs = 0;
            while (entradaWeb.ready()){
                entradaWeb.readLine();
                numWebs++;
            }
            ListaWebs.getListaWebs().construirIndicesWebs(numWebs);
            entradaWeb.close();
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public void cargarListaWeb(String pNomWeb, String pNomEnlac){
    
        try{
            //Los archivos deben estar dentro de la carpeta GestionWeb
            Scanner entradaWeb = new Scanner(new FileReader(pNomWeb));
            Scanner entradaEnlac = new Scanner(new FileReader(pNomEnlac));
            Web wActual = null;
            this.contarNumeroWebs(pNomWeb);
            String lineaWeb, lineaEnlac;
            ArrayList<ArrayList<Integer>> estructuraTemp = new ArrayList<ArrayList<Integer>>();
            while (entradaWeb.hasNext() && entradaEnlac.hasNext()) {
                lineaWeb = entradaWeb.nextLine();
                lineaEnlac = entradaEnlac.nextLine();
                String[] spWeb = lineaWeb.split("\\s+");
                String[] spEnlac = lineaEnlac.split("\\s+");
                ArrayList<Integer> listaEnlaces = new ArrayList<Integer>();
                int i = 2;
                while (i < spEnlac.length){
                    listaEnlaces.add(Integer.parseInt(spEnlac[i]));
                    i++;
                }
                estructuraTemp.add(listaEnlaces);
                wActual = new Web(spWeb[0], Integer.parseInt(spWeb[1]), null);
                ListaWebs.getListaWebs().insertarWeb(wActual);
            }
            entradaWeb.close();
            entradaEnlac.close();
            //Rellenar los enlaces de cada Web
            Iterator<ArrayList<Integer>> itrPrincipal = estructuraTemp.iterator();
            ArrayList<Integer> actual;
            Iterator<Integer> itrParcial;
            ArrayList<Web> enlacesFinales;
            int posicion = 0;
            while (itrPrincipal.hasNext()){
                actual = itrPrincipal.next();
                itrParcial = actual.iterator();
                enlacesFinales = new ArrayList<Web>();
                try {
                    wActual = ListaWebs.getListaWebs().getWebPorIndice(posicion);
                    while (itrParcial.hasNext()) {
                        enlacesFinales.add(ListaWebs.getListaWebs().getWebPorIndice(itrParcial.next()));
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
                wActual.setListaEnlaces(enlacesFinales);
                posicion++;
            }
        }
        catch(IOException e) {e.printStackTrace();}

    }

    public void cargarDiccionarioPC(String pNomFich){
        try{
            Scanner entrada = new Scanner(new FileReader(pNomFich));
            while (entrada.hasNext()){
                DiccionarioPC.getDiccionarioPC().insertarPalabraClave(entrada.nextLine());
            }
            entrada.close();
        }
        catch (IOException e){e.printStackTrace();}

    }

    public void escribirWebs(String pNomWeb, String pNomEnlac){
        try {
            PrintWriter wrWeb = new PrintWriter(pNomWeb);
            PrintWriter wrEnlac = new PrintWriter(pNomEnlac);
            Web wActual =  null;
            Web enlac;
            int i = 0;
            Iterator<Web> itrEnlac;
            ArrayList<Web> arrayEnlac;
            while (i < ListaWebs.getListaWebs().longitud()){
                try {
                    wActual = ListaWebs.getListaWebs().getWebPorIndice(i);
                } catch (Exception e){
                    e.printStackTrace();
                }
                arrayEnlac = wActual.enlacesSalientes();
                itrEnlac = arrayEnlac.iterator();
                wrWeb.println(wActual.getNombre() + " " + wActual.getNumero());
                wrEnlac.print(wActual.getNumero() + " -->");
                while (itrEnlac.hasNext()){
                    enlac = itrEnlac.next();
                    wrEnlac.print(" " + enlac.getNumero());
                }
                wrEnlac.println("");
                i++;
            }
            wrWeb.close();
            wrEnlac.close();
        }
        catch (IOException e){e.printStackTrace();}

    }

    public void escribirDiccionarioPC(String pNomFich){
        try{
            PrintWriter wr = new PrintWriter(pNomFich);
            Iterator<String> itr = DiccionarioPC.getDiccionarioPC().getIterator();
            String palabraClave;
            while (itr.hasNext()){
                palabraClave = itr.next();
                wr.println(palabraClave);
            }
            wr.close();
        }
        catch (IOException e){e.printStackTrace();}
    }

}
