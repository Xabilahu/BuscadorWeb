package packEstructurasEnlazadas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularLinkedList<T> implements ListADT<T> {

	// Atributos
	protected Node<T> last; // apuntador al ultimo
	protected String descr;  // descripcion
	protected int count;

	// Constructor
	public CircularLinkedList() {
	     last = null;
		descr = "";
		count = 0;
	}
	
	public void setDescr(String nom) {
	  descr = nom;
	}

	public String getDescr() {
	  return descr;
	}

	public T removeFirst() {
	// Elimina el primer elemento de la lista
        // Precondicion: la lista tiene al menos un elemento
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		T aux=last.next.data;
		if(count>1){
            last.next=last.next.next;
        }
        else{
		    last=null;
        }
		return aux;
	}

	public T removeLast() {
	// Elimina el ultimo elemento de la lista
        // Precondicion: la lista tiene al menos un elemento
			// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
        T aux=last.data;
        Node elemActual=last;
        int counter=1;
        if (counter<count) {
            while(counter<=count){
                elemActual=elemActual.next;
                counter++;
            }
            elemActual.next=last.next;
            last=elemActual;
        }
        else{
            last=null;
        }
        return aux;
		   }


	public T remove(T elem) {
	//Elimina un elemento concreto de la lista
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
        T aux;
        Node elemActual=last;
        boolean found=false;
        int counter=count;
            if ( elemActual.data.equals(elem)) {
                aux=this.removeLast();
            }
            else {
                while (!found && counter>0) {
                    if(elemActual.next.data.equals(elem)){
                        found=true;
                    }
                    else{
                        elemActual = elemActual.next;
                        count--;
                    }
                }
                aux= (T)elemActual.next.data;
                elemActual.next = elemActual.next.next;
            }
            return aux;
	}

	public T first() {
	//Da acceso al primer elemento de la lista
	      if (isEmpty())
	          return null;
	      else return last.next.data;
	}

	public T last() {
	//Da acceso al ultimo elemento de la lista
	      if (isEmpty())
	          return null;
	      else return last.data;
	}

	public boolean contains(T elem) {
            return elem.equals(this.find(elem));
		   }

	public T find(T elem) {
	//Determina si la lista contiene un elemento concreto, y develve su referencia, null en caso de que no esta
		// COMPLETAR EL CODIGO Y CALCULAR EL COSTE
		T aux;
		Node elemActual=last;
		boolean found=false;
		int counter=count;
		while (!found && counter>0) {
			if(elemActual.data.equals(elem)){
				found=true;
			}
			else{
			    elemActual = elemActual.next;
			    count--;
			}
		}
		aux= (T)elemActual.data;
		return aux;
	}

	public boolean isEmpty() 
	//Determina si la lista esta vacia
	{ return last == null;};
	
	public int size() 
	//Determina el numero de elementos de la lista
	{ return count;}
	
	/** Return an iterator to the stack that iterates through the items . */ 
	   public Iterator<T> iterator() { return new ListIterator(); } 

	   // an iterator, doesn't implement remove() since it's optional 
	   private class ListIterator implements Iterator<T> {
	       private Node<T> current;
           @Override
           public boolean hasNext() {
               return current!=null;
           }

           @Override
           public T next() {
               if(hasNext()) {
                   T aux = current.data;
                   current = current.next;
                   return aux;
               }
           }

           // COMPLETAR EL CODIGO Y CALCULAR EL COSTE



	   } // private class
		
		
         public void visualizarNodos() {
			System.out.println(this.toString());
		}

		@Override
		public String toString() {
			String result = new String();
			Iterator<T> it = iterator();
			while (it.hasNext()) {
				T elem = it.next();
				result = result + "[" + elem.toString() + "] \n";
			}	
			return "SimpleLinkedList " + result + "]";
		}

}
