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
    	requires(Robot.m_intakeMotor);
    	requires(Robot.m_intakeHeight);
    }

    protected void initialize() {
    	i = 0;
    	time = new Timer();
    	time.reset();
    }

    protected void execute() {
    	Robot.m_intakeMotor.setMotor(1);
    	if(RobotMap.pdp.getCurrent(5) > 7 && i <= 0){
    		time.start();
    		i++;
    	}
    }

    protected boolean isFinished() {
        return RobotMap.pdp.getCurrent(5) > 7 && time.get() < waitTime;
        
    }

    protected void end() {
    	Robot.m_intakeHeight.stopMotor();
    	Robot.m_intakeMotor.stopMotor();
    }
    
    protected void interrupted() {
    	end();
    }
}