package packTools;
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

    public void mergeSort(T[] pArray){
        mergeSort(pArray, 0, (pArray.length - 1));
    }

    private void mergeSort(T[] pArray, int inicio, int fin){
        if (inicio < fin){
            mergeSort(pArray, inicio, (inicio + fin) / 2);
            mergeSort(pArray, ((inicio + fin) / 2) + 1, fin);
            mezcla(pArray, inicio, (inicio + fin) / 2, fin);
        }
    }

    private void mezcla(T[] pArray, int i, int centro, int f){
        T[] laMezcla = (T[]) new Comparable[f-i+1];
        int cont = 0;
        int izq = i;
        int der = centro + 1;
        while (izq <= centro && der <= f){
            if (pArray[izq].compareTo(pArray[der]) <= 0){
                laMezcla[cont] = pArray[izq];
                izq++;
            } else {
                laMezcla[cont] = pArray[der];
                der++;
            }
            cont++;
        }
        if (izq > centro){
            while (der <= f){
                laMezcla[cont] = pArray[der];
                der++;
                cont++;
            }
        } else {
            while (izq <= centro){
                laMezcla[cont] = pArray[izq];
                izq++;
                cont++;
            }
        }
        for (int j = i; j <= f; j++) pArray[j] =  laMezcla[j-i];
    }

    public void quickSort(T[] pArray){
        quickSort(pArray, 0, pArray.length - 1);
    }

    private void quickSort(T[] pArray, int inicio, int fin){
        if (fin - inicio > 0){
            int indiceParticion = particion(pArray, inicio, fin);
            quickSort(pArray, inicio, indiceParticion - 1);
            quickSort(pArray, indiceParticion + 1, fin);
        }
    }

    private int particion(T[] pArray, int i, int f){
        T pivote = pArray[i];
        int izq = i;
        int der = f;

        while (izq < der){
            while (pArray[izq].compareTo(pivote) <= 0 && izq < der) izq++;
            while (pArray[der].compareTo(pivote) > 0) der--;
            if (izq < der) intercambiar(pArray, izq, der);
        }
        pArray[i] = pArray[der];
        pArray[der] = pivote;

        return der;
    }

    private void intercambiar(T[] pArray, int uno, int dos){
        T temp = pArray[uno];
        pArray[uno] = pArray[dos];
        pArray[dos] = temp;
    }
}
