package org.usfirst.frc.team360.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team360.robot.*;
import org.usfirst.frc.team360.robot.commands.JoystickTankDrive;
//import org.usfirst.frc.team360.robot.commands.JoystickTankDrive;
/**
 *
 */
public class DriveTrain extends Subsystem {
	VictorSP motorR1 = RobotMap.motorR1;
	VictorSP motorR2 = RobotMap.motorR2;
	VictorSP motorL1 = RobotMap.motorL1;
	VictorSP motorL2 = RobotMap.motorL2;
	Encoder encR = RobotMap.encR;
	Encoder encL = RobotMap.encL;

	// Put methods for controlling this subsystem
    // here. Call these from Commands.
	public DriveTrain(){
		encL.reset();
		encR.reset();
	}
	public void resetRHardEnc(){
		encR.reset();
	}
	public void resetLHardEnc(){
		encL.reset();
	}
	public void resetEncs(){
		encL.reset();
		encR.reset();
	}
	public int getRSoftEnc(){
		return encR.get();
	}
	public int getLSoftEnc(){
		return encR.get();
	}
	public int getRHardEnc(){
		return encR.get();
	}
	public int getLHardEnc(){
		return encR.get();
	}
	public void softResetR(){
		RobotMap.encRReset = getRHardEnc();
			
	}
	public void softResetL(){
		RobotMap.encLReset = getLHardEnc();
	}
	
	public void drive(double RMotor, double LMotor) {
		  motorR1.set(RMotor);
		  motorR2.set(RMotor);
		  motorL1.set(-LMotor);
		  motorL2.set(-LMotor);
	  }
	  public void driveR(double RMotor){
		  motorL1.set(RMotor);
		  motorL2.set(RMotor);
	  }
	  public void driveL(double LMotor){
		  motorR1.set(-LMotor);
		  motorR2.set(-LMotor);
	  }
	  public void stopR(){
		  motorR1.stopMotor();
		  motorR2.stopMotor();  
	  }
	  public void stopL(){
		  motorL1.stopMotor();
		  motorL2.stopMotor();
	  }
	  public void stop(){
		  motorL1.stopMotor();
		  motorL2.stopMotor();
		  motorR1.stopMotor();
		  motorR2.stopMotor(); 
	  }
    public void initDefaultCommand() {
    	setDefaultCommand(new JoystickTankDrive());
    }
}