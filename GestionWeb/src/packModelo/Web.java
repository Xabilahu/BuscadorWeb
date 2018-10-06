package packModelo;
import java.util.ArrayList;
import java.util.Objects;

public class Web implements Comparable<Web>{

    private String nombre;
    private int numero;
    private ArrayList<Web> listaEnlaces;

    public Web(String pNombre, int pNumero, ArrayList<Web> pListaEnlaces){
        this.nombre = pNombre;
        this.numero = pNumero;
        this.listaEnlaces = pListaEnlaces;
    }

    public ArrayList<Web> enlacesSalientes() {
        return this.listaEnlaces;
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getNumero(){
        return this.numero;
    }

    public void setListaEnlaces(ArrayList<Web> enlaces){
        this.listaEnlaces = enlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Web web = (Web) o;
        return Objects.equals(nombre, web.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public int compareTo(Web pWeb) {
        return this.nombre.compareTo(pWeb.nombre);
    }
}
