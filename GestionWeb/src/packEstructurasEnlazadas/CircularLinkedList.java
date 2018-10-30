package packEstructurasEnlazadas;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class CircularLinkedList<T> implements ListADT<T> {

    // Atributos
    protected Node<T> last; // apuntador al ultimo
    protected String descr;  // descripcion
    protected int count;

    // Constructor
    public CircularLinkedList() {
        this.last = null;
        this.descr = "";
        this.count = 0;
    }

    public void setDescr(String nom) {
        this.descr = nom.trim();
    }

    public String getDescr() {
        return this.descr;
    }

    public T removeFirst() {
        // Elimina el primer elemento de la lista
        // Precondicion: la lista tiene al menos un elemento
        // COMPLETAR EL CODIGO Y CALCULAR EL COSTE
        if (this.last != null) {
            T aux = this.last.next.data;

            if (this.count > 1) this.last.next = this.last.next.next;
            else this.last = null;
            this.count--;

            return aux;
        } else throw new UnsupportedOperationException();
    }

    public T removeLast() {
        // Elimina el ultimo elemento de la lista
        // Precondicion: la lista tiene al menos un elemento
        // COMPLETAR EL CODIGO Y CALCULAR EL COSTE
        if (this.last != null) {
            T aux = this.last.data;
            if (this.count == 1) this.last = null;
            else {
                Node elemActual = this.last.next;
                while (elemActual.next != this.last) {
                    elemActual = elemActual.next;
                }
                elemActual.next = this.last.next;
                this.last = elemActual;
            }
            this.count--;
            return aux;
        } else throw new UnsupportedOperationException();
    }


    public T remove(T elem) {
        //Elimina un elemento concreto de la lista
        // COMPLETAR EL CODIGO Y CALCULAR EL COSTE
        T aux = null;
        if (!this.isEmpty()){
            Node elemActual = this.last;
            boolean found = false;
            while (elemActual.next != this.last && !found) {
                if (elemActual.next.data.equals(elem)) found = true;
                else elemActual = elemActual.next;
            }
            if (found || last.data.equals(elem)) {
                aux = (T) elemActual.next.data;
                if (elemActual.next == this.last && this.count == 1) this.last = null;
                else {
                    if (elemActual.next == this.last) this.last = elemActual;
                    elemActual.next = elemActual.next.next;
                }
                this.count--;
            }
        }
        return aux;
    }

    public T first() {
        //Da acceso al primer elemento de la lista
        if (this.isEmpty()) return null;
        else return last.next.data;
    }

    public T last() {
        //Da acceso al ultimo elemento de la lista
        if (this.isEmpty()) return null;
        else return last.data;
    }

    public boolean contains(T elem) {
        if(this.find(elem) != null) return true;
        else return false;
    }

    public T find(T elem) {
        //Determina si la lista contiene un elemento concreto, y develve su referencia, null en caso de que no esta
        // COMPLETAR EL CODIGO Y CALCULAR EL COSTE
        T aux = null;
        if (!this.isEmpty()) {
            Node elemActual = last.next;
            boolean found = false;

            if (elemActual.data.equals(elem)) found = true;
            else elemActual = elemActual.next;

            while (elemActual != last.next && !found) {
                if (elemActual.data.equals(elem)) found = true;
                else elemActual = elemActual.next;
            }
            if (found) aux = (T) elemActual.data;
        }
        return aux;
    }

    public boolean isEmpty() {
        //Determina si la lista esta vacia
        return last == null;
    }
	
	public int size() {
        //Determina el numero de elementos de la lista
        return count;
    }
	
	/** Return an iterator to the stack that iterates through the items . */ 
	   public Iterator<T> iterator() {
	       return new ListIterator();
	   }

	   // an iterator, doesn't implement remove() since it's optional 
	   private class ListIterator implements Iterator<T> {

	       private Node<T> current;
	       private boolean primeroPasado;

	       public ListIterator() {
	           if (last == null) current = null;
	           else current = last.next;
	           primeroPasado = false;
           }

           @Override
           public boolean hasNext() {
               if (last == null || ( primeroPasado && current == last.next)){
               		return false;
			   }
			   return true;
           }

           @Override
           public T next() {
               if(hasNext()) {
                   if(!primeroPasado) primeroPasado = true;
                   T aux = current.data;
                   current = current.next;
                   return aux;
               }
               else{
                   throw new NoSuchElementException();
               }
           }

           @Override
           public void remove() {
	           throw new UnsupportedOperationException();
           }

}


        public void visualizarNodos() {
			System.out.println(this.toString());
		}

		@Override
		public String toString() {
			String result = "\n";
			Iterator<T> it = iterator();
			while (it.hasNext()) {
				T elem = it.next();
				result += "[" + elem.toString() + "] \n";
			}	
			return result;
		}

}
