package org.usfirst.frc.team360.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team360.robot.*;
/**
 *
 */
public class Shifter extends Subsystem {
    
//error here
	public static DoubleSolenoid shifter = RobotMap.shifter;  
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void open(){ 
		shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void close() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}