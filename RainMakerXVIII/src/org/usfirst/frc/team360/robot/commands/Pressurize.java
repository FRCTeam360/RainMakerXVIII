package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class Pressurize extends Command {

    public Pressurize() {
    	requires (Robot.pneumatics);
    	
    }

    protected void initialize() {

    }

    protected void execute() {
    	Robot.pneumatics.pressurize(); 
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.pneumatics.stop(); 
    }

    protected void interrupted() {
    	end();
    }
}