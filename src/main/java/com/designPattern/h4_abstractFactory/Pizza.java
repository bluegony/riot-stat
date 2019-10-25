package com.designPattern.h4_abstractFactory;

/**
 * abstract factory 는 factory method 여러개를 한 interface로 묶어서 여러 부품을 일괄 교체하기 쉽도록 만들어놓은 조합이다.
 * head first 예제는 factory method 패턴 예제랑 섞여서 너무 복잡...
 *
 * Created on 2017. 10. 11..
 */
abstract class Pizza {

    protected String name = "pizza";
    Dough dough;
    Sauce sauce;
    Cheese cheese;

     abstract void prepare();


     void bake(){
         System.out.println("bake " + name );

     }
     void cut(){
         System.out.println("cut " + name );

     }
     void box(){
         System.out.println("box " + name );
         System.out.println( );

     }

}
