package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team360.robot.*;
/**
 *
 */
public class SetIntakeHeight extends Command {
	boolean ShouldBeStopped;
	
	public SetIntakeHeight() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_intakeHeight);
    	//super("JoystickTankDrive");
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(OI.joyOI.getRawAxis(1)) >= .01){
    		Robot.m_intakeHeight.setMotor(OI.joyOI.getRawAxis(1)*.5);
    	} else {
   		Robot.m_intakeHeight.stopMotor();
    	}
    	SmartDashboard.putNumber("Intake Height Motor PDP (amps)", RobotMap.pdp.getCurrent(15));
    	if (RobotMap.pdp.getCurrent(15) > 4.5 || ShouldBeStopped == true)  {
    		Robot.m_intakeHeight.stopMotor();
    		ShouldBeStopped = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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