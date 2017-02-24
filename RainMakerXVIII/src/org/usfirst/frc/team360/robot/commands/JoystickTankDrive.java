package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team360.robot.*;
/**
 *
 */
public class JoystickTankDrive extends Command {

    public JoystickTankDrive() {
    	requires(Robot.drivetrain); 
    }

    protected void initialize() {
    }
    
    protected void execute() {
    	if(RobotMap.driveForward){
	    	if(Math.abs(OI.joyR.getRawAxis(1)) >= .01){
	    		Robot.drivetrain.driveR(OI.joyR.getRawAxis(1));
	    	} else {
	    		Robot.drivetrain.driveR(0);
	    	}
	    	if(Math.abs(OI.joyL.getRawAxis(1)) >= .01){
	    		Robot.drivetrain.driveL(OI.joyL.getRawAxis(1));
	    	} else {
	    		Robot.drivetrain.driveL(0);
	    	}
	    } else {
	    	if(Math.abs(OI.joyL.getRawAxis(1)) >= .01){
	    		Robot.drivetrain.driveR(-OI.joyL.getRawAxis(1));
	    	} else {
	    		Robot.drivetrain.driveR(0);
	    	}
	    	if(Math.abs(OI.joyR.getRawAxis(1)) >= .01){
	    		Robot.drivetrain.driveL(-OI.joyR.getRawAxis(1));
	    	} else {
	    		Robot.drivetrain.driveL(0);
	    	}
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.drivetrain.stopR();
    	Robot.drivetrain.stopL();
    }
    
    protected void interrupted() {
    	end();
    }
}