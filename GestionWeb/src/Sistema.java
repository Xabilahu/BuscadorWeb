import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Sistema {

    public static void cargarDatosFichero(){
        Stopwatch stp = new Stopwatch();
        Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator +"index.txt",System.getProperty("user.dir")+File.separator+"pld-arcs-1-N.txt");
        Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator +"words.txt");
        System.out.println(stp.elapsedTime());
    }

    public static ArrayList<String> buscarWeb(){
        System.out.println("Inserte la web que quiera buscar");
        Scanner sc = new Scanner(System.in);
        String palabraClave = sc.next();
        Stopwatch stp = new Stopwatch();
        ArrayList<String> webs = Busqueda.getBusqueda().word2Webs(palabraClave);
        System.out.print("Se han encontrado las siguientes webs relacionadas:");
        for (int i = 0; i<webs.size(); i++){
            System.out.print(" " + webs.get(i));
        }
        System.out.println("\n");
        System.out.println(stp.elapsedTime());
        return webs;
    }

    public static void insertarWeb(){
        Stopwatch stp = new Stopwatch();
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
        System.out.println(stp.elapsedTime());

    }

    public static ArrayList<String> devolverEnlaces(){
        System.out.print("Introduzca la web de donde desee obtener sus enlaces: ");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        Stopwatch stp = new Stopwatch();
        ArrayList<String> lista = Busqueda.getBusqueda().enlacesSalientes(web);
        System.out.print("Se han encontrado las siguientes webs relacionadas:");
        for (int i = 0; i<lista.size(); i++){
            System.out.print(" " + lista.get(i));
        }
        System.out.println("\n");
        System.out.println(stp.elapsedTime());
        return lista;

    }

    public static void guardarEnFichero(){
        System.out.print("1. Guardar Webs y Enlaces\n2. Guardar palabras clave del Diccionario\n\nOpción: ");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        Stopwatch stp = new Stopwatch();
        switch (opcion){
            case 1: Fichero.getFichero().escribirWebs("indexEscritoPrograma.txt", "pid-arcs-1-NEscritoPrograma.txt");break;
            case 2: Fichero.getFichero().escribirDiccionarioPC("wordsEscritoPrograma.txt");break;
            default: break;
        }
        System.out.println(stp.elapsedTime());
    }

    public static ArrayList<String> listaOrdenadaWebs(){
        Stopwatch stp = new Stopwatch();
        ArrayList<String> lista = Busqueda.getBusqueda().webOrdenada();
        System.out.println(stp.elapsedTime());
        return lista;
    }

    public static void main(String[] args){
    	ArrayList<String> lista;
    	Boolean error = false;
    	int opcion;
        Scanner sc = new Scanner(System.in);
    	do {
    		if (!error) {
    			System.out.print("MENU:\n" +
	    			"1. Cargar los datos desde los ficheros.\n" +
	    			"2. Búsqueda de una página web\n" +
	    			"3. Inserción de una nueva página web\n" +
	    			"4. Devolver las páginas web enlazadas desde una web dada\n"+
	    			"5. Guardar la lista de webs en ficheros\n" +
	    			"6. Obtener una lista de póginas web ordenada alfabéticamente\n" +
	    			"7. Salir\n\n Opción: ");
    		}

    		opcion = sc.nextInt();
    		error = false;
	    	switch (opcion) {
	    		case 1: cargarDatosFichero(); break;
	    		case 2: buscarWeb(); break;
	    		case 3: insertarWeb(); break;
	    		case 4: devolverEnlaces(); break;
	    		case 5: guardarEnFichero(); break;
	    		case 6: listaOrdenadaWebs(); break;
	    		case 7: sc.close(); System.exit(0);
	    		default: System.out.println("Error, introduce una opción correcta"); error = true;
	    	}
    	}while (true);

    }
}
