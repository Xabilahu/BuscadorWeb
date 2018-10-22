package packModelo;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Fichero {

    private static Fichero miFichero = null;

    private Fichero(){}

    public static Fichero getFichero(){
        if (miFichero == null) {
            miFichero = new Fichero();
        }
        return miFichero;
    }

    public void cargarListaWeb(String pNomWeb, String pNomEnlac){

        try{
            //Los archivos deben estar dentro de la carpeta GestionWeb
            Scanner entradaWeb = new Scanner(new FileReader(pNomWeb));
            Scanner entradaEnlac = new Scanner(new FileReader(pNomEnlac));
            Web wActual;
            String lineaWeb, lineaEnlac;
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
                wActual = new Web(spWeb[0], Integer.parseInt(spWeb[1]), listaEnlaces);
                ListaWebs.getListaWebs().insertarWeb(wActual);
            }
            entradaWeb.close();
            entradaEnlac.close();
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
            Web wActual;
            int enlac;
            Iterator<Web> itrWeb = ListaWebs.getListaWebs().getIterator();
            Iterator<Integer> itrEnlac;
            ArrayList<Integer> arrayEnlac;
            while (itrWeb.hasNext()){
                wActual = itrWeb.next();
                arrayEnlac = wActual.enlacesSalientes();
                itrEnlac = arrayEnlac.iterator();
                wrWeb.println(wActual.getNombre() + " " + wActual.getNumero());
                wrEnlac.print(wActual.getNumero() + " -->");
                while (itrEnlac.hasNext()){
                    enlac = itrEnlac.next();
                    wrEnlac.print(" " + enlac);
                }
                wrEnlac.println("");
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
