package packModelo;

import java.util.*;

public class Grafo {

    HashMap<String, Integer> th;
    String[] keys;
    ArrayList<Integer>[] adjList;

    public void crearGrafo(ListaWebs lista){
        // Post: crea el grafo desde la lista de webs
        //       Los nodos son nombres de webs


        // Paso 1: llenar “th”
        th = new HashMap<String,Integer>(lista.longitud());
        Iterator<Web> itr = lista.getIterator();
        Web wActual;
        while (itr.hasNext()) {
            wActual = itr.next();
            th.put(wActual.getNombre(), wActual.getNumero());
        }

        // Paso 2: llenar “keys”
        keys = new String[th.size()];
        for (String k: th.keySet()) keys[th.get(k)] = k;

        // Paso 3: llenar “adjList”
        itr = lista.getIterator();
        adjList = (ArrayList<Integer>[]) new ArrayList[th.size()];
        for (int i = 0; i < adjList.length; i++) {
            adjList[i] = itr.next().enlacesSalientes();
        }
    }

    public void print(){
        for (int i = 0; i < adjList.length; i++){
            System.out.print("Element: " + i + " " + keys[i] + " --> ");
            for (int k: adjList[i])  System.out.print(keys[k] + " ### ");

            System.out.println();
        }
    }

    public boolean estanConectados(String a1, String a2){
    //Pre: a1 y a2 son webs existentes
        Queue<Integer> porExaminar = new LinkedList<Integer>();

        int comienzo = th.get(a1);
        int fin = th.get(a2);
        boolean enc = false;
        boolean[] examinados = new boolean[th.size()];

        porExaminar.offer(comienzo);
        examinados[comienzo] = true;
        int nodoActual;
        ArrayList<Integer> adyacentes;
        Iterator<Integer> itr;

        while (!enc && !porExaminar.isEmpty()) {
            nodoActual = porExaminar.remove();
            if (nodoActual == fin) enc = true;
            else {
                adyacentes = this.adjList[nodoActual];
                itr = adyacentes.iterator();
                while (itr.hasNext()){
                    int adj = itr.next();
                    if (!examinados[adj]) {
                        porExaminar.offer(adj);
                        examinados[adj] = true;
                    }
                }
            }
        }
        return enc;
    }

    public ArrayList<Integer> estanConectadosCamino(String a1, String a2) {
        //Pre: a1 y a2 son webs existentes
        ArrayList<Integer> resultado = new ArrayList<>();

        int[] backPointers = new int[this.adjList.length];
        backPointers[this.th.get(a1)] = -1;

        Queue<Integer> porExaminar = new LinkedList<Integer>();

        int comienzo = th.get(a1);
        int fin = th.get(a2);
        boolean enc = false;
        boolean[] examinados = new boolean[th.size()];

        porExaminar.offer(comienzo);
        examinados[comienzo] = true;
        int nodoActual;
        ArrayList<Integer> adyacentes;
        Iterator<Integer> itr;

        while (!enc && !porExaminar.isEmpty()) {
            nodoActual = porExaminar.remove();
            if (nodoActual == fin) enc = true;
            else {
                adyacentes = this.adjList[nodoActual];
                itr = adyacentes.iterator();
                while (itr.hasNext()){
                    int adj = itr.next();
                    if (!examinados[adj]) {
                        porExaminar.offer(adj);
                        backPointers[adj] = nodoActual;
                        examinados[adj] = true;
                    }
                }
            }
        }
        if (enc) {
            int i = this.th.get(a2);
            resultado.add(i);
            while (backPointers[i] != -1) {
                resultado.add(backPointers[i]);
                i = backPointers[i];
            }

            int x = 0;
            for (int y = resultado.size() - 1; y >= resultado.size() / 2 + 1; y--){
                int temp = resultado.get(x);
                resultado.set(x++,resultado.get(y));
                resultado.set(y, temp);
            }
        }
        return resultado;
    }

}
