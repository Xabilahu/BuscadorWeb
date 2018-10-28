package packPruebas;

import org.junit.Before;
import org.junit.Test;
import packEstructurasEnlazadas.Node;
import packEstructurasEnlazadas.UnorderedCircularLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
    public void testSetDescrGetDescr() throws Exception {
        assertEquals(this.listaVacía.getDescr(), "");
        this.listaVacía.setDescr("");
        assertEquals(this.listaVacía.getDescr(), "");
        this.listaVacía.setDescr("       ");
        assertEquals(this.listaVacía.getDescr(), "");
        this.listaVacía.setDescr("a    a");
        assertEquals(this.listaVacía.getDescr(), "a    a");
        this.listaVacía.setDescr(" a a ");
        assertEquals(this.listaVacía.getDescr(), "a a");
        this.listaVacía.setDescr("abc");
        assertEquals(this.listaVacía.getDescr(), "abc");
    }

    @Test
    public void testRemoveFirst() throws Exception {
        //******************TEST LISTA VACÍA************************************
        try {
            this.listaVacía.removeFirst();
            fail();
        } catch (UnsupportedOperationException e) {
        }
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertEquals(this.lista1Elem.size(), 1);
        assertTrue(this.lista1Elem.first().equals('a'));
        assertEquals(this.lista1Elem.first(), this.lista1Elem.last());
        assertTrue(this.lista1Elem.removeFirst().equals('a'));
        assertEquals(this.lista1Elem.size(), 0);
        assertNull(this.lista1Elem.first());
        assertNull(this.lista1Elem.last());
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        Iterator<Character> itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 3);
        assertTrue(this.listaVariosElem.removeFirst().equals('a'));
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 2);
        assertTrue(this.listaVariosElem.first().equals('b'));
        assertTrue(this.listaVariosElem.last().equals('c'));
    }

    @Test
    public void testRemoveLast() throws Exception {
        //******************TEST LISTA VACÍA************************************
        try{
            this.listaVacía.removeLast();
            fail();
        } catch (UnsupportedOperationException e){}
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertEquals(this.lista1Elem.size(), 1);
        assertTrue(this.lista1Elem.first().equals('a'));
        assertEquals(this.lista1Elem.first(), this.lista1Elem.last());
        assertTrue(this.lista1Elem.removeLast().equals('a'));
        assertEquals(this.lista1Elem.size(), 0);
        assertNull(this.lista1Elem.first());
        assertNull(this.lista1Elem.last());
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        Iterator<Character> itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 3);
        assertTrue(this.listaVariosElem.removeLast().equals('c'));
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 2);
        assertTrue(this.listaVariosElem.first().equals('a'));
        assertTrue(this.listaVariosElem.last().equals('b'));
    }

    @Test
    public void testRemove() throws Exception {
        //******************TEST LISTA VACÍA************************************
        assertEquals(this.listaVacía.size(), 0);
        assertNull(this.listaVacía.first());
        assertNull(this.listaVacía.last());
        assertNull(this.listaVacía.remove('a'));
        assertEquals(this.listaVacía.size(), 0);
        assertNull(this.listaVacía.first());
        assertNull(this.listaVacía.last());
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertEquals(this.lista1Elem.size(), 1);
        assertTrue(this.lista1Elem.first().equals('a'));
        assertEquals(this.lista1Elem.first(), this.lista1Elem.last());
        //*************************ELEM = X*************************************
        assertNull(this.lista1Elem.remove('x'));
        assertEquals(this.lista1Elem.size(), 1);
        assertTrue(this.lista1Elem.first().equals('a'));
        assertEquals(this.lista1Elem.first(), this.lista1Elem.last());
        //*************************ELEM = A*************************************
        assertTrue(this.lista1Elem.remove('a').equals('a'));
        assertEquals(this.lista1Elem.size(), 0);
        assertNull(this.lista1Elem.first());
        assertNull(this.lista1Elem.last());
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        Iterator<Character> itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 3);
        assertTrue(this.listaVariosElem.first().equals('a'));
        assertTrue(this.listaVariosElem.last().equals('c'));
        //*************************ELEM = X*************************************
        assertNull(this.listaVariosElem.remove('x'));
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 3);
        assertTrue(this.listaVariosElem.first().equals('a'));
        assertTrue(this.listaVariosElem.last().equals('c'));
        //*************************ELEM = A*************************************
        assertTrue(this.listaVariosElem.remove('a').equals('a'));
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('b'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 2);
        assertTrue(this.listaVariosElem.first().equals('b'));
        assertTrue(this.listaVariosElem.last().equals('c'));
        //*************************ELEM = B*************************************
        this.setUp();
        assertTrue(this.listaVariosElem.remove('b').equals('b'));
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('c'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 2);
        assertTrue(this.listaVariosElem.first().equals('a'));
        assertTrue(this.listaVariosElem.last().equals('c'));
        //*************************ELEM = C*************************************
        this.setUp();
        assertTrue(this.listaVariosElem.remove('c').equals('c'));
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.next().equals('a'));
        assertTrue(itr.next().equals('b'));
        assertFalse(itr.hasNext());
        assertEquals(this.listaVariosElem.size(), 2);
        assertTrue(this.listaVariosElem.first().equals('a'));
        assertTrue(this.listaVariosElem.last().equals('b'));
    }

    @Test
    public void testFirst() throws Exception {
        //******************TEST LISTA VACÍA************************************
        assertNull(this.listaVacía.first());
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertTrue(this.lista1Elem.first().equals('a'));
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        assertTrue(this.listaVariosElem.first().equals('a'));
    }

    @Test
    public void testLast() throws Exception {
        //******************TEST LISTA VACÍA************************************
        assertNull(this.listaVacía.last());
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertTrue(this.lista1Elem.last().equals('a'));
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        assertTrue(this.listaVariosElem.last().equals('c'));
    }

    @Test
    public void testContains() throws Exception {
        //******************TEST LISTA VACÍA************************************
        assertFalse(this.listaVacía.contains('x'));
        //******************TEST LISTA 1 ELEMENTO*******************************
        //*************************ELEM = X*************************************
        assertFalse(this.lista1Elem.contains('x'));
        //*************************ELEM = A*************************************
        assertTrue(this.lista1Elem.contains('a'));
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        //*************************ELEM = X*************************************
        assertFalse(this.listaVariosElem.contains('x'));
        //*************************ELEM = A*************************************
        assertTrue(this.listaVariosElem.contains('a'));
        //*************************ELEM = B*************************************
        assertTrue(this.listaVariosElem.contains('b'));
        //*************************ELEM = C*************************************
        assertTrue(this.listaVariosElem.contains('c'));
    }

    @Test
    public void testFind() throws Exception {
        //******************TEST LISTA VACÍA************************************
        assertNull(this.listaVacía.find('x'));
        //******************TEST LISTA 1 ELEMENTO*******************************
        //*************************ELEM = X*************************************
        assertNull(this.lista1Elem.find('x'));
        //*************************ELEM = A*************************************
        assertTrue(this.lista1Elem.find('a').equals('a'));
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        //*************************ELEM = X*************************************
        assertNull(this.listaVariosElem.find('x'));
        //*************************ELEM = A*************************************
        assertTrue(this.listaVariosElem.find('a').equals('a'));
        //*************************ELEM = B*************************************
        assertTrue(this.listaVariosElem.find('b').equals('b'));
        //*************************ELEM = C*************************************
        assertTrue(this.listaVariosElem.find('c').equals('c'));
    }

    @Test
    public void testIsEmpty() throws Exception {
        //******************TEST LISTA VACÍA************************************
        assertTrue(this.listaVacía.isEmpty());
        //******************TEST LISTA 1 ELEMENTO*******************************
        assertFalse(this.lista1Elem.isEmpty());
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        assertFalse(this.listaVariosElem.isEmpty());
    }

    @Test
    public void testSize() throws Exception {
        //******************TEST LISTA VACÍA************************************
        assertEquals(this.listaVacía.size(), 0);
        //******************TEST LISTA 1 ELEMENTO*******************************
        this.listaVacía.addToFront('x');
        assertEquals(this.listaVacía.size(), 1);
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        this.listaVacía.addToFront('x');
        this.listaVacía.addToFront('x');
        assertEquals(this.listaVacía.size(), 3);
    }

    @Test
    public void testHasNext() throws Exception {
        //******************TEST LISTA VACÍA************************************
        Iterator<Character> itr = this.listaVacía.iterator();
        assertFalse(itr.hasNext());
        //******************TEST LISTA 1 ELEMENTO*******************************
        itr = this.lista1Elem.iterator();
        assertTrue(itr.hasNext());
        this.lista1Elem.removeFirst();
        assertFalse(itr.hasNext());
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        itr = this.listaVariosElem.iterator();
        assertTrue(itr.hasNext());
        this.listaVariosElem.removeFirst();
        assertTrue(itr.hasNext());
        this.listaVariosElem.removeFirst();
        assertTrue(itr.hasNext());
        this.listaVariosElem.removeFirst();
        assertFalse(itr.hasNext());
    }

    @Test
    public void testNext() throws Exception {
        //******************TEST LISTA VACÍA************************************
        Iterator<Character> itr = this.listaVacía.iterator();
        try{
            itr.next();
            fail();
        } catch (NoSuchElementException e){}
        //******************TEST LISTA 1 ELEMENTO*******************************
        itr = this.lista1Elem.iterator();
        assertSame(this.lista1Elem.first(), itr.next());
        try{
            itr.next();
            fail();
        } catch (NoSuchElementException e){}
        //******************TEST LISTA VARIOS ELEMENTOS*************************
        itr = this.listaVariosElem.iterator();
        assertSame(this.listaVariosElem.first(), itr.next());
        assertTrue(itr.next().equals('b'));
        assertSame(this.listaVariosElem.last(), itr.next());
        try{
            itr.next();
            fail();
        } catch (NoSuchElementException e){}
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
        assertEquals(this.listaVacía.size(), 0);
        assertNull(this.listaVacía.first());
        assertNull(this.listaVacía.last());
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