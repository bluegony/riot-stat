package com.study.designPattern.h6_command;

/**
 * Created on 2017. 10. 12..
 */
public class StereOffCommand implements Command {

    Stereo stereo;

    public StereOffCommand(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        stereo.off();

    }

    @Override
    public void undo() {
        stereo.on();
        stereo.setCD();
        stereo.setVolume(11);

    }
}
