import java.util.ArrayList;

public class Web{

    private String nombre;
    private int numero;
    private ArrayList<Integer> listaEnlaces;

    public Web(String pNombre, int pNumero, ArrayList<Integer> pListaEnlaces){
        this.nombre = pNombre;
        this.numero = pNumero;
        this.listaEnlaces = pListaEnlaces;
    }

    public ArrayList<Integer> enlacesSalientes() {
        return this.listaEnlaces;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getNumero(){
        return this.numero;
    }

}
