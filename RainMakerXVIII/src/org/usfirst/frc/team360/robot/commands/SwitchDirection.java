package org.usfirst.frc.team360.robot.commands;



import org.usfirst.frc.team360.robot.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SwitchDirection extends Command {
	double timeWait = .02;
	Timer time;
    public SwitchDirection() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = new Timer();
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.oi.joyR.getRawAxis(1)) <= .3 && Math.abs(Robot.oi.joyL.getRawAxis(1)) <= .3){
    		timeWait = .25;
    		RobotMap.driveForward = ! RobotMap.driveForward;
    	} 
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeWait < time.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}