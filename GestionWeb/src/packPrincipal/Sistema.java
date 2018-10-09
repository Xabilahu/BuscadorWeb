package packPrincipal;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import packModelo.ListaWebs;
import packModelo.Fichero;
import packModelo.Web;
import packTools.Stopwatch;

public class Sistema {

    public void cargarDatosFichero(){
        Stopwatch stp = new Stopwatch();
        Fichero.getFichero().cargarListaWeb(System.getProperty("user.dir") + File.separator +"index.txt",System.getProperty("user.dir")+File.separator+"pld-arcs-1-N.txt");
        Fichero.getFichero().cargarDiccionarioPC(System.getProperty("user.dir") + File.separator +"words.txt");
        System.out.println("\nLa ejecucion ha tardado " + stp.elapsedTime() + " segundos.\n\n");
    }

    public static ArrayList<String> buscarWeb(){
        System.out.print("\nInserte la palabra clave de la web que quiera buscar: ");
        Scanner sc = new Scanner(System.in);
        String palabraClave = sc.next();
        Stopwatch stp = new Stopwatch();
        ArrayList<String> webs = ListaWebs.getListaWebs().word2Webs(palabraClave.toLowerCase());
        if (webs != null) {
            System.out.print("\nSe han encontrado " + webs.size() + " webs relacionadas:");
            for (int i = 0; i < webs.size(); i++) {
                System.out.print(" " + webs.get(i));
            }
            System.out.println("\n");
        } else System.out.println("\nNo existe la palabra en el Diccionario.\n");
        System.out.println("La ejecucion ha tardado " + stp.elapsedTime() + " segundos.\n\n");
        return webs;
    }

    public static void insertarWeb(){
        System.out.print("\nIntroduzca la URL de la web que quiera añadir: ");
        Scanner sc = new Scanner(System.in);
        String web;
        boolean existingWeb;
        do {
            web = sc.next();
            existingWeb = ListaWebs.getListaWebs().string2Id(web.toLowerCase()) != -1;
            if (existingWeb) System.out.print("\nLa web introducida ya existe. Prueba con otra URL: ");
        } while (existingWeb);
        int id=ListaWebs.getListaWebs().longitud();
        System.out.print("\nIntroduzca la URL los enlaces salientes correspondientes a su web, en caso de no querer insertar ninguno," +
                        "escriba -1 por pantalla: ");
        String enlace;
        int idEnlace;
        ArrayList<Integer> enlaces = new ArrayList<Integer>();
        do{
            enlace = sc.next();
            idEnlace = ListaWebs.getListaWebs().string2Id(enlace);
            if(!enlace.equals("-1")){
            	 if(idEnlace != -1 && !enlaces.contains(idEnlace))  {
                     enlaces.add(idEnlace);
                     System.out.print("URL correcta, vuelva a introducir otra: ");
                 } else if (idEnlace == -1){
                     System.out.print("URL incorrecta, vuelva a introducir otra: ");
                 } else if (enlaces.contains(idEnlace)){
                     System.out.print("Enlace añadido previamente, prueba con otro enlace: ");
                 }
            }
           
        } while(!enlace.equals("-1"));
        Stopwatch stp = new Stopwatch();
        Web nuevaWeb = new Web(web,id,enlaces);
        ListaWebs.getListaWebs().insertarWeb(nuevaWeb);
        System.out.println("\nLa ejecucion ha tardado " + stp.elapsedTime() + " segundos.\n\n");

    }

    public static ArrayList<String> devolverEnlaces(){
        System.out.print("\nIntroduzca la web de donde desee obtener sus enlaces: ");
        Scanner sc = new Scanner(System.in);
        String web = sc.next();
        Stopwatch stp = new Stopwatch();
        ArrayList<String> lista = ListaWebs.getListaWebs().enlacesSalientes(web);
        if (lista != null) {
            System.out.print("\nSe han encontrado las siguientes webs relacionadas:");
            for (int i = 0; i < lista.size(); i++) {
                System.out.print(" " + lista.get(i));
            }
            System.out.println("\n");
        } else System.out.println("\nLa web introducida no existe.\n");
        System.out.println("La ejecucion ha tardado " + stp.elapsedTime() + " segundos.\n\n");
        return lista;

    }

    public static void guardarEnFichero(){
        System.out.print("\n1. Guardar Webs y Enlaces\n2. Guardar palabras clave del Diccionario\n\nOpción: ");
        Scanner sc = new Scanner(System.in);
        int opcion;
        boolean correcto;
        Stopwatch stp = null;
        do {
            try {
                correcto = true;
                opcion = sc.nextInt();
                stp = new Stopwatch();
                switch (opcion) {
                    case 1:
                        Fichero.getFichero().escribirWebs("indexEscritoPrograma.txt", "pid-arcs-1-NEscritoPrograma.txt");
                        break;
                    case 2:
                        Fichero.getFichero().escribirDiccionarioPC("wordsEscritoPrograma.txt");
                        break;
                    default:
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.print("\nOpcion incorrecta, vuelva a introducir una opcion: ");
                sc = new Scanner(System.in);
                correcto = false;
            }
        } while (!correcto);
        System.out.println("\nLa ejecucion ha tardado " + stp.elapsedTime() + " segundos.\n\n");
    }

    public static ArrayList<String> listaOrdenadaWebs(){
        Stopwatch stp = new Stopwatch();
        ArrayList<String> lista = ListaWebs.getListaWebs().webOrdenada();
        System.out.println("\nLa ejecucion ha tardado " + stp.elapsedTime() + " segundos.\n\n");
        return lista;
    }

    public static ArrayList<String> buscarPalabras(){
        System.out.print("\nIntroduzca la URL de la web de la que quiera obtener palabras: ");
        Scanner sc = new Scanner(System.in);
        String palabraClave = sc.next();
        Stopwatch stp = new Stopwatch();
        ArrayList<String> words = ListaWebs.getListaWebs().web2Words(palabraClave.toLowerCase());
        if (words != null){
            System.out.print("\nSe han encontrado " + words.size() + " palabras:");
            for (int i = 0; i< words.size(); i++){
                System.out.print(" " + words.get(i));
            }
            System.out.println("\n");
        } else System.out.println("\nLa web introducida no existe.\n");
        System.out.println("La ejecucion ha tardado " + stp.elapsedTime() + " segundos.\n\n");
        return words;
    }

    public static void main(String[] args){
    	ArrayList<String> lista;
    	boolean error = false;
    	boolean primeraVez = true;
    	int opcion;
        Scanner sc = new Scanner(System.in);
    	do {
    		if (!error) {
    			System.out.print("MENU:\n" +
	    			"1. Cargar los datos desde los ficheros.\n" +
	    			"2. Busqueda de una pagina web\n" +
	    			"3. Insercion de una nueva pagina web\n" +
	    			"4. Devolver las paginas web enlazadas desde una web dada\n"+
	    			"5. Guardar la lista de webs en ficheros\n" +
	    			"6. Obtener una lista de paginas web ordenada alfabeticamente\n" +
	    			"7. Obtener palabras de una pagina web.\n" +
                    "8. Salir\n\n Opcion: ");
    		}
            try {
                opcion = sc.nextInt();
                error = false;
                switch (opcion) {
                    case 1:
                        new Sistema().cargarDatosFichero();
                        primeraVez = false;
                        break;
                    case 2:
                        if (!primeraVez) buscarWeb();
                        break;
                    case 3:
                        if (!primeraVez) insertarWeb();
                        break;
                    case 4:
                        if (!primeraVez) devolverEnlaces();
                        break;
                    case 5:
                        if (!primeraVez) guardarEnFichero();
                        break;
                    case 6:
                        if (!primeraVez) listaOrdenadaWebs();
                        break;
                    case 7:
                        if (!primeraVez) buscarPalabras();
                        break;
                    case 8:
                        sc.close();
                        System.exit(0);
                    default:
                        System.out.println("Error, introduce una opcion correcta");
                        error = true;
                }
                if (primeraVez) System.out.println("\n\nPrimero debes cargar los datos desde los ficheros.\n\n");
            } catch (InputMismatchException e){
                System.out.println("\n\nOpcion incorrecta, vuelva a introducir una opcion.\n\n");
                sc = new Scanner(System.in);
            }
    	}while (true);

    }
}