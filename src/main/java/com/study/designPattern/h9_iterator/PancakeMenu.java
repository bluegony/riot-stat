package com.study.designPattern.h9_iterator;

import java.util.ArrayList;

/**
 * Created on 2017. 10. 11..
 */
public class PancakeMenu {
    ArrayList menuItems;

    public PancakeMenu() {
        menuItems = new ArrayList();
        addItem("달걀 팬케이크");
        addItem("소시지 팬케이크");
    }

    public void addItem(String name) {
        MenuItem menuItem = new MenuItem(name);
        menuItems.add(menuItem);
    }

    public ArrayList getMenuItems() {
        return menuItems;
    }

    public Iterator createIterator() {
        return new PancakeMenuIterator(menuItems);

        // java.util.Iterator 쓰면 그냥 이렇게하면된다.
        // Collection 객체들은 Iterator를 얻을수 있도록 Iterable을 구현하고 있다.
//        return menuItems.iterator();
    }
}
