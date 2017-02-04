package org.usfirst.frc.team360.robot.subsystems;
import org.usfirst.frc.team360.robot.RobotMap;
import org.usfirst.frc.team360.robot.commands.CatapultDown;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Catapult extends Subsystem {
    
	public static DoubleSolenoid CatapultTusks = RobotMap.catapultTusks;
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void open(){ 
		CatapultTusks.set(DoubleSolenoid.Value.kForward);
		
	}
	public void close() {
		CatapultTusks.set(DoubleSolenoid.Value.kReverse);
	}
	public boolean getCatapultPosition() {
		return RobotMap.catapult.get();
	}
  
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CatapultDown());
    }
}