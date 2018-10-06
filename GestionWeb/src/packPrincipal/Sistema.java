package packPrincipal;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import packModelo.ListaWebs;
import packModelo.Fichero;
import packModelo.Web;
import packTools.Stopwatch;

public class Sistema {

    public static void cargarDatosFichero(){
        Stopwatch stp = new Stopwatch();
        Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator +"index.txt",System.getProperty("user.dir")+File.separator+"pld-arcs-1-N.txt");
        Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator +"words.txt");
        System.out.println(stp.elapsedTime());
    }

    public static ArrayList<String> buscarWeb(){
        System.out.print("Inserte la palabra clave de la web que quiera buscar: ");
        Scanner sc = new Scanner(System.in);
        String palabraClave = sc.next();
        Stopwatch stp = new Stopwatch();
        ArrayList<String> webs = ListaWebs.getListaWebs().word2Webs(palabraClave);
        System.out.print("Se han encontrado " + webs.size() + " webs relacionadas:");
        for (int i = 0; i<webs.size(); i++){
            System.out.print(" " + webs.get(i));
        }
        System.out.println("\n");
        System.out.println(stp.elapsedTime());
        return webs;
    }

    public static void insertarWeb(){
        System.out.print("Inserte el nombre de su web: ");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        int id= ListaWebs.getListaWebs().longitud();
        System.out.print("Inserte los enlaces salientes correspondientes a la web (URL), en caso de no querer insertar ninguno," +
                        "escriba -1 por pantalla: ");
        String enlace;
        int idEnlace;
        ArrayList<Web> enlaces = new ArrayList<Web>();
        do{
            enlace = sc.next();
            idEnlace = ListaWebs.getListaWebs().string2Id(enlace);
            if (!enlace.equals("-1")) {
                try {
                    if (idEnlace != -1 && !enlaces.contains(ListaWebs.getListaWebs().getWebPorIndice(idEnlace))) {
                        enlaces.add(ListaWebs.getListaWebs().getWebPorIndice(idEnlace));
                        System.out.print("URL correcta, vuelva a introducir otra: ");
                    } else if (enlaces.contains(ListaWebs.getListaWebs().getWebPorIndice(idEnlace))){
                        System.out.print("Enlace añadido previamente, prueba con otro enlace: ");
                    } else{
                        System.out.print("URL incorrecta, vuelva a introducir otra: ");
                    }
                } catch (Exception e){
                    System.out.print("URL incorrecta, vuelva a introducir otra: ");
                }
            }
        }
        while(!enlace.equals("-1"));
        Stopwatch stp = new Stopwatch();
        Web nuevaWeb = new Web(web,id,enlaces);
        ListaWebs.getListaWebs().insertarWeb(nuevaWeb);
        System.out.println(stp.elapsedTime());

    }

    public static ArrayList<String> devolverEnlaces(){
        System.out.print("Introduzca la web de donde desee obtener sus enlaces: ");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        Stopwatch stp = new Stopwatch();
        ArrayList<String> lista = ListaWebs.getListaWebs().enlacesSalientes(web);
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
        ArrayList<String> lista = ListaWebs.getListaWebs().webOrdenada();
        System.out.println(stp.elapsedTime());
        return lista;
    }

    public static ArrayList<String> buscarPalabras(){
        System.out.print("Inserte la web de la que quiera obtener palabras: ");
        Scanner sc = new Scanner(System.in);
        String palabraClave = sc.next();
        Stopwatch stp = new Stopwatch();
        ArrayList<String> words = ListaWebs.getListaWebs().web2Words(palabraClave);
        System.out.print("Se han encontrado " + words.size() + " palabras:");
        for (int i = 0; i< words.size(); i++){
            System.out.print(" " + words.get(i));
        }
        System.out.println("\n");
        System.out.println(stp.elapsedTime());
        return words;
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
	    			    "2. Busqueda de una pagina web.\n" +
	    			    "3. Insercion de una nueva pagina web.\n" +
	    			    "4. Devolver las paginas web enlazadas desde una web dada.\n"+
	    			    "5. Guardar la lista de webs en ficheros.\n" +
	    			    "6. Obtener una lista de paginas web ordenada alfabeticamente.\n" +
	    			    "7. Obtener palabras de una pagina web.\n" +
                        "8. Salir.\n\n Opcion: ");
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
                case 7: buscarPalabras(); break;
	    		case 8: sc.close(); System.exit(0);
	    		default: System.out.print("\nError, introduce una opcion correcta: "); error = true;
	    	}
    	}while (true);

    }
}
