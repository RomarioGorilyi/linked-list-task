package com.innovecs.task.list;

import com.innovecs.task.iterator.Iterator;
import com.innovecs.task.list.LinkedList;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.innovecs.task.util.LinkedListUtils.createNotEmptyLinkedList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Roman Horilyi
 */
class LinkedListTest {

    @Test
    void testAdd_whenListIsEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(5);
        linkedList.add(7);
        linkedList.add(0);
        linkedList.add(-1);

        int expectedListSize = 4;
        assertEquals(expectedListSize, linkedList.size());
    }

    @Test
    void testGet() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});

        int retrievedValue = linkedList.get(1);

        int expectedRetrievedValue = 7;
        assertEquals(expectedRetrievedValue, retrievedValue);
    }

    @Test
    void testGet_shouldThrowsIndexOutOfBoundsException_whenIndexIsLargerOrEqualsToListSize() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(0));
    }

    @Test
    void testGet_shouldThrowsIndexOutOfBoundsException_whenIndexIsNegative() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.get(-1));
    }

    @Test
    void testGetFirst_shouldReturnEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        Optional<Integer> firstElement = linkedList.getFirst();

        assertEquals(Optional.empty(), firstElement);
    }

    @Test
    void testGetFirst_shouldReturnValue() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});

        Optional<Integer> firstElement = linkedList.getFirst();

        Optional<Object> expectedFirstElement = Optional.of(5);
        assertEquals(expectedFirstElement, firstElement);
    }

    @Test
    void testGetLast_shouldReturnEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        Optional<Integer> lastElement = linkedList.getLast();

        assertEquals(Optional.empty(), lastElement);
    }

    @Test
    void testGetLast_shouldReturnValue() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});

        Optional<Integer> lastElement = linkedList.getLast();

        Optional<Object> expectedLastElement = Optional.of(-1);
        assertEquals(expectedLastElement, lastElement);
    }

    @Test
    void testRemove() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});

        int removedValue = linkedList.remove(1);

        assertEquals(7, removedValue);
        assertEquals(2, linkedList.size());
        assertEquals(5, (int) linkedList.get(0));
        assertEquals(-1, (int) linkedList.get(1));
    }

    @Test
    void testRemove_shouldThrowsIndexOutOfBoundsException_whenIndexIsLargerOrEqualsToListSize() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(0));
    }

    @Test
    void testRemove_shouldThrowsIndexOutOfBoundsException_whenIndexIsNegative() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.remove(-1));
    }

    @Test
    void testRemoveFirst() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});

        Optional<Integer> removedValue = linkedList.removeFirst();

        assertTrue(removedValue.isPresent());
        assertEquals(5, (int) removedValue.get());
        assertEquals(2, linkedList.size());
        assertEquals(7, (int) linkedList.get(0));
        assertEquals(-1, (int) linkedList.get(1));
    }

    @Test
    void testRemoveFirst_whenEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        Optional<Integer> removedValue = linkedList.removeFirst();

        assertEquals(Optional.empty(), removedValue);
        assertEquals(0, linkedList.size());
    }

    @Test
    void testRemoveLast() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});

        Optional<Integer> removedValue = linkedList.removeLast();

        assertTrue(removedValue.isPresent());
        assertEquals(-1, (int) removedValue.get());
        assertEquals(2, linkedList.size());
        assertEquals(5, (int) linkedList.get(0));
        assertEquals(7, (int) linkedList.get(1));
    }

    @Test
    void testRemoveLast_whenListIsEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        Optional<Integer> removedValue = linkedList.removeLast();

        assertEquals(Optional.empty(), removedValue);
        assertEquals(0, linkedList.size());
    }

    @Test
    void testToArray_whenListIsEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        Object[] values = linkedList.toArray();

        assertEquals(0, values.length);
    }

    @Test
    void testToArray_whenListIsNonEmpty() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});

        Object[] values = linkedList.toArray();

        assertEquals(3, values.length);
    }

    @Test
    void testIterator() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        Iterator<Integer> iterator = linkedList.iterator();

        assertNotNull(iterator);
    }

    @Test
    void testIteratorWithIndex() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});

        Iterator<Integer> iterator = linkedList.iterator(0);

        assertNotNull(iterator);
    }

    @Test
    void testIteratorWithIndex_shouldThrowsIndexOutOfBoundsException_whenIndexIsLargerOrEqualsToListSize() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.iterator(0));
    }

    @Test
    void testIteratorWithIndex_shouldThrowsIndexOutOfBoundsException_whenIndexIsNegative() {
        LinkedList<Integer> linkedList = new LinkedList<>();

        assertThrows(IndexOutOfBoundsException.class, () -> linkedList.iterator(-1));
    }
}
