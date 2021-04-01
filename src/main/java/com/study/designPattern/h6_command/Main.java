package com.study.designPattern.h6_command;

/**
 * Created on 2017. 10. 12..
 */
public class Main {

    public static void main(String[] args) {
        SimpleRemoteControl remote = new SimpleRemoteControl();
        Light light = new Light();
        Stereo stereo = new Stereo();
        testRemote(remote, new LightOnCommand(light));
        testRemote(remote, new LightOffCommand(light));
        testRemote(remote, new StereoOnCommand(stereo));
        testRemote(remote, new StereOffCommand(stereo));

    }

    private static void testRemote(SimpleRemoteControl remote, Command command) {
        remote.setSlot(command);
        remote.buttonPressed();
        remote.undoButtonPressed();
    }
}
