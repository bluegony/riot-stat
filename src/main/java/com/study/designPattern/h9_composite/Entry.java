package com.study.designPattern.h9_composite;

/**
 * Created on 2017. 9. 26..
 */
public abstract class Entry {
    public abstract String getName();
    public abstract int getSize();
    protected void printList() {
        printList("/");
    }
    protected abstract void printList(String prefix);
    public String toString() {
        return getName() + "(" + getSize()+")";
    }
    public Entry add(Entry entry) {
        throw new RuntimeException();
    }
}
