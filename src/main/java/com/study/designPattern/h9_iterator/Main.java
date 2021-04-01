package com.study.designPattern.h9_iterator;

/**
 * Created on 2017. 10. 11..
 */
public class Main {

    public static void main(String[] args) {

        PancakeMenu pancakeMenu = new PancakeMenu();
        Iterator pancakeMenuIterator = pancakeMenu.createIterator();
        printMenu(pancakeMenuIterator);

        DinnerMenu dinnerMenu = new DinnerMenu();
        Iterator dinnerMenuIterator = dinnerMenu.createIterator();
        printMenu(dinnerMenuIterator);

    }

    public static void printMenu(Iterator i) {
        while(i.hasNext()) {
            MenuItem menuItem = (MenuItem)i.next();
            System.out.println(menuItem.getName());
        }
    }
}
