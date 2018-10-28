package packPruebas;

import org.junit.Before;
import org.junit.Test;
import packEstructurasEnlazadas.Node;
import packEstructurasEnlazadas.UnorderedCircularLinkedList;

import java.util.Iterator;

import static org.junit.Assert.*;

public class UnorderedCircularLinkedListTest {

    private UnorderedCircularLinkedList<Character> listaVacía;
    private UnorderedCircularLinkedList<Character> lista1Elem;
    private UnorderedCircularLinkedList<Character> listaVariosElem;

    @Before
    public void setUp() throws Exception {
        this.listaVacía = new UnorderedCircularLinkedList<>();
        Node<Character> elem = new Node<>('a');
        elem.next = elem;
        this.lista1Elem = new UnorderedCircularLinkedList<>(elem, 1, "1elem");
        Node<Character> last = new Node<>('c');
        Node<Character> medium = new Node<>('b');
        Node<Character> first = new Node<>('a');
        first.next = medium;
        medium.next = last;
        last.next = first;
        this.listaVariosElem = new UnorderedCircularLinkedList<>(last, 3, "3elem");
    }

    @Test
    public void testAddToFront() {
        //******************TEST LISTA VACÍA************************************
        assertEquals(this.listaVacía.size(), 0);
        assertNull(this.listaVacía.first());
        assertNull(this.listaVacía.last());
        this.listaVacía.addToFront('x');
        assertEquals(this.listaVacía.size(), 1);
        assertEquals(this.listaVacía.last(), this.listaVacía.first());
        assertTrue(this.listaVacía.last().equals('x'));
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertEquals(this.lista1Elem.last(), this.lista1Elem.first());
        this.lista1Elem.addToFront('x');
        assertEquals(this.lista1Elem.size(), 2);
        assertFalse(this.lista1Elem.last().equals(this.lista1Elem.first()));
        assertTrue(this.lista1Elem.first().equals('x'));
        assertTrue(this.lista1Elem.last().equals('a'));
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        Iterator<Character> itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 3);
        this.listaVariosElem.addToFront('x');
        assertEquals(this.listaVariosElem.size(), 4);
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('x'));
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
    }

    @Test
    public void testAddToRear() {
        //******************TEST LISTA VACÍA************************************
        assertEquals(this.listaVacía.size(), 0);
        assertNull(this.listaVacía.first());
        assertNull(this.listaVacía.last());
        this.listaVacía.addToRear('x');
        assertEquals(this.listaVacía.size(), 1);
        assertEquals(this.listaVacía.last(), this.listaVacía.first());
        assertTrue(this.listaVacía.last().equals('x'));
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertEquals(this.lista1Elem.last(), this.lista1Elem.first());
        this.lista1Elem.addToRear('x');
        assertEquals(this.lista1Elem.size(), 2);
        assertFalse(this.lista1Elem.last().equals(this.lista1Elem.first()));
        assertTrue(this.lista1Elem.first().equals('a'));
        assertTrue(this.lista1Elem.last().equals('x'));
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        Iterator<Character> itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 3);
        this.listaVariosElem.addToRear('x');
        assertEquals(this.listaVariosElem.size(), 4);
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertTrue(itr.next().equals('x'));
        assertFalse(itr.hasNext());
    }

    @Test
    public void testAddAfter() {
        //******************TEST LISTA VACÍA************************************
        try{
            this.listaVacía.addAfter('x', 'a');
            fail();
        } catch (IllegalArgumentException e){}
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertEquals(this.lista1Elem.size(), 1);
        assertTrue(this.lista1Elem.first().equals('a'));
        this.lista1Elem.addAfter('x','a');
        assertEquals(this.lista1Elem.size(), 2);
        assertTrue(this.lista1Elem.first().equals('a'));
        assertTrue(this.lista1Elem.last().equals('x'));
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        Iterator<Character> itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 3);
        //****************************Target=A**********************************
        this.listaVariosElem.addAfter('x', 'a');
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('x'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 4);
        //****************************Target=B**********************************
        this.listaVariosElem.addAfter('x', 'b');
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('x'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('x'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 5);
        //****************************Target=C**********************************
        this.listaVariosElem.addAfter('x', 'c');
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('x'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('x'));
        assertTrue(itr.next().equals('c'));
        assertTrue(itr.next().equals('x'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 6);
    }
}