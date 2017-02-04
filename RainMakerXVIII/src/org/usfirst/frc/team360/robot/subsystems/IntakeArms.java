package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeArms extends Subsystem {
	
	public static DoubleSolenoid IntakeArms = RobotMap.intakeArms; 
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void open(){ 
		IntakeArms.set(DoubleSolenoid.Value.kForward);
	}
	public void close() {
		IntakeArms.set(DoubleSolenoid.Value.kReverse);
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}