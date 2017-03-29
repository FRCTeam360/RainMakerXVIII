package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team360.robot.*;

public class SetIntakeHeight extends Command {
	boolean ShouldBeStopped;
	
	public SetIntakeHeight() {
        requires(Robot.m_intakeHeight);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(Math.abs(OI.joyOI.getRawAxis(1)) >= .01){
    		Robot.m_intakeHeight.setMotor(OI.joyOI.getRawAxis(1)*.5);
    	} else {
   		Robot.m_intakeHeight.stopMotor();
    	}
    	SmartDashboard.putNumber("intake speed", RobotMap.intakeHeight.get());
    	SmartDashboard.putNumber("Intake Height Motor PDP (amps)", RobotMap.pdp.getCurrent(15));
//    	if (RobotMap.pdp.getCurrent(15) > 4.5 || ShouldBeStopped == true)  {
//    		Robot.m_intakeHeight.stopMotor();
//    		ShouldBeStopped = true;
//    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.m_intakeHeight.stopMotor();
    }

    protected void interrupted() {
    	end();
    }
}