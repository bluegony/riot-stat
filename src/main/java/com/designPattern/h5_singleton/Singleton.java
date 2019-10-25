package com.designPattern.h5_singleton;

/**
 * Created on 2017. 10. 11..
 */
public class Singleton {
    private static Singleton uniqueInstance = new Singleton();  // 1. 정적생성자

    private Singleton() {

    }

    public static synchronized Singleton getInstance() {        // 2. 동기화 : 성능 문제
        return uniqueInstance;
    }

//    private volatile static Singleton uniqueInstance;         // 3. Double Checkinhg Locking
//    public static Singleton getInstance() {
//        if(uniqueInstance == null) {
//            synchronized (Singleton.class) {
//                if(uniqueInstance == null) {
//                    uniqueInstance = new Singleton()
//                }
//            }
//        }
//    }
}
