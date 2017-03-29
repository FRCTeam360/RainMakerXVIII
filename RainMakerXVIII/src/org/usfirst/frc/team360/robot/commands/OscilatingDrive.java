package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class OscilatingDrive extends CommandGroup {
	
    public OscilatingDrive() {
    	addSequential(new BackAndForthDriveLeftOneWay(0));
    	addSequential(new BackAndForthDriveRightFullWay(0));
    	addSequential(new BackAndForthDriveLeftFullWay(0));
    	addSequential(new BackAndForthDriveRightOneWay(0));
    }
}