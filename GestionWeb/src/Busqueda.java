import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Busqueda {

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
    	String url = "";
       	boolean encontrado = false;
    	Web web = null;
		Iterator<Web> it = this.getIterator();
		while (it.hasNext() && !encontrado ){
			web = it.next();
			if(web.getNumero() == pId) {
				encontrado = true;
				url = web.getNombre();
			}
		}
	   	return url;
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
        ArrayList<String> enlacesSalientes = null;
    	boolean encontrado = false;
    	Web web = null;
		Iterator<Web> it = this.getIterator();
		while (it.hasNext() && !encontrado ){
			web = it.next();
			if(web.getNombre().equals(pNombre)) {
				encontrado = true;
				enlacesSalientes = web.enlacesSalientes();
			}
		}
    	return enlacesSalientes;
    }

    public ArrayList<String> webOrdenada() {
    	ArrayList<String> webOrdenada = listaWebs.stream().map(Web::getNombre).sorted().collect(Collectors.toCollection(ArrayList::new));
    	return null;
    }

    public ArrayList<String> word2Webs(String pPalabraClave) {        
    	ArrayList<String> word2Webs = null;
    	if(DiccionarioPC.getDiccionarioPC().existe(pPalabraClave)) {
    		word2Webs = new ArrayList<String>();
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
    	ArrayList<String> web2Words = null;
    	if(string2Id(pNombre)!= -1) {
       		web2Words = DiccionarioPC.getDiccionarioPC().web2Words(pNombre);
    	} 	    	
    	return web2Words;
    }

    public void insertarWeb(Web pWeb){
        this.listaWebs.add(pWeb);
    }

}
