package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TurnLightsOn extends Command {

    public TurnLightsOn() {
    	requires(Robot.lights);
    }

    protected void initialize() {
    	Robot.lights.lightsOn();
    }

    protected void execute() {

    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {

    }

    protected void interrupted() {

    }
}