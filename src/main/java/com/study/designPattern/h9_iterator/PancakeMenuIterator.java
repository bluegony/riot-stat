package com.study.designPattern.h9_iterator;

import java.util.ArrayList;

/**
 * Created on 2017. 10. 11..
 */
public class PancakeMenuIterator implements Iterator {
    ArrayList<MenuItem> items;
    int position = 0;

    public PancakeMenuIterator(ArrayList items) {
        this.items = items;
    }

    @Override
    public boolean hasNext() {
        if(position >= items.size() || items.get(position) == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        MenuItem menuItem = items.get(position);
        position = position +1;
        return menuItem;

    }
}
