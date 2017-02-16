package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.RobotMap;
import org.usfirst.frc.team360.robot.commands.*;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
	VictorSP m_dualWheelShooterMotor = RobotMap.dualWheelShooterMotor;
	Encoder m_dualWheelEncoder = RobotMap.dualWheelShooterEncoder;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public int getEnc(){
		return m_dualWheelEncoder.get();
	}
	public void resetEnc(){
		m_dualWheelEncoder.reset();
	}
	public void setMotor(double speed){
		m_dualWheelShooterMotor.set(speed);
	}
	public void stopMotor(){
		m_dualWheelShooterMotor.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RunShooter());
    }
    
}

