package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class TurnLightsOff extends Command {
    public TurnLightsOff() {
    	requires(Robot.lights);
    }
    
    protected void initialize() {
    	Robot.lights.lightsOff();
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