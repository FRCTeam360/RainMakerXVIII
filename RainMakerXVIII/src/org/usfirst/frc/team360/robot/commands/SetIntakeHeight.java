package org.usfirst.frc.team360.robot.commands;

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
    	Robot.m_intakeHeight.setMotor(.5);
    	SmartDashboard.putNumber("PDP (amps)", RobotMap.pdp.getCurrent(15));
    	SmartDashboard.putNumber("Potentiometer", RobotMap.pot.get());
    	if (RobotMap.pdp.getCurrent(15) > 1.5 || ShouldBeStopped == true)  {
    		Robot.m_intakeHeight.stopMotor();
    		ShouldBeStopped = true;
    	} else if (RobotMap.pot.get() >= 120) {
    		Robot.m_intakeHeight.stopMotor();
    		ShouldBeStopped = true;
    	} else if (RobotMap.pot.get() <= 80) {
    		Robot.m_intakeHeight.stopMotor();
    		ShouldBeStopped = true;
    	} else {
    		Robot.m_intakeHeight.setMotor(.5);
    	}
    	/*if (RobotMap.pdp.getCurrent(15) >= 4.5 || ShouldBeStopped == true)  {
    		Robot.m_intakeHeight.stopMotor();
    		ShouldBeStopped = true;
    	}
    	*/
    	/*SmartDashboard.putNumber("Intake Height Motor PDP (amps)", RobotMap.pdp.getCurrent(15));
    	if (RobotMap.pdp.getCurrent(15) > 4.5 || ShouldBeStopped == true)  {
    		Robot.m_intakeHeight.stopMotor();
    		ShouldBeStopped = true;
    	}
    	SmartDashboard.putNumber("Potentiometer", RobotMap.pot.get());
    	if  (RobotMap.pot.get() >= 10) {
    		Robot.m_intakeHeight.stopMotor();
    	}*/
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