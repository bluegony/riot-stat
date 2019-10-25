package com.designPattern.h9_iterator;

/**
 * Created on 2017. 10. 11..
 */
public class DinnerMenu {
    MenuItem[] menuItems;
    int index = 0;
    static final int max_items = 6;

    public DinnerMenu() {
        menuItems = new MenuItem[max_items];
        addItem("베이컨 스테이크");
        addItem("핫도그");

    }
    public void addItem(String name) {
        MenuItem menuItem = new MenuItem(name);
        if(index >=max_items) {
            System.out.println("메뉴개수초과");
        } else {
            menuItems[index] = menuItem;
            index += 1;
        }
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public int getIndex() {
        return index;
    }
    public Iterator createIterator() {
        return new DinnerMenuIterator(menuItems);
    }
}
