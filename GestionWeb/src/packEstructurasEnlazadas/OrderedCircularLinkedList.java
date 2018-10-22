package packEstructurasEnlazadas;

public class OrderedCircularLinkedList<T extends Comparable> extends CircularLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){		
		Node<T> nuevo = new Node<T>(elem);
		if (last != null) {
			Node<T> actual = last.next;
			Node<T> anterior = last;
			boolean added = false;
			if (actual.next == last) {
				nuevo.next = last;
				last.next = nuevo;
				if(elem.compareTo(last.data) >= 0) {
					last = nuevo;
				}
				added = true;
			}
			while (actual != last && !added) {
				if (elem.compareTo(actual.data) < 0) {
					nuevo.next = actual;
					anterior.next = nuevo;
					added = true;
				}
				actual = actual.next;
				anterior = anterior.next;
			}
			if (!added){
			    if (elem.compareTo(actual.data) < 0){
                    nuevo.next = actual;
                    anterior.next = nuevo;
                } else {
			        nuevo.next = last.next;
			        last.next = nuevo;
			        last = nuevo;
                }
            }
		}else {
			last = nuevo;
			nuevo.next = nuevo;
		}
	
	}
}
