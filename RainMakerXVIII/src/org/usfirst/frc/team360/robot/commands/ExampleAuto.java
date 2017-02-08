
package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ExampleAuto extends CommandGroup {

    public  ExampleAuto() {

    	addSequential(new ShiftUp());

    	addSequential(new WaitCommand(.25));

    	addSequential(new DriveStraightPID(.95, 180, 200));

    	addSequential(new DriveStraightPID(.95, 180, 2382));

    	addSequential(new PIDNav(245));

    	addSequential(new DriveStraightPID(.95, 245, 3386));

    	addSequential(new PIDNav(180));
    	
    }

}