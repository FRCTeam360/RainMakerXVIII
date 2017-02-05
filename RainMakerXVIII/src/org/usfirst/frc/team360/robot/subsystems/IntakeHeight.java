package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.RobotMap;
import org.usfirst.frc.team360.robot.commands.SetIntakeHeight;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeHeight extends Subsystem {
	VictorSP m_intakeHeight = RobotMap.intakeHeight;
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
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new SetIntakeHeight());
    }
    
}

