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
	VictorSP shooterMotor = RobotMap.shooterMotor;
	Encoder shooterEncoder = RobotMap.shooterEncoder;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public int getEnc(){
		return shooterEncoder.get();
	}
	public void resetEnc(){
		shooterEncoder.reset();
	}
	public void setMotor(double speed){
		shooterMotor.set(speed);
	}
	public void stopMotor(){
		shooterMotor.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new RunShooter());
    }
    
}

