package packModelo;
import packEstructurasEnlazadas.UnorderedCircularLinkedList;

import java.util.ArrayList;
import java.util.Iterator;

public class DiccionarioPC {

    private static DiccionarioPC miDiccionarioPC = null;
    private UnorderedCircularLinkedList<String> listaPalabras;

    private DiccionarioPC(){
        this.listaPalabras = new UnorderedCircularLinkedList<>();
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
        this.listaPalabras.addToRear(pPalabra);
    }

    public boolean existe(String pPalabra){
        return listaPalabras.contains(pPalabra);
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