package com.innovecs.task.iterator;

import java.util.Optional;

/**
 * @author Roman Horilyi
 */
public interface Iterator<T> {

    /**
     * Checks if this list iterator has more elements when traversing the list in the forward direction.
     *
     * @return {@code true} if this list iterator when traversing the list in the forward direction
     */
    boolean hasNext();

    /**
     * Returns the next element in the list and advances the cursor position.
     *
     * @return {@link Optional} value of the next element in the list, if it exists,
     * otherwise {@link Optional#empty()}
     */
    Optional<T> next();

    /**
     * Removes from the list the element that is pointed by the cursor.
     *
     * @return {@link Optional} value of the removed element in the list, if it exists,
     * otherwise {@link Optional#empty()}
     */
    Optional<T> remove();
}
