package com.innovecs.task.list;

import com.innovecs.task.iterator.ListIterator;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author Roman Horilyi
 */
public class LinkedList<T> implements List<T> {
    private Node<T> headElement;
    private int size;

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element the element to be added to this list
     */
    @Override
    public void add(T element) {
        addLast(element);
    }

    /**
     * Adds the specified element at the specified position in this list.
     * Shifts the element currently at that position (if any) and any subsequent elements to the right.
     * @param index index at which the specified element is to be added
     * @param element the element to be added
     */
    @Override
    public void add(int index, T element) {
        ListIterator<T> iterator = iterator(index);
        iterator.add(element);
    }

    /**
     * Adds the specified element at the beginning of this list.
     *
     * @param element the element to be added
     */
    @Override
    public void addFirst(T element) {
        ListIterator<T> iterator = iterator();
        iterator.add(element);
    }

    /**
     * Adds the specified element to the end of this list.
     *
     * @param element the element to be added
     */
    @Override
    public void addLast(T element) {
        ListIterator<T> iterator;
        if (isEmpty()) {
            iterator = iterator();
        } else {
            int lastIndex = size - 1;
            iterator = iterator(lastIndex);
            iterator.next(); // go to the tail that points to null
        }
        iterator.add(element);
    }

    /**
     * Returns the element at the specified position in this list.
     *
     * @param index index of the element to return
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public T get(int index) {
        return getElement(index);
    }

    /**
     * Returns {@link Optional} of the first element of this list
     * or {@link Optional#empty()} in case this list is empty.
     */
    @Override
    public Optional<T> getFirst() {
        int startIndex = 0;
        return applyFailFastRetrieving(startIndex, this::getElement);
    }

    /**
     * Returns {@link Optional} of the last element of this list
     * or {@link Optional#empty()} in case this list is empty.
     */
    @Override
    public Optional<T> getLast() {
        int lastElementIndex = size - 1;
        return applyFailFastRetrieving(lastElementIndex, this::getElement);
    }

    /**
     * Removes the element at the specified position in this list and returns this element.
     *
     * @param index index of the element to return
     * @return removed element
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    @Override
    public T remove(int index) {
        return removeElement(index);
    }

    /**
     * Removes the first element of this list and returns it.
     *
     * @return {@link Optional} value of the first element or {@link Optional#empty()} in case this list is empty
     */
    @Override
    public Optional<T> removeFirst() {
        int startIndex = 0;
        return applyFailFastRetrieving(startIndex, this::removeElement);
    }

    /**
     * Removes the last element of this list and returns it.
     *
     * @return {@link Optional} value of the last element or {@link Optional#empty()} in case this list is empty
     */
    @Override
    public Optional<T> removeLast() {
        int lastElementIndex = size - 1;
        return applyFailFastRetrieving(lastElementIndex, this::removeElement);
    }

    /**
     * Returns the number of elements in this list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if this list contains elements or not.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns an array containing all of the elements in this list in proper sequence (from first to last element).
     */
    @Override
    public Object[] toArray() {
        Object[] resultArray = new Object[size];
        ListIterator<T> iterator = iterator();
        int arrayIndex = 0;
        while (iterator.hasNext()) {
            int i = arrayIndex; // effectively final index
            iterator.next().ifPresent(value -> resultArray[i] = value);
            arrayIndex++;
        }
        return resultArray;
    }

    /**
     * Returns a iterator of the elements in this list (in proper sequence),
     * starting at the first element of the list.
     */
    @Override
    public ListIterator<T> iterator() {
        return new LinkedListIterator();
    }

    /**
     * Returns a iterator of the elements in this list (in proper sequence),
     * starting at the specified position in the list.
     *
     * @param index index of the first element to be returned from the iterator (by a call to next)
     */
    public ListIterator<T> iterator(int index) {
        return new LinkedListIterator(index);
    }


    private T getElement(int index) {
        checkIndexBounds(index);

        Node<T> currentElement = headElement;
        for (int i = 0; i < index; i++) {
            currentElement = currentElement.getNextNode();
        }
        return currentElement.getData();
    }

    private T removeElement(int index) {
        return iterator(index)
                .remove()
                .orElseThrow(IndexOutOfBoundsException::new);
    }

    /**
     * Unlinks the specified element from this list.
     *
     * @param element element to be removed
     * @param previousElement previous element of the element to be removed
     */
    private void unlinkElement(Node<T> element, Node<T> previousElement) {
        if (element == headElement) {
            headElement = element.getNextNode();
        } else {
            previousElement.setNextNode(element.getNextNode());
        }
    }

    /**
     * Links the specified new element to this list
     * in such a way it's inserted between the specified previous and current elements.
     *
     * @param newElement the new element to be linked
     * @param element the next element of the specified new element to be linked
     * @param previousElement the previous element of the specified new element to be linked
     */
    private void linkElement(Node<T> newElement, Node<T> element, Node<T> previousElement) {
        if (element == headElement) {
            headElement = newElement;
            headElement.setNextNode(element);
        } else {
            previousElement.setNextNode(newElement);
            newElement.setNextNode(element);
        }
    }

    private void checkIndexBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Applies the specified retrieve function of a value with the specified index and returns this value.
     *
     * @param index position of an element to be retrieved
     * @param retrieveFunction retrieve function to be applied
     *                         that accepts the specified index and returns element of the specified type E
     * @return {@link Optional} retrieved value or {@link Optional#empty()} in case this list is empty
     */
    private <E> Optional<E> applyFailFastRetrieving(int index, Function<Integer, E> retrieveFunction) {
        if (isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(retrieveFunction.apply(index));
        }
    }

    private class LinkedListIterator implements ListIterator<T> {
        private Node<T> previousElement;
        private Node<T> currentElement;

        private LinkedListIterator() {
            currentElement = headElement;
        }

        private LinkedListIterator(int index) {
            this();
            iterateToElement(index);
        }

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public Optional<T> next() {
            if (!hasNext()) {
                return Optional.empty();
            }
            T data = currentElement.getData();
            previousElement = currentElement;
            currentElement = currentElement.getNextNode();
            return Optional.of(data);
        }

        @Override
        public Optional<T> remove() {
            if (!hasNext()) {
                return Optional.empty();
            }
            Optional<T> removedValue = Optional.of(currentElement.getData());
            unlinkElement(currentElement, previousElement);
            currentElement = currentElement.getNextNode();
            size--;
            return removedValue;
        }

        @Override
        public void add(T element) {
            Node<T> newElement = new Node<>(element);
            if (isEmpty()) {
                linkElement(newElement, null, null);
            } else {
                linkElement(newElement, currentElement, previousElement);
            }
            previousElement = newElement;
            size++;
        }

        private void iterateToElement(int index) {
            checkIndexBounds(index);
            for (int i = 0; i < index; i++) {
                next();
            }
        }
    }
}