package packEstructurasEnlazadas;

public class UnorderedCircularLinkedList<T> extends CircularLinkedList<T> implements UnorderedListADT<T> {

	public void addToFront(T elem) {
        Node<T> nuevo = new Node<T>(elem);
        if (this.last != null) nuevo.next = this.last.next;
        else this.last = nuevo;
        this.last.next = nuevo;
        this.count++;
	}

	public void addToRear(T elem) {
        this.addToFront(elem);
        this.last = this.last.next;
	}
	
	public void addAfter(T elem, T target) {
	    //Se empieza a comprobar desde el last, no desde el last.next
        if(this.last != null){
            Node<T> actualNode = this.last;
            Node<T> anterior = actualNode;
            actualNode = this.comprobarTarget(elem, target, actualNode);
            while (anterior != actualNode) {
                anterior = actualNode;
                actualNode = this.comprobarTarget(elem, target, actualNode);
            }
            this.count++;
        } else throw new IllegalArgumentException("Empty List, no target to be found.");
	}

	private Node<T> comprobarTarget(T elem, T target, Node<T> actualNode) {
	    //Pre: actualNode != null
        //Post: se añade elem a la lista si actualNode.data == target y no se modifica actualNode
        //      si actualNode.data != target actualNode avanza y se devuelve su referencia
        if (actualNode.data.equals(target)){
            Node<T> nuevo = new Node<T>(elem);
            nuevo.next = actualNode.next;
            actualNode.next = nuevo;
        } else actualNode = actualNode.next;
        return actualNode;
    }
}
