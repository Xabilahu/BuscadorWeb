package packEstructurasEnlazadas;

public class OrderedCircularLinkedList<T extends Comparable> extends CircularLinkedList<T> implements OrderedListADT<T> {
	
	public void add(T elem){		
		Node nuevo = new Node(elem);	
		if (last != null) {
			Node actual = last.next;
			Node anterior = last;
			boolean added = false;
			
			while (actual.next != last && !added) {
				if (elem.compareTo(actual.data) < 0) {
					nuevo.next = actual;
					anterior.next = nuevo;
					added = true;				
				}
				actual = actual.next;
			}
			if (actual.next == last && !added) {
				actual.next = nuevo;
				nuevo.next = actual;
				if(elem.compareTo(actual.data) >= 0) {
					last = nuevo;
				}
				added = true;
			}
		}else {
			last = nuevo;
			nuevo.next = nuevo;
		}
	
	}
}
