package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CameraAim extends CommandGroup {
	
    public  CameraAim() {
    	
    	addParallel(new TurnLightsOn());
    	addParallel(new ShiftUp());
    	addSequential(new WaitCommand(1.4));
    	addSequential(new WaitForGoodCameraValue());
    	addSequential(new CameraPIDAim());
    	addSequential(new WaitCommand(.2));
    	
    }
}