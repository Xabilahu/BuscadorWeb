import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Busqueda{

    private static Busqueda miBusqueda = new Busqueda();
    private ArrayList<Web> listaWebs;

    private Busqueda(){
        this.listaWebs = new ArrayList<Web>();
    }

    public int longitud(){
    	return this.listaWebs.size();
	}

    public static Busqueda getBusqueda(){
        return miBusqueda;
    }
    
    public Iterator<Web> getIterator(){
    	return listaWebs.iterator();
    }

    public String id2String(int pId) {
    	return this.listaWebs.get(pId).getNombre();
    }

    public int string2Id(String pNombre) {
        int id = -1;
    	boolean encontrado = false;
    	Web web = null;
		Iterator<Web> it = this.getIterator();
		while (it.hasNext() && !encontrado ){
			web = it.next();
			if(web.getNombre().equals(pNombre)) {
				encontrado = true;
				id = web.getNumero();
			}
		}
	   	return id;
    }

    public ArrayList<String> enlacesSalientes(String pNombre) {
        ArrayList<String> enlacesSalientes = new ArrayList<String>();
        ArrayList<Integer> idEnlacesSalientes = null;
        Iterator<Integer> itr = null;
        int enlace;
    	boolean encontrado = false;
    	Web web = null;
		Iterator<Web> it = this.getIterator();
		while (it.hasNext() && !encontrado ){
			web = it.next();
			if(web.getNombre().equals(pNombre)) {
				encontrado = true;
				idEnlacesSalientes = web.enlacesSalientes();
				itr = idEnlacesSalientes.iterator();
				while (itr.hasNext()){
					enlace = itr.next();
					enlacesSalientes.add(this.id2String(enlace));
				}
			}
		}
    	return enlacesSalientes;
    }

    public ArrayList<String> webOrdenada() {
        SortAndSearch ordenar = new SortAndSearch();
        ordenar.mergeSort(this.listaWebs);
        //ordenar.quickSort(this.listaWebs); Coste O(n^2) porque las webs están ordenadas, peor caso para qs
        ArrayList<String> arrayURL = new ArrayList<String>();
        Iterator<Web> itr = this.getIterator();
        while (itr.hasNext()){
            arrayURL.add(itr.next().getNombre());
        }
        return arrayURL;
    	//return listaWebs.stream().map(Web::getNombre).sorted().collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> word2Webs(String pPalabraClave) {        
    	ArrayList<String> word2Webs = new ArrayList<String>();
    	if(DiccionarioPC.getDiccionarioPC().existe(pPalabraClave)) {    	
    		Web web = null;
			Iterator<Web> it = this.getIterator();
			while (it.hasNext()){
				web = it.next();				
				if (web.getNombre().contains(pPalabraClave)) {
					word2Webs.add(web.getNombre());
				}
			}
    	}
    	return word2Webs;
    }

    public ArrayList<String> web2Words(String pNombre) {
    	ArrayList<String> web2Words = new ArrayList<String>();
    	if(string2Id(pNombre)!= -1) {
       		web2Words = DiccionarioPC.getDiccionarioPC().web2Words(pNombre);
    	} 	    	
    	return web2Words;
    }

    public void insertarWeb(Web pWeb){
        this.listaWebs.add(pWeb);
    }

}
