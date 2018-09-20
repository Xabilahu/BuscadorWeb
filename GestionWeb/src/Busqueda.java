import java.util.ArrayList;

public class Busqueda {

    private static Busqueda miBusqueda = new Busqueda();
    private ArrayList<Web> listaWebs;

    private Busqueda(){
        this.listaWebs = new ArrayList<Web>();
    }

    public static Busqueda getBusqueda(){
        return miBusqueda;
    }

    public String id2String(int pId) {
        return "";
    }

    public int string2Id(String pNombre) {
        return 0;
    }

    public ArrayList<String> enlacesSalientes(String pNombre) {
        return new ArrayList<String>();
    }

    public ArrayList<String> webOrdenada(String pNombre) {
        return new ArrayList<String>();
    }

    public ArrayList<String> word2Webs(String pPalabraClave) {
        return new ArrayList<String>();
    }

    public ArrayList<String> web2Words(String pNombre) {
        return new ArrayList<String>();
    }

    public void insertarWeb(Web pWeb){
        this.listaWebs.add(pWeb);
    }

}
