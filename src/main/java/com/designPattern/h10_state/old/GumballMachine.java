package com.designPattern.h10_state.old;


import static com.designPattern.h10_state.old.Status.HAS_QUARTER;

/**
 * Created on 2017. 10. 12..
 */
public class GumballMachine {

    Status status = Status.SOLD_OUT;
    int count = 0;

    public GumballMachine(int count) {
        this.count = count;
        if(count>0) {
            status = Status.NO_QUATTER;
        }
    }

    public void insertQuarter() {
        switch (status) {
            case HAS_QUARTER:
                System.out.println("동전은한개만넣으세요");
                break;
            case NO_QUATTER:
                status = HAS_QUARTER;
                System.out.println("동전을 넣으셨습니다");
                break;
            case SOLD_OUT:
                System.out.println("매진되었습니다 다음에이용해주세요");
                break;
            case SOLD:
                System.out.println("잠시만 기다려주세요. 상품이 나가고있습니다.");
                break;
        }
    }
    public void ejectQuarter() {

    }
    public void turnCrank() {

    }
    public void dispense() {

    }
}
