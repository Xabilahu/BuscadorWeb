import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {

    public static void cargarDatosFichero(){
        Fichero.getFichero().cargarListaWeb("smallindex.txt","smallpld-arcs-1-N.txt");
        Fichero.getFichero().cargarDiccionarioPC("words.txt");
    }

    public static ArrayList<String> buscarWeb(){
        System.out.println("Inserte la web que quiera buscar");
        Scanner sc = new Scanner(System.in);
        String palabraClave = sc.next();
        ArrayList<String> webs= Busqueda.getBusqueda().word2Webs(palabraClave);
        System.out.println("Se han encontrado las siguientes webs relacionadas:");
        sc.close();
        return webs;


    }

    public static void insertarWeb(){
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

    public static ArrayList<String> devolverEnlaces(){
        System.out.print("Introduzca la web de donde desee obtener sus enlaces: ");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        sc.close();
        return Busqueda.getBusqueda().enlacesSalientes(web);

    }

    public static void guardarEnFichero(){
        System.out.print("1. Guardar Webs y Enlaces\n2. Guardar palabras clave del Diccionario\n\nOpciÃ³n: ");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        switch (opcion){
            case 1: Fichero.getFichero().escribirWebs("smallindex.txt", "smallpid-arcs-1-N.txt");break;
            case 2: Fichero.getFichero().escribirDiccionarioPC("words.txt");break;
            default: break;
        }
    }

    public static ArrayList<String> listaOrdenadaWebs(){
        return Busqueda.getBusqueda().webOrdenada();
    }

    public static void main(String[] args){
    	ArrayList<String> lista;
    	Boolean error = false;
    	Scanner sc = new Scanner(System.in);
    	do {
    		if (!error) {
    			System.out.print("MENU:\n" +
	    			"1. Cargar los datos desde los ficheros.\n" +
	    			"2. Búsqueda de una página web\n" + 
	    			"3. Inserción de una nueva página web\n" + 
	    			"4. Devolver las páginas web enlazadas desde una web dada\n"+
	    			"5. Guardar la lista de webs en ficheros\n" +
	    			"6. Obtener una lista de páginas web ordenada alfabéticamente\n" +
	    			"7. Salir\n\n Opción: ");
    		}	 
    		error = false;
	    	switch (sc.nextInt()) {
	    		case 1: cargarDatosFichero(); break;
	    		case 2: buscarWeb(); break;
	    		case 3: insertarWeb(); break;
	    		case 4: devolverEnlaces(); break;
	    		case 5: guardarEnFichero(); break;
	    		case 6: lista = listaOrdenadaWebs(); break;
	    		case 7: sc.close(); System.exit(0);
	    		default: System.out.println("Error, introduce una opción correcta"); error = true;
	    	}
    	}while (true);

    }
    private static void imprimirWebs(){
    	Busqueda.getBusqueda().webOrdenada();
    }
}
