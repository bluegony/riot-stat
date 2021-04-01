package com.study.designPattern.h3_decorator.oldStyle;

/**
 * Created on 2017. 10. 10..
 */
public class DarkRoast extends Beverage {

    // 이 클래스 자체도 Beverage에서 boolean등을 사용하여 공통으로 구현할수도 있지만. 변경이 생기면 Beverage 코드 수정이 필요해진다

    public DarkRoast() {
        this.description = "최고의 다크로스트 커피.";
    }
    public int cost() {
        return 1000+super.cost();
    }
}
