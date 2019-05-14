package com.innovecs.task.list;

import com.innovecs.task.iterator.Iterable;

import java.util.Optional;

/**
 * @author Roman Horilyi
 */
public interface List<T> extends Iterable<T> {

    void add(T element);
    void add(int index, T element);
    void addFirst(T element);
    void addLast(T element);

    T get(int index);
    Optional<T> getFirst();
    Optional<T> getLast();

    T remove(int index);
    Optional<T> removeFirst();
    Optional<T> removeLast();

    int size();
    boolean isEmpty();
    Object[] toArray();
}
