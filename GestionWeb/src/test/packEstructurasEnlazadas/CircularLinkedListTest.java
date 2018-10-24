package test.packEstructurasEnlazadas; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import packEstructurasEnlazadas.CircularLinkedList;
import packEstructurasEnlazadas.UnorderedCircularLinkedList;

import static org.junit.Assert.*;

/** 
* CircularLinkedList Tester. 
*
*/ 
public class CircularLinkedListTest { 

@Before
public void before() throws Exception {

} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: setDescr(String nom) 
* 
*/ 
@Test
public void testSetDescr() throws Exception {
    CircularLinkedList<Integer> listaVacia= new CircularLinkedList<Integer>();
    listaVacia.setDescr("prueba");
    assertEquals(listaVacia.getDescr(),"prueba");
} 

/** 
* 
* Method: getDescr() 
* 
*/ 
@Test
public void testGetDescr() throws Exception {
    CircularLinkedList<Integer> listaVacia= new CircularLinkedList<Integer>();
    listaVacia.setDescr("prueba");
    assertEquals(listaVacia.getDescr(),"prueba");
}

/** 
* 
* Method: removeFirst() 
* 
*/ 
@Test
public void testRemoveFirst() throws Exception {
    UnorderedCircularLinkedList<Integer> listaVacia= new UnorderedCircularLinkedList<Integer>();
    UnorderedCircularLinkedList<Integer> lista1Elem= new UnorderedCircularLinkedList<Integer>();
    UnorderedCircularLinkedList<Integer> lista2Elem= new UnorderedCircularLinkedList<Integer>();
    lista1Elem.addToFront(1);
    lista2Elem.addToFront(1);
    lista2Elem.addToFront(2);
    lista2Elem.removeFirst();
    assertEquals(lista1Elem.first(),lista2Elem.first());
    lista1Elem.removeFirst();
    assertTrue(lista1Elem.isEmpty());


} 

/** 
* 
* Method: removeLast() 
* 
*/ 
@Test
public void testRemoveLast() throws Exception {
    UnorderedCircularLinkedList<Integer> listaVacia= new UnorderedCircularLinkedList<Integer>();
    UnorderedCircularLinkedList<Integer> lista1Elem= new UnorderedCircularLinkedList<Integer>();
    UnorderedCircularLinkedList<Integer> lista2Elem= new UnorderedCircularLinkedList<Integer>();
    lista1Elem.addToFront(1);
    lista2Elem.addToFront(2);
    lista2Elem.addToFront(1);
    lista2Elem.removeLast();
    assertEquals(lista1Elem.first(),lista2Elem.first());
    lista1Elem.removeLast();
    assertTrue(lista1Elem.isEmpty());
} 

/** 
* 
* Method: remove(T elem) 
* 
*/ 
@Test
public void testRemove() throws Exception {
    UnorderedCircularLinkedList<Integer> listaElem= new UnorderedCircularLinkedList<Integer>();
    listaElem.addToFront(2);
    listaElem.addToFront(3);
    listaElem.addToFront(1);
    listaElem.addToFront(4);
    assertNull(listaElem.remove(5));
    listaElem.remove(4);
    assertFalse(listaElem.contains(4));
    listaElem.remove(3);
    assertFalse(listaElem.contains(3));
    listaElem.remove(2);
    assertFalse(listaElem.contains(2));
    listaElem.remove(1);
    assertTrue(listaElem.contains(1));
} 

/** 
* 
* Method: first() 
* 
*/ 
@Test
public void testFirst() throws Exception {
    UnorderedCircularLinkedList<Integer> listaElem= new UnorderedCircularLinkedList<Integer>();
    assertNull(listaElem.first());
    listaElem.addToFront(1);
    assertTrue(listaElem.first()==1);
    listaElem.addToFront(2);
    assertTrue(listaElem.first()==2);
    listaElem.addToFront(3);
    assertTrue(listaElem.first()==3);

} 

/** 
* 
* Method: last() 
* 
*/ 
@Test
public void testLast() throws Exception {
    UnorderedCircularLinkedList<Integer> listaElem= new UnorderedCircularLinkedList<Integer>();
    assertNull(listaElem.last());
    listaElem.addToRear(1);
    assertTrue(listaElem.last()==1);
    listaElem.addToRear(2);
    assertTrue(listaElem.last()==2);
    listaElem.addToRear(3);
    assertTrue(listaElem.last()==3);
} 

/** 
* 
* Method: contains(T elem) 
* 
*/ 
@Test
public void testContains() throws Exception {
    UnorderedCircularLinkedList<Integer> listaVacia= new UnorderedCircularLinkedList<Integer>();
    UnorderedCircularLinkedList<Integer> listaElem= new UnorderedCircularLinkedList<Integer>();
    listaElem.addToFront(2);
    listaElem.addToFront(3);
    listaElem.addToFront(1);
    listaElem.addToFront(4);
    assertFalse(listaElem.contains(5));
    assertTrue(listaElem.contains(4));
    assertTrue(listaElem.contains(3));
    assertTrue(listaElem.contains(2));
    assertTrue(listaElem.contains(1));
    assertFalse(listaVacia.contains(1));
} 

/** 
* 
* Method: find(T elem) 
* 
*/ 
@Test
public void testFind() throws Exception {
    UnorderedCircularLinkedList<Integer> listaVacia= new UnorderedCircularLinkedList<Integer>();
    UnorderedCircularLinkedList<Integer> listaElem= new UnorderedCircularLinkedList<Integer>();
    listaElem.addToFront(2);
    listaElem.addToFront(3);
    listaElem.addToFront(1);
    listaElem.addToFront(4);
    assertTrue(listaElem.find(5)==null);
    assertTrue(listaElem.find(4)==4);
    assertTrue(listaElem.find(3)==3);
    assertTrue(listaElem.find(2)==2);
    assertTrue(listaElem.find(1)==1);
    assertTrue(listaVacia.find(1)==null);
} 

/** 
* 
* Method: isEmpty() 
* 
*/ 
@Test
public void testIsEmpty() throws Exception {
    UnorderedCircularLinkedList<Integer> listaVacia= new UnorderedCircularLinkedList<Integer>();
    UnorderedCircularLinkedList<Integer> listaElem= new UnorderedCircularLinkedList<Integer>();
    listaElem.addToFront(2);
    assertTrue(listaVacia.isEmpty());
    assertFalse(listaElem.isEmpty());
} 

/** 
* 
* Method: size() 
* 
*/ 
@Test
public void testSize() throws Exception {
    UnorderedCircularLinkedList<Integer> listaVacia= new UnorderedCircularLinkedList<Integer>();
    UnorderedCircularLinkedList<Integer> listaElem= new UnorderedCircularLinkedList<Integer>();
    listaElem.addToFront(2);
    assertTrue(listaElem.size()==1);
    assertTrue(listaVacia.size()==0);
    listaElem.addToFront(3);
    assertTrue(listaElem.size()==2);
} 

/** 
* 
* Method: iterator() 
* 
*/ 
@Test
public void testIterator() throws Exception {
    assertNull(null);
} 

/** 
* 
* Method: visualizarNodos() 
* 
*/ 
@Test
public void testVisualizarNodos() throws Exception {
    assertNull(null);
} 

/** 
* 
* Method: toString() 
* 
*/ 
@Test
public void testToString() throws Exception {
    assertNull(null);
} 

/** 
* 
* Method: hasNext() 
* 
*/ 
@Test
public void testHasNext() throws Exception {
    assertNull(null);
} 

/** 
* 
* Method: next() 
* 
*/ 
@Test
public void testNext() throws Exception {
    assertNull(null);
} 


} 
