import java.util.ArrayList;

public class DiccionarioPC {

    private static DiccionarioPC miDiccionarioPC = new DiccionarioPC();
    private ArrayList<String> listaPalabras;

    private DiccionarioPC(){
        this.listaPalabras = new ArrayList<String>();
    }

    public static DiccionarioPC getDiccionarioPC(){
        return miDiccionarioPC;
    }

    public String buscarPalabra(String pPalabra, String pAlgo){
        return "";
        //TODO recordar por qué hay dos parámetros
    }

    public void insertarPalabraClave(String pPalabra){
        this.listaPalabras.add(pPalabra);
    }

    //el método existe será implementado mediante el metodo contains(), que no sabemos su eficiencia (su coste)

}
