package org.usfirst.frc.team360.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team360.robot.*;

public class Shifter extends Subsystem {
    
//error here
	public static DoubleSolenoid shifter = RobotMap.shifter;
	public void open(){ 
		shifter.set(DoubleSolenoid.Value.kForward);
	}
	public void close() {
		shifter.set(DoubleSolenoid.Value.kReverse);
	}
    public void initDefaultCommand() {
    }
}