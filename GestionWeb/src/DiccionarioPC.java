import java.util.ArrayList;
import java.util.Iterator;

public class DiccionarioPC {

    private static DiccionarioPC miDiccionarioPC = new DiccionarioPC();
    private ArrayList<String> listaPalabras;

    private DiccionarioPC(){
        this.listaPalabras = new ArrayList<String>();
    }

    public static DiccionarioPC getDiccionarioPC(){
        return miDiccionarioPC;
    }

    public void insertarPalabraClave(String pPalabra){
        this.listaPalabras.add(pPalabra);
    }

    public boolean existe(String pPalabra){
        return listaPalabras.contains(pPalabra);
    }

    //Hace las veces de buscarPalabras()
    public ArrayList<String> web2Words(String pNombre) {
        Iterator<String> it = this.listaPalabras.iterator();
        ArrayList<String> words = new ArrayList<String>();
        String palabraClave;
        while (it.hasNext()){
            palabraClave = it.next();
            if (pNombre.contains(palabraClave)) {
            	words.add(palabraClave);
            }
        }
        return words;
    }

    public Iterator<String> getIterator(){
        return this.listaPalabras.iterator();
    }

}
