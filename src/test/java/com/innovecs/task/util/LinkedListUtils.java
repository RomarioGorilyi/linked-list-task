package com.innovecs.task.util;

import com.innovecs.task.list.LinkedList;

/**
 * @author Roman Horilyi
 */
public class LinkedListUtils {

    public static LinkedList<Integer> createNotEmptyLinkedList(int[] values) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int value : values) {
            linkedList.add(value);
        }
        return linkedList;
    }
}
