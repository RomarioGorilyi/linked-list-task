package com.innovecs.task.service;

import com.innovecs.task.list.List;

/**
 * @author Roman Horilyi
 */
public interface ListService {
    /**
     * Reverses the specified list.
     *
     * @param list the list to be reversed
     * @param <T> the type of list values
     */
    <T> void reverseList(List<T> list);

    /**
     * Returns a string representation of the specified collection.
     *
     * @param list the list to be represented as a string
     * @param <T> the type of list values
     * @return a string representation of the specified collection
     */
    <T> String toString(List<T> list);
}
