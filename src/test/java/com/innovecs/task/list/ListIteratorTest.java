package com.innovecs.task.list;

import com.innovecs.task.iterator.ListIterator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static com.innovecs.task.util.LinkedListUtils.createNotEmptyLinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Roman Horilyi
 */
class ListIteratorTest {

    @Test
    @Tag("defaultIterator")
    void testHasNext_whenListIsEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        ListIterator<Integer> iterator = linkedList.iterator();

        boolean hasNext = iterator.hasNext();

        assertFalse(hasNext);
    }

    @Test
    @Tag("defaultIterator")
    void testHasNext_whenListIsNonEmpty() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        ListIterator<Integer> iterator = linkedList.iterator();

        boolean hasNext = iterator.hasNext();

        assertTrue(hasNext);
    }

    @Test
    @Tag("defaultIterator")
    void testNext_whenListIsEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        ListIterator<Integer> iterator = linkedList.iterator();

        Optional<Integer> nextValue = iterator.next();

        assertFalse(nextValue.isPresent());
    }

    @Test
    @Tag("defaultIterator")
    void testNext_whenListIsNonEmpty() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        ListIterator<Integer> iterator = linkedList.iterator();

        Optional<Integer> value0 = iterator.next();
        assertTrue(value0.isPresent());
        assertEquals(5, (int) value0.get());

        Optional<Integer> value1 = iterator.next();
        assertTrue(value1.isPresent());
        assertEquals(7, (int) value1.get());

        Optional<Integer> value2 = iterator.next();
        assertTrue(value2.isPresent());
        assertEquals(-1, (int) value2.get());

        Optional<Integer> value3 = iterator.next();
        assertFalse(value3.isPresent());
        assertEquals(3, linkedList.size());
    }

    @Test
    @Tag("defaultIterator")
    void testRemove_whenListIsEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        ListIterator<Integer> iterator = linkedList.iterator();

        Optional<Integer> removedValue = iterator.remove();

        assertFalse(removedValue.isPresent());
    }

    @Test
    @Tag("defaultIterator")
    void testRemove_whenListIsNonEmpty() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        ListIterator<Integer> iterator = linkedList.iterator();

        Optional<Integer> removedValue0 = iterator.remove();
        assertTrue(removedValue0.isPresent());
        assertEquals(5, (int) removedValue0.get());
        assertEquals(2, linkedList.size());

        Optional<Integer> removedValue1 = iterator.remove();
        assertTrue(removedValue1.isPresent());
        assertEquals(7, (int) removedValue1.get());
        assertEquals(1, linkedList.size());

        Optional<Integer> removedValue2 = iterator.remove();
        assertTrue(removedValue2.isPresent());
        assertEquals(-1, (int) removedValue2.get());
        assertEquals(0, linkedList.size());

        Optional<Integer> removedValue3 = iterator.remove();
        assertFalse(removedValue3.isPresent());
    }

    @Test
    @Tag("defaultIterator")
    void testAdd_whenListIsEmpty() {
        LinkedList<Integer> linkedList = new LinkedList<>();
        ListIterator<Integer> iterator = linkedList.iterator();

        iterator.add(1);
        assertEquals(1, linkedList.size());

        iterator.add(-3);
        assertEquals(2, linkedList.size());

        Object[] values = linkedList.toArray();
        Integer[] expectedValues = {1, -3};
        assertTrue(Arrays.equals(values, expectedValues));
    }

    @Test
    @Tag("defaultIterator")
    void testAddFirst_whenListIsNonEmpty() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        ListIterator<Integer> iterator = linkedList.iterator();

        iterator.add(1);
        assertEquals(4, linkedList.size());

        iterator.add(-3);
        assertEquals(5, linkedList.size());

        Object[] values = linkedList.toArray();
        Integer[] expectedValues = {1, -3, 5, 7, -1};
        assertTrue(Arrays.equals(values, expectedValues));
    }

    @Test
    @Tag("defaultIterator")
    void testAddInTheMiddle_whenListIsNonEmpty() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        ListIterator<Integer> iterator = linkedList.iterator();
        iterator.next();

        iterator.add(1);
        assertEquals(4, linkedList.size());

        iterator.add(-3);
        assertEquals(5, linkedList.size());

        Object[] values = linkedList.toArray();
        Integer[] expectedValues = {5, 1, -3, 7, -1};
        assertTrue(Arrays.equals(values, expectedValues));
    }

    @Test
    @Tag("indexedIterator")
    void testAddLast_whenListIsNonEmpty() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        int lastIndex = 2;
        ListIterator<Integer> iterator = linkedList.iterator(lastIndex);
        iterator.next(); // go to the tail of the list which points to null

        iterator.add(1);
        assertEquals(4, linkedList.size());

        iterator.add(-3);
        assertEquals(5, linkedList.size());

        Object[] values = linkedList.toArray();
        Integer[] expectedValues = {5, 7, -1, 1, -3};
        assertTrue(Arrays.equals(values, expectedValues));
    }

    @Test
    @Tag("indexedIterator")
    void testHasNext_whenIndexedIteratorAndListIsNonEmpty() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        ListIterator<Integer> iterator = linkedList.iterator(1);

        boolean hasNext = iterator.hasNext();

        assertTrue(hasNext);
    }

    @Test
    @Tag("indexedIterator")
    void testNext_whenIndexedIterator() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        ListIterator<Integer> iterator = linkedList.iterator(1);

        Optional<Integer> value0 = iterator.next();
        assertTrue(value0.isPresent());
        assertEquals(7, (int) value0.get());

        Optional<Integer> value1 = iterator.next();
        assertTrue(value1.isPresent());
        assertEquals(-1, (int) value1.get());

        Optional<Integer> value2 = iterator.next();
        assertFalse(value2.isPresent());
        assertEquals(3, linkedList.size());
    }

    @Test
    @Tag("indexedIterator")
    void testRemove_whenIndexedIterator() {
        LinkedList<Integer> linkedList = createNotEmptyLinkedList(new int[]{5, 7, -1});
        ListIterator<Integer> iterator = linkedList.iterator(1);

        Optional<Integer> removedValue0 = iterator.remove();
        assertTrue(removedValue0.isPresent());
        assertEquals(7, (int) removedValue0.get());
        assertEquals(2, linkedList.size());

        Optional<Integer> removedValue1 = iterator.remove();
        assertTrue(removedValue1.isPresent());
        assertEquals(-1, (int) removedValue1.get());
        assertEquals(1, linkedList.size());

        Optional<Integer> removedValue2 = iterator.remove();
        assertFalse(removedValue2.isPresent());
        assertEquals(1, linkedList.size());
        assertEquals(5, (int) linkedList.get(0));
    }
}
