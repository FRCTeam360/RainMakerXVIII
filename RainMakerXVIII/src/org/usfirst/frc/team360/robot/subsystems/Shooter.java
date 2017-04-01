package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.*;

import com.ctre.CANTalon;
import com.ctre.CANTalon.VelocityMeasurementPeriod;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	CANTalon shooterMotor = RobotMap.shootingMotor;
	public Shooter(){

		shooterMotor.SetVelocityMeasurementPeriod(VelocityMeasurementPeriod.Period_20Ms);
	}
	public int getEnc(){
		return shooterMotor.getEncPosition();
	}
	public double getVelocity(){
		return shooterMotor.getEncVelocity();
	}
	public void resetEnc(){
		shooterMotor.setEncPosition(0);
	}
	public void setMotor(double speed){
		shooterMotor.set(speed);
	}
	public void stopMotor(){
		shooterMotor.set(0);
	}
	
    public void initDefaultCommand() {
    }
    
}

