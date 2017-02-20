package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team360.robot.*;
/**
 *
 */
public class JoystickTankDrive extends Command {

    public JoystickTankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//super("JoystickTankDrive");
    	requires(Robot.drivetrain); 
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
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

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stopR();
    	Robot.drivetrain.stopL();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}