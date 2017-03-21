package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class CameraAim extends CommandGroup {
	
    public CameraAim() {
    	//addParallel(new ResetNavX());
    	addParallel(new TurnLightsOn());
    	addParallel(new ShiftUp());
    	addSequential(new SetGearIntakePositionCenter());
    	addSequential(new WaitForGoodCameraValue());
    	addSequential(new PIDCameraAim());
    	addSequential(new PIDCameraDriveStraight());
    }
}