package packModelo;
import java.util.ArrayList;
import java.util.Iterator;

public class DiccionarioPC {

    private static DiccionarioPC miDiccionarioPC = null;
    private ArrayList<String> listaPalabras;

    private DiccionarioPC(){
        this.listaPalabras = new ArrayList<String>();
    }

    public static DiccionarioPC getDiccionarioPC(){
    	if (miDiccionarioPC == null) {
    		miDiccionarioPC = new DiccionarioPC();
    	}
        return miDiccionarioPC;
    }
    
    public Iterator<String> getIterator(){
        return this.listaPalabras.iterator();
    }

    public void insertarPalabraClave(String pPalabra){
        this.listaPalabras.add(pPalabra);
    }

    public boolean existe(String pPalabra){
        return listaPalabras.contains(pPalabra);
    }

    public int longitud(){
        return this.listaPalabras.size();//Sólo se usa en FicheroTest
    }

    public String get(int pIndex){
        return this.listaPalabras.get(pIndex);//Sólo se usa en FicheroTest
    }

    public ArrayList<String> web2Words(String pNombre) {
        Iterator<String> it = this.listaPalabras.iterator();
        ArrayList<String> words = new ArrayList<String>();
        String palabraClave;
        while (it.hasNext()){
            palabraClave = it.next();
            if (palabraClave.length() >= 3) {
            	 if (pNombre.contains(palabraClave)) {
                 	words.add(palabraClave);
                 }
            }           
        }
        return words;
    }   
}
