package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeArmUp extends Command {
 Timer time;
    public IntakeArmUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	time = new Timer(); 
    	time.reset();
    	time.start();

    	requires(Robot.intakearm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(RobotMap.dangerZone == false){
    		if(Robot.catapult.getCatapultPosition() == false){
    			Robot.intakearm.open();
    		}
    	} else {
    		Robot.intakearm.open();
    	}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return time.get() > .6;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}