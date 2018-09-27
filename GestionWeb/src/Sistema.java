import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {

    public void cargarDatosFichero(){
        //Fichero.getFichero().cargarListaWeb(nombreFicheroWebs,nombreFicheroEnlaces);
        //Fichero.getFichero().cargarDiccionarioPC(nombreFicheroDiccionario);
    }

    public ArrayList<String> buscarWeb(){
        System.out.println("Inserte la web que quiera buscar");
        Scanner sc = new Scanner(System.in);
        String palabraClave = sc.next();
        ArrayList<String> webs= Busqueda.getBusqueda().word2Webs(palabraClave);
        System.out.println("Se han encontrado las siguientes webs relacionadas:");
        sc.close();
        return webs;


    }

    public void insertarWeb(){
        System.out.println("Inserte el nombre de su web");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        //Int id=Busqueda.getBusqueda().getLengthWebs();
        System.out.println("Inserte los enlaces salientes correspondientes a la web, en caso de no querer insertar ninguno," +
                        "escriba -1 por pantalla:");
        String enlace;
        ArrayList<String> enlaces = null;
        do{
            enlace = sc.next();
            if(!enlace.equals("-1")) {
                enlaces.add(enlace);

            }
        }
        while(!enlace.equals("-1"));
        //Web nuevaWeb = new Web(web,,enlaces);
        Busqueda.getBusqueda().insertarWeb(null);
        sc.close();

    }

    public ArrayList<String> devolverEnlaces(){
        System.out.println("Introduzca la web de donde desee obtener sus enlaces");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        sc.close();
        return Busqueda.getBusqueda().enlacesSalientes(web);

    }

    public void guardarEnFichero(){

    }

    public ArrayList<String> listaOrdenadaWebs(){
        return Busqueda.getBusqueda().webOrdenada();
    }

    public static void main(String[] args){

    }
    private void imprimirWebs(ArrayList<String> pWebs){
        Iterator<String> itr=pWebs.iterator();
        String dato;
        while(itr.hasNext()){
            dato=itr.next();
            System.out.println(dato);

        }
    }
}
