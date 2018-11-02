package packPruebas;

import org.junit.Before;
import org.junit.Test;

import packEstructurasEnlazadas.Node;
import packEstructurasEnlazadas.OrderedCircularLinkedList;

import static org.junit.Assert.*;

import java.util.Iterator;

public class OrderedCircularLinkedListTest {

	 private OrderedCircularLinkedList<Character> listaVacia;
	 private OrderedCircularLinkedList<Character> lista1Elem;
	 private OrderedCircularLinkedList<Character> listaVariosElem;
	 private Character a, b, c, x;

	@Before
    public void setUp() throws Exception {
		a = '2';
		b = '4';
		c = '6';
		x = '1';
        this.listaVacia = new OrderedCircularLinkedList<>();
        Node<Character> elem = new Node<>(a);
        elem.next = elem;
        this.lista1Elem = new OrderedCircularLinkedList<>(elem, 1, "1elem");
        Node<Character> last = new Node<>(c);
        Node<Character> medium = new Node<>(b);
        Node<Character> first = new Node<>(a);
        first.next = medium;
        medium.next = last;
        last.next = first;
        this.listaVariosElem = new OrderedCircularLinkedList<>(last, 3, "3elem");
    }
	
	@Test
    public void testAdd() {
		//******************TEST LISTA VACIA************************************
        assertEquals(this.listaVacia.size(), 0);
        assertNull(this.listaVacia.first());
        assertNull(this.listaVacia.last());
        this.listaVacia.add(x);
        assertEquals(this.listaVacia.size(), 1);
        assertEquals(this.listaVacia.last(), this.listaVacia.first());
        assertTrue(this.listaVacia.last().equals(x));
        //******************TEST LISTA 1 ELEMENTO*******************************
        for(int i = 1; i <= 7; i++) {
        	x = (char) i;
	        assertEquals(this.lista1Elem.last(), this.lista1Elem.first());
	        this.lista1Elem.add(x);
	        assertEquals(this.lista1Elem.size(), 2);
	        if (x.compareTo(a)<0) {
	        	assertTrue(this.lista1Elem.first().equals(x));
	            assertTrue(this.lista1Elem.last().equals(a));
	        }else {
	        	assertTrue(this.lista1Elem.first().equals(a));
	            assertTrue(this.lista1Elem.last().equals(x));
	        }
	        lista1Elem.remove(x);
	        //******************TEST LISTA VARIOS ELEMENTOS*************************
	        Iterator<Character> itr = this.listaVariosElem.iterator();
	        assertTrue(itr.next().equals(a));
	        assertTrue(itr.next().equals(b));
	        assertTrue(itr.next().equals(c));
	        assertFalse(itr.hasNext());
	        assertEquals(this.listaVariosElem.size(), 3);        	
	        this.listaVariosElem.add(x);
	        assertEquals(this.listaVariosElem.size(), 4);
	        itr = this.listaVariosElem.iterator();
	        if (x.compareTo(a)<0) {
		        assertTrue(itr.next().equals(x));
		        assertTrue(itr.next().equals(a));
		        assertTrue(itr.next().equals(b));
		        assertTrue(itr.next().equals(c));
		        assertFalse(itr.hasNext());
	        }else if(x.compareTo(a)>= 0 && x.compareTo(b) < 0) {
	            assertTrue(itr.next().equals(a));
	            assertTrue(itr.next().equals(x));
	            assertTrue(itr.next().equals(b));
	            assertTrue(itr.next().equals(c));
	            assertFalse(itr.hasNext());
	        }else if(x.compareTo(b)>= 0 && x.compareTo(c) < 0) {
		    	assertTrue(itr.next().equals(a));              
		        assertTrue(itr.next().equals(b));
		        assertTrue(itr.next().equals(x));
		        assertTrue(itr.next().equals(c));
		        assertFalse(itr.hasNext());
	        }else {
		    	assertTrue(itr.next().equals(a));              
		        assertTrue(itr.next().equals(b));
		        assertTrue(itr.next().equals(c));
		        assertTrue(itr.next().equals(x));
		        assertFalse(itr.hasNext());
	        }
	        listaVariosElem.remove(x);
        }        	       
    }
}