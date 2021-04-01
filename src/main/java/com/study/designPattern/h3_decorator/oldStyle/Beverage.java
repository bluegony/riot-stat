package com.study.designPattern.h3_decorator.oldStyle;

/**
 * Created on 2017. 10. 10..
 */
public class Beverage {
    protected String description;
    protected boolean milk;
    protected boolean soy;
    protected boolean mocha;
    protected boolean whip;

    protected  String getDescription() {
        return description;
    }

    // 첨가물 변동이 생기면 코드를 수정해야한다 . 같은 첨가물을 두번 이상 추가하려고 하면 또 수정해야한다.
    public int cost() {
        int defaultCost = 2000;
        if(milk) {
            defaultCost += 500;
        }
        if(soy) {
            defaultCost += 500;
        }
        if(mocha) {
            defaultCost += 500;
        }
        if(whip) {
            defaultCost += 500;
        }

        return defaultCost;
    }


}
