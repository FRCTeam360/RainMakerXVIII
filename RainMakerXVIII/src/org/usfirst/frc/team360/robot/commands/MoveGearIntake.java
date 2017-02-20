package org.usfirst.frc.team360.robot.commands;



import org.usfirst.frc.team360.robot.OI;
import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGearIntake extends Command {
	double distance = 0;
	double sensitivity = 0;
    public MoveGearIntake(double distance, double sensitivity) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.sensitivity = sensitivity;
    	this.distance = distance;
    	requires(Robot.m_intakeHeight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.m_intakeHeight.potentiometerValue() > distance){
        	Robot.m_intakeHeight.setMotor(.65);
    	}else{
    	Robot.m_intakeHeight.setMotor(-.65);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.m_intakeHeight.potentiometerValue() > distance - sensitivity && Robot.m_intakeHeight.potentiometerValue() < distance + sensitivity;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_intakeHeight.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}