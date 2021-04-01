package com.study.designPattern.h9_iterator.old;

import java.util.ArrayList;

/**
 * Created on 2017. 10. 11..
 */
public class Main {

    public static void main(String[] args) {

        PancakeMenu pancakeMenu = new PancakeMenu();
        ArrayList<MenuItem> pancakeMenuItems = pancakeMenu.getMenuItems();

        DinnerMenu dinnerMenu = new DinnerMenu();
        MenuItem[] dinnerMenuItems = dinnerMenu.getMenuItems();

        for(MenuItem menuItem : pancakeMenuItems) {
            System.out.println(menuItem.getName());
        }

        for(int i = 0; i<dinnerMenu.getIndex() ; i++) {
            MenuItem menuItem = dinnerMenuItems[i];
            System.out.println(menuItem.getName());
        }

    }
}
