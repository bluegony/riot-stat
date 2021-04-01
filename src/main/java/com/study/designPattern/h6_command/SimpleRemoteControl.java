package com.study.designPattern.h6_command;

/**
 * Created on 2017. 10. 12..
 */
public class SimpleRemoteControl {

    Command slot; // slot은 여러개가 있을수도 있다.

    public void buttonPressed() {
        slot.execute();
    }   // slot이 늘어나면 buttonPressed는 slot번호를 parameter로 받는다
    public void undoButtonPressed() {
        slot.undo();
    }

    public void setSlot(Command slot) {
        this.slot = slot;
    }
}
