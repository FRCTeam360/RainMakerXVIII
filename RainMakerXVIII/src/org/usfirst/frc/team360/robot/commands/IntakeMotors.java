package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeMotors extends Command {

    public IntakeMotors() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.intakemotor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.dangerZone == false){
    		if(Robot.catapult.getCatapultPosition() == false){
    			//Robot.intakemotor.runMotor(.54);
    			Robot.intakemotor.runMotor(.75);
    		} else {
    			Robot.intakemotor.stop();
    		}
    	} else {
    		//Robot.intakemotor.runMotor(.54);
    		Robot.intakemotor.runMotor(.75);
    	}
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intakemotor.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}