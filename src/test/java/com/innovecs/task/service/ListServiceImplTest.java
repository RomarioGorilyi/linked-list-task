package com.innovecs.task.service;

import com.innovecs.task.list.LinkedList;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static com.innovecs.task.util.LinkedListUtils.createNotEmptyLinkedList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Roman Horilyi
 */
public class ListServiceImplTest {

    private ListService listService = new ListServiceImpl();

    @Test
    void testReverseList() {
        LinkedList<Integer> list = createNotEmptyLinkedList(new int[]{5, 7, -1});

        listService.reverseList(list);

        Object[] listValues = list.toArray();
        Integer[] expectedListValues = {-1, 7, 5};
        assertTrue(Arrays.equals(listValues, expectedListValues));
    }

    @Test
    void testReverseList_whenListIsEmpty() {
        LinkedList<Integer> list = new LinkedList<>();

        listService.reverseList(list);

        Object[] listValues = list.toArray();
        assertEquals(0, listValues.length);
    }

    @Test
    void testToString() {
        LinkedList<Integer> list = createNotEmptyLinkedList(new int[]{5, 7, -1});

        String listAsString = listService.toString(list);

        String expectedListAsString = "[5, 7, -1]";
        assertEquals(expectedListAsString, listAsString);
    }

    @Test
    void testToString_whenListIsEmpty() {
        LinkedList<Integer> list = new LinkedList<>();

        String listAsString = listService.toString(list);

        String expectedListAsString = "[]";
        assertEquals(expectedListAsString, listAsString);
    }
}
