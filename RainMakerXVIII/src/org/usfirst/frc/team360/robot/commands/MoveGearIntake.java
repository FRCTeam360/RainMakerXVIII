package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.*;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveGearIntake extends Command {
	double distance = 0;
	double sensitivity = 0;
    public MoveGearIntake(double distance, double sensitivity) {
    	this.sensitivity = sensitivity;
    	this.distance = distance;
    	requires(Robot.m_intakeHeight);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(Robot.m_intakeHeight.potentiometerValue() > distance){	
    		if(Math.abs(distance - Robot.m_intakeHeight.potentiometerValue()) > 5){
    			Robot.m_intakeHeight.setMotor(-.65);
    		} else {
    			Robot.m_intakeHeight.setMotor(-.2);
    		}
    	}else{
    		if(Math.abs(distance - Robot.m_intakeHeight.potentiometerValue()) > 5){
    			Robot.m_intakeHeight.setMotor(.65);
    		} else {
    			Robot.m_intakeHeight.setMotor(.2);
    		}
    	}
    	
    }

    protected boolean isFinished() {
        return Robot.m_intakeHeight.potentiometerValue() > distance - sensitivity && Robot.m_intakeHeight.potentiometerValue() < distance + sensitivity;
    }

    protected void end() {
    	Robot.m_intakeHeight.stopMotor();
    }

    protected void interrupted() {
    	end();
    }
}