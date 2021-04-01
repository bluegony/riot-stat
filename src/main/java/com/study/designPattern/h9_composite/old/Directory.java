package com.study.designPattern.h9_composite.old;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created on 2017. 9. 26..
 */
public class Directory {
    private String name;
    private ArrayList directory = new ArrayList();

    public Directory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    // 1. arrayList에  File 객체도 넣어 재귀함수를 쓸 수 있도록 하려면 File과 Directory의 상위 class인 entry를 만들면 된다.
    // Entry에도 add함수가 있어야 사용할때 형변환할필요없이 사용가능.
    public Directory add(Directory entry) {
        directory.add(entry);
        return this;
    }
    // 2. getSize와 printList는  file과 Directory와 구현이 다르므로 Entry class에서 abstract method로 선언하고 하위 class에서 구현해준다
    // file은 그냥 값을 return, directory는 재귀함수로 구현
    public int getSize() {
        int size = 0;
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            size += ((Directory)it.next()).getSize();
        }
        return size;
    }
    protected void printList(String prefix) {
        System.out.println(prefix + "/" + this);
        Iterator it = directory.iterator();
        while (it.hasNext()) {
            ((Directory)it.next()).printList(prefix + "/" + this);
        }
    }
}
