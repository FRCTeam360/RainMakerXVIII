package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.*;
import org.usfirst.frc.team360.robot.commands.*;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeMotor extends Subsystem {
	VictorSP m_intakeMotor = RobotMap.intakeMotor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void setMotor(double speed){
		m_intakeMotor.set(-speed);
	}
	public void stopMotor(){
		m_intakeMotor.set(0);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
         setDefaultCommand(new SetIntakeSpeed());
    }
}

