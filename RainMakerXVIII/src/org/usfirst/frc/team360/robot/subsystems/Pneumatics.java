package org.usfirst.frc.team360.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team360.robot.*;
import org.usfirst.frc.team360.robot.commands.Pressurize;

public class Pneumatics extends Subsystem {
    Compressor comp = RobotMap.compressor;
    public void pressurize (){
    	comp.start();
    }
    public void stop(){
    	comp.stop();
    }
    public void initDefaultCommand() {
    	setDefaultCommand(new Pressurize());
    }
}