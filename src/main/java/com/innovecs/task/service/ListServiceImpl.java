package com.innovecs.task.service;

import com.innovecs.task.iterator.Iterator;
import com.innovecs.task.list.List;

import java.util.StringJoiner;

/**
 * @author Roman Horilyi
 */
public class ListServiceImpl implements ListService {

    @Override
    public <T> void reverseList(List<T> list) {
        T[] listValues = removeAllListValuesToArray(list);
        for (T value : listValues) {
            list.addFirst(value);
        }
    }

    @Override
    public <T> String toString(List<T> list) {
        String elementsDelimiter = ", ";
        String prefix = "[";
        String suffix = "]";
        StringJoiner stringJoiner = new StringJoiner(elementsDelimiter, prefix, suffix);
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            iterator.next().ifPresent(value -> stringJoiner.add(value.toString()));
        }
        return stringJoiner.toString();
    }


    @SuppressWarnings("unchecked")
    private <T> T[] removeAllListValuesToArray(List<T> list) {
        Object[] listValues = new Object[list.size()];
        Iterator<T> iterator = list.iterator();
        int arrayIndex = 0;
        while (iterator.hasNext()) {
            int i = arrayIndex; // effectively final index
            iterator.remove().ifPresent(value -> listValues[i] = value);
            arrayIndex++;
        }

        return (T[]) listValues;
    }
}
