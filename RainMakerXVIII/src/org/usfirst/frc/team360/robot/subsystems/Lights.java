package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.*;
import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Lights extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void lightsOn(){
		RobotMap.lights.set(Relay.Value.kForward);
	}
	public void lightsOff(){
		RobotMap.lights.set(Relay.Value.kReverse);
	}
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TurnLightsOff());
    }
}