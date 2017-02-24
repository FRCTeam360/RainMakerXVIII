package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeUntilGearGrabbed extends Command {
	Timer time;
	double waitTime = .2;
	int i = 0;
    public IntakeUntilGearGrabbed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_intakeMotor);
    	requires(Robot.m_intakeHeight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	i = 0;
    	time = new Timer();
    	time.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_intakeMotor.setMotor(1);
    	if(RobotMap.pdp.getCurrent(11) > 7 && i <= 0){
    		time.start();
    		i++;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.pdp.getCurrent(11) > 7 && time.get() < waitTime;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_intakeHeight.stopMotor();
    	Robot.m_intakeMotor.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}