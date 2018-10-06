package packModelo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import packTools.SortAndSearch;

public class ListaWebs {

    private static ListaWebs miListaWebs = new ListaWebs();
    private HashMap<String, Web> listaWebs;
    private Web[] indicesWebs;
    private int primLibre;

    private ListaWebs(){
        this.listaWebs = new HashMap<String,Web>();
        this.primLibre = 0;
    }

    public void construirIndicesWebs(int x){
        this.indicesWebs = new Web[x + 1000];
    }

    public int longitud(){
    	return this.listaWebs.size();
	}

    public static ListaWebs getListaWebs(){
        return miListaWebs;
    }

    public Web getWebPorIndice(int pIndex) throws Exception{
        if (pIndex < 0 || pIndex >= primLibre){
            throw new Exception();
        } else return this.indicesWebs[pIndex];
    }

    public String id2String(int pId) throws Exception{
    	if (pId < 0 || pId >= primLibre){
    	    throw new Exception();
        } else return this.indicesWebs[pId].getNombre();
    }

    public int string2Id(String pNombre) {
        if (this.listaWebs.containsKey(pNombre)) return this.listaWebs.get(pNombre).getNumero();
	   	else return -1;
    }

    public ArrayList<String> enlacesSalientes(String pNombre) {
        Web wActual = this.listaWebs.get(pNombre);
        ArrayList<Web> enlaces = wActual.enlacesSalientes();
        ArrayList<String> arrayURL = new ArrayList<String>();
        Iterator<Web> itr = enlaces.iterator();
        while (itr.hasNext()){
            arrayURL.add(itr.next().getNombre());
        }
        return arrayURL;
    }

    public ArrayList<String> webOrdenada() {
        SortAndSearch ordenar = new SortAndSearch();
        Web[] arrayAOrdenar = this.listaWebs.values().toArray(new Web[primLibre]);
        ordenar.mergeSort(arrayAOrdenar);
        //ordenar.quickSort(this.listaWebs); Coste O(n^2) porque las webs están ordenadas, peor caso para qs
        ArrayList<String> arrayURL = new ArrayList<String>();
        int i = 0;
        while (i<arrayAOrdenar.length){
            arrayURL.add(arrayAOrdenar[i].getNombre());
            i++;
        }
        return arrayURL;
    }

    public ArrayList<String> word2Webs(String pPalabraClave) {
    	ArrayList<String> word2Webs = new ArrayList<String>();
    	if(DiccionarioPC.getDiccionarioPC().existe(pPalabraClave)) {
    		Web web = null;
			int cont = 0;
			while (cont < this.primLibre){
				web = this.indicesWebs[cont];
				if (web.getNombre().contains(pPalabraClave)) {
					word2Webs.add(web.getNombre());
				}
				cont++;
			}
    	}
    	return word2Webs;
    }

    public ArrayList<String> web2Words(String pNombre) {
    	ArrayList<String> web2Words = new ArrayList<String>();
    	if(this.listaWebs.containsKey(pNombre)) {
       		web2Words = DiccionarioPC.getDiccionarioPC().web2Words(pNombre);
    	}
    	return web2Words;
    }

    public void insertarWeb(Web pWeb){
        this.listaWebs.put(pWeb.getNombre(), pWeb);
        this.indicesWebs[this.primLibre] = pWeb;
        this.primLibre++;
    }

}
