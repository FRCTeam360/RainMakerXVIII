package org.usfirst.frc.team360.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team360.robot.*;
import org.usfirst.frc.team360.robot.commands.Pressurize;

public class Pneumatics extends Subsystem {
    Compressor comp = RobotMap.compressor;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void pressurize (){
    	comp.start();
    }
    public void stop(){
    	comp.stop();
    }
    public void initDefaultCommand() {
    	setDefaultCommand(new Pressurize());
        // Set the default command for a subsystem here.
    }
}