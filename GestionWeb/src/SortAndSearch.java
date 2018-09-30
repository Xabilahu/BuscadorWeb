import java.util.ArrayList;

public class SortAndSearch < T extends Comparable<T> >{

    public void mergeSort(ArrayList<T> pArray){
        mergeSort(pArray, 0, pArray.size() - 1);
    }

    private void mergeSort(ArrayList<T> pArray, int inicio, int fin){
        if (inicio < fin){
            mergeSort(pArray, inicio, (inicio + fin) / 2);
            mergeSort(pArray, ((inicio + fin) / 2) + 1, fin);
            mezcla(pArray, inicio, (inicio + fin) / 2, fin);
        }
    }

    private void mezcla(ArrayList<T> pArray, int i, int centro, int f){
        ArrayList<T> laMezcla = new ArrayList<T>();
        int izq = i;
        int der = centro + 1;
        while (izq <= centro && der <= f){
            if (pArray.get(izq).compareTo(pArray.get(der)) <= 0){
                laMezcla.add(pArray.get(izq));
                izq++;
            } else {
                laMezcla.add(pArray.get(der));
                der++;
            }
        }
        if (izq > centro){
            while (der <= f){
                laMezcla.add(pArray.get(der));
                der++;
            }
        } else {
            while (izq <= centro){
                laMezcla.add(pArray.get(izq));
                izq++;
            }
        }
        for (int j = i; j <= f; j++) pArray.set(j, laMezcla.get(j-i));
    }

    public void quickSort(ArrayList<T> pArray){
        quickSort(pArray, 0, pArray.size() - 1);
    }

    private void quickSort(ArrayList<T> pArray, int inicio, int fin){
        if (fin - inicio > 0){
            int indiceParticion = particion(pArray, inicio, fin);
            quickSort(pArray, inicio, indiceParticion - 1);
            quickSort(pArray, indiceParticion + 1, fin);
        }
    }

    private int particion(ArrayList<T> pArray, int i, int f){
        T pivote = pArray.get(i);
        int izq = i;
        int der = f;

        while (izq < der){
            while (pArray.get(izq).compareTo(pivote) <= 0 && izq < der) izq++;
            while (pArray.get(der).compareTo(pivote) > 0) der--;
            if (izq < der) intercambiar(pArray, izq, der);
        }
        pArray.set(i, pArray.get(der));
        pArray.set(der, pivote);

        return der;
    }

    private void intercambiar(ArrayList<T> pArray, int uno, int dos){
        T temp = pArray.get(uno);
        pArray.set(uno, pArray.get(dos));
        pArray.set(dos, temp);
    }
}
