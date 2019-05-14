package com.innovecs.task.list;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Roman Horilyi
 */
public class Node<T> {
    @Getter
    private T data;
    @Getter @Setter
    private Node<T> nextNode;

    public Node(T data) {
        this.data = data;
    }
}
