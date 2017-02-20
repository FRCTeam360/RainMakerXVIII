package org.usfirst.frc.team360.robot.commands;



import org.usfirst.frc.team360.robot.OI;
import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeUntilGearGrabbed extends Command {
    public IntakeUntilGearGrabbed() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_intakeMotor);
    	requires(Robot.m_intakeHeight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_intakeMotor.setMotor(1);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return RobotMap.pdp.getCurrent(12) > 7;
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