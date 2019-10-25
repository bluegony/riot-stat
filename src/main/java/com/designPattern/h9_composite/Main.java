package com.designPattern.h9_composite;

/**
 * Created on 2017. 10. 11..
 */
public class Main {

    public static void main(String[] args) {
        Entry root = new Directory("root");
        Entry bin = new Directory("bin");
        root.add(bin);
        bin.add(new File("vi", 10000));
        root.printList();
        Entry user = new Directory("user");
        root.add(user);
        user.add(new File("userlist", 5000));
        user.add(new File("userdata", 20000));
        root.printList();
    }
}
