package org.usfirst.frc.team360.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team360.robot.*;

public class ShooterPnuematic extends Subsystem {
    
//error here
	public static DoubleSolenoid shooterPnuematic = RobotMap.shooterThing;
	public void open(){ 
		shooterPnuematic.set(DoubleSolenoid.Value.kForward);
	}
	public void close() {
		shooterPnuematic.set(DoubleSolenoid.Value.kReverse);
	}
    public void initDefaultCommand() {
    }
}