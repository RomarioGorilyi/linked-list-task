package com.innovecs.task;

import com.innovecs.task.list.LinkedList;
import com.innovecs.task.list.List;
import com.innovecs.task.service.ListService;
import com.innovecs.task.service.ListServiceImpl;

/**
 * @author Roman Horilyi
 */
public class LinkedListApplication {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(4);
        list.add(3);
        list.add(7);
        list.add(9);

        ListService listService = new ListServiceImpl();
        listService.reverseList(list);

        System.out.println("Reversed list: \n");
        System.out.println(listService.toString(list));
    }
}
