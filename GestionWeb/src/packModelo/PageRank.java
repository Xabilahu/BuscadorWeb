package packModelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import packTools.SortAndSearch;

public class PageRank {

    private HashMap<String, Double> pageRankMap;
    private static PageRank miPageRank = new PageRank();
    private static final double DAMPING_FACTOR = 0.85;
    private static final double DIFF = 0.0001;

    private PageRank() {
        ListaWebs listaWebs = ListaWebs.getListaWebs();
        this.pageRankMap = new HashMap<>();
        int numWebs = listaWebs.longitud();
        double[] nextPageRank = new double[numWebs];
        Iterator<Web> itr = listaWebs.getIterator();
        int index = 0;
        double eqFirstArg = (1-DAMPING_FACTOR)/numWebs;
        Web wActual;

        while(itr.hasNext()) {
            wActual = itr.next();
            wActual.setPageRank(1/numWebs);
            nextPageRank[index++] = eqFirstArg;
        }

        double actualDiff = Double.POSITIVE_INFINITY;
        while (actualDiff >= DIFF) {
            itr = listaWebs.getIterator();
            while (itr.hasNext()){
                wActual = itr.next();
                ArrayList<Integer> enlacSalientes = wActual.enlacesSalientes();
                int numEnlacSalientes = enlacSalientes.size();
                Iterator<Integer> itrEnlac = enlacSalientes.iterator();

                while (itrEnlac.hasNext())
                    nextPageRank[itrEnlac.next()] += DAMPING_FACTOR * (wActual.getPageRank() / numEnlacSalientes);
                
            }
            index = 0;
            itr = listaWebs.getIterator();
            actualDiff = 0.0;
            while (itr.hasNext()) {
                wActual = itr.next();
                actualDiff += Math.abs(wActual.getPageRank() - nextPageRank[index]);
                wActual.setPageRank(nextPageRank[index]);
                nextPageRank[index++] = eqFirstArg;
            }
        }

        itr = listaWebs.getIterator();
        while (itr.hasNext()) {
            wActual = itr.next();
            this.pageRankMap.put(wActual.getNombre(), wActual.getPageRank());
        }

    }

    public static PageRank getPageRank(){
        return miPageRank;
    }

    public void reset() {
        miPageRank = new PageRank();
    }

    public HashMap<String,Double> pageRank(){
        return this.pageRankMap;
    }

    public ArrayList<Par> buscar(String palabraClave) {
        ArrayList<String> websPalabra = ListaWebs.getListaWebs().word2Webs(palabraClave);
        return this.calcularParesOrdenar(websPalabra);
    }

    public ArrayList<Par> buscar(String palabraClave1, String palabraClave2) {
        ArrayList<String> websPalabra = ListaWebs.getListaWebs().word2Webs(palabraClave1 + " " + palabraClave2);
        return this.calcularParesOrdenar(websPalabra);
    }

    private ArrayList<Par> calcularParesOrdenar(ArrayList<String> pWebs) {
        ArrayList<Par> pares = new ArrayList<>();
        Iterator<String> itr = pWebs.iterator();

        while (itr.hasNext()) {
            String wActual = itr.next();
            Par pActual = new Par(wActual, this.pageRankMap.get(wActual));
            pares.add(pActual);
        }

        SortAndSearch<Par> sort = new SortAndSearch<>();
        sort.mergeSort(pares);

        return pares;
    }

}