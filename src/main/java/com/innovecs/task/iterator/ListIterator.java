package com.innovecs.task.iterator;

/**
 * @author Roman Horilyi
 */
public interface ListIterator<T> extends Iterator<T> {

    /**
     * Appends the specified element into the list.
     * The element is inserted immediately before the element that would be returned by next(), if any.
     * If the list contains no elements, the new element becomes the sole element on the list.
     * The new element is inserted before the implicit cursor: a subsequent call to next would be unaffected.
     *
     * @param element the element to insert
     */
    void add(T element);
}
