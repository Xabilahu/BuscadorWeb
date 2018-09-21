import java.util.ArrayList;

public class Web {

    private String nombre;
    private int numero;
    private ArrayList<String> listaEnlaces;

    public Web(String pNombre, int pNumero, ArrayList<String> pListaEnlaces){
        this.nombre = pNombre;
        this.numero = pNumero;
        this.listaEnlaces = pListaEnlaces;
    }

    public ArrayList<String> enlacesSalientes() {
        return this.listaEnlaces;
    }

    /*public Character buscarPrimerCaracter() {
        return ' ';
    }*/

}
