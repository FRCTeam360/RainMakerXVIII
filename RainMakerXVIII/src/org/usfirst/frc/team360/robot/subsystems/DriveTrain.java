package org.usfirst.frc.team360.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team360.robot.*;
import org.usfirst.frc.team360.robot.commands.JoystickTankDrive;
public class DriveTrain extends Subsystem {
	VictorSP motorR1 = RobotMap.motorR1;
	VictorSP motorR2 = RobotMap.motorR2;
	VictorSP motorL1 = RobotMap.motorL1;
	VictorSP motorL2 = RobotMap.motorL2;
	Encoder driveTrainEncoderLeft = RobotMap.driveTrainEncoderLeft;
	Encoder driveTrainEncoderRight = RobotMap.driveTrainEncoderRight;

	public DriveTrain(){
		driveTrainEncoderLeft.reset();
		driveTrainEncoderRight.reset();
	}
	public void resetRHardEnc(){
		driveTrainEncoderRight.reset();
	}
	public void resetLHardEnc(){
		driveTrainEncoderLeft.reset();
	}
	public void resetEncs(){
		driveTrainEncoderLeft.reset();
		driveTrainEncoderRight.reset();
	}
	public int getRSoftEnc(){
		return driveTrainEncoderRight.get() - RobotMap.driveTrainEncoderRightReset;
	}
	public int getLSoftEnc(){
		return driveTrainEncoderLeft.get() - RobotMap.driveTrainEncoderLeftReset;
	}
	public int getRHardEnc(){
		return driveTrainEncoderRight.get();
	}
	public int getLHardEnc(){
		return driveTrainEncoderLeft.get();
	}
	public void softResetR(){
		RobotMap.driveTrainEncoderRightReset = getRHardEnc();
			
	}
	public void softResetL(){
		RobotMap.driveTrainEncoderLeftReset = getLHardEnc();
	}
	
	public void drive(double RMotor, double LMotor) {
		  motorR1.set(-RMotor);
		  motorR2.set(-RMotor);
		  motorL1.set(LMotor);
		  motorL2.set(LMotor);
	  }
	  public void driveR(double RMotor){
		  motorR1.set(-RMotor);
		  motorR2.set(-RMotor);
	  }
	  public void driveL(double LMotor){
		  motorL1.set(LMotor);
		  motorL2.set(LMotor);
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
    
    public void checkCurrent() {
  
   
    }
    
}

