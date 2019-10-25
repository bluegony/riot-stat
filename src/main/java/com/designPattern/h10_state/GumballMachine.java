package com.designPattern.h10_state;



/**
 * Created on 2017. 10. 12..
 */
public class GumballMachine {

    private State soldOutState;
    private State hasQuarterState;
    private State noQuarterState;
    private State soldState;

    private State state; // 현재상태
    private int count = 0;

    public GumballMachine(int count) {
        soldOutState = new SoldOutState(this);
        hasQuarterState = new HasQuarterState(this);
        noQuarterState = new NoQuarterState(this);
        soldState = new SoldState(this);

        this.count = count;
        if (count > 0) {
            state = noQuarterState;
        }
    }

    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {   // client 동작 한번에 상태 변화가 두번 생길수도 있다.
        state.turnCrank();
        state.dispense();
    }


    public void setState(State state) {
        this.state = state;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getHasQuarterState() {
        return hasQuarterState;
    }

    public State getNoQuarterState() {
        return noQuarterState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getState() {
        return state;
    }

    public int getCount() {
        return count;
    }
}
