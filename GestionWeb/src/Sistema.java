import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {

    public void cargarDatosFichero(){
        Fichero.getFichero().cargarListaWeb("smallindex.txt","smallpld-arcs-1-N.txt");
        Fichero.getFichero().cargarDiccionarioPC("words.txt");
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
        System.out.print("Inserte el nombre de su web: ");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        int id=Busqueda.getBusqueda().longitud();
        System.out.print("Inserte los enlaces salientes correspondientes a la web, en caso de no querer insertar ninguno," +
                        "escriba -1 por pantalla: ");
        int enlace;
        ArrayList<Integer> enlaces = null;
        do{
            enlace = sc.nextInt();
            if(enlace != -1) {
                enlaces.add(enlace);

            }
        }
        while(enlace != -1);
        Web nuevaWeb = new Web(web,id,enlaces);
        Busqueda.getBusqueda().insertarWeb(nuevaWeb);
        sc.close();

    }

    public ArrayList<String> devolverEnlaces(){
        System.out.print("Introduzca la web de donde desee obtener sus enlaces: ");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        sc.close();
        return Busqueda.getBusqueda().enlacesSalientes(web);

    }

    public void guardarEnFichero(){
        System.out.print("1. Guardar Webs y Enlaces\n2. Guardar palabras clave del Diccionario\n\nOpción: ");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion){
            case 1: Fichero.getFichero().escribirWebs("smallindex.txt", "smallpid-arcs-1-N.txt");break;
            case 2: Fichero.getFichero().escribirDiccionarioPC("words.txt");break;
            default: break;
        }
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
