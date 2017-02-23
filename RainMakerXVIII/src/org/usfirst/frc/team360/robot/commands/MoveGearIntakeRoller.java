package org.usfirst.frc.team360.robot.commands;



import org.usfirst.frc.team360.robot.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGearIntakeRoller extends Command {
	double timeWait = 0;
	double speed = 0;
	Timer time;
    public MoveGearIntakeRoller(double speed, double timeWait) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_intakeMotor);
    	this.timeWait = timeWait;
    	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = new Timer();
    	time.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_intakeMotor.setMotor(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return timeWait < time.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_intakeMotor.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}