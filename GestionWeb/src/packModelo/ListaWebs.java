package packModelo;
import java.util.ArrayList;
import java.util.Iterator;

import packTools.SortAndSearch;

public class ListaWebs{

    private static ListaWebs miListaWebs = null;
    private ArrayList<Web> listaWebs;

    private ListaWebs(){
        this.listaWebs = new ArrayList<Web>();
    }
    
    public static ListaWebs getListaWebs(){
    	if(miListaWebs == null) {
    		miListaWebs = new ListaWebs();
		}
        return miListaWebs;
    }   
   
    public Iterator<Web> getIterator(){
    	return listaWebs.iterator();
    }
    
    public int longitud(){
    	return this.listaWebs.size();
	}

    public String id2String(int pId) {
    	if (pId >= 0 && pId <= longitud() - 1) {
    		return this.listaWebs.get(pId).getNombre();
    	}else {
    		return "No existen webs con ese indice";
    	}
    	
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
				enlacesSalientes = new ArrayList<String>();
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
        ArrayList<Web> arrayAOrdenar = (ArrayList<Web>) this.listaWebs.clone();
        ordenar.mergeSort(arrayAOrdenar);
        //ordenar.quickSort(this.listaWebs); Coste O(n^2) porque las webs están ordenadas, peor caso para qs
        ArrayList<String> arrayURL = new ArrayList<String>();
        Iterator<Web> itr = arrayAOrdenar.iterator();
        while (itr.hasNext()){
            arrayURL.add(itr.next().getNombre());
        }
        return arrayURL;
    	//return listaWebs.stream().map(Web::getNombre).sorted().collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> word2Webs(String pPalabraClave) {
    	ArrayList<String> word2Webs = new ArrayList<String>();
    	ArrayList<String> word2WebsAux = new ArrayList<String>();
    	String[] palabrasClaves = pPalabraClave.split("\\s+");
    	Iterator<Web> itWeb;
    	Iterator<String> itString;
    	Web web;
    	String webS;
    	int count = 0;
    	while (count < palabrasClaves.length && word2Webs!= null) {
    		if(DiccionarioPC.getDiccionarioPC().existe(palabrasClaves[count])){
    			if(word2Webs.isEmpty()) {
    				itWeb = this.getIterator();
    				while (itWeb.hasNext()){
    					web = itWeb.next();
    					if (web.getNombre().contains(palabrasClaves[count])) {
    						word2Webs.add(web.getNombre());
    					}
    				}
    			}else {
    				itString = word2Webs.iterator();
    				while (itString.hasNext()){
    					webS = itString.next();
    					if (webS.contains(palabrasClaves[count])) {
    						word2WebsAux.add(webS);
    					}
    				}
    				if (!word2WebsAux.isEmpty()) {
    					word2Webs.clear();
    					for(int i = 0; i < word2WebsAux.size(); i++ ) {
    						word2Webs.add(word2WebsAux.get(i));
    					}
    					word2WebsAux.clear();
    				}
    			}
    		}else {
    			word2Webs = null;
    		}
    		count ++;
    	}
       	return word2Webs;
    }

    public ArrayList<String> web2Words(String pNombre) {
    	ArrayList<String> web2Words = null;
    	if(string2Id(pNombre)!= -1) {
    		web2Words = new ArrayList<String>();
       		web2Words = DiccionarioPC.getDiccionarioPC().web2Words(pNombre);
    	} 	    	
    	return web2Words;
    }

    public void insertarWeb(Web pWeb){
        this.listaWebs.add(pWeb);
    }

}
