package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.*;
import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class IntakeHeight extends Subsystem {
	VictorSP m_intakeHeight = RobotMap.intakeHeight;
	Potentiometer potentiometer = RobotMap.pot;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public IntakeHeight(){
		m_intakeHeight.setInverted(true);
	}
	public void setMotor(double speed){
		m_intakeHeight.set(speed);
	}
	public void stopMotor(){
		m_intakeHeight.set(0);
	}
	public double potentiometerValue(){
		return potentiometer.get();
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
         setDefaultCommand(new SetIntakeHeight());
    }
}