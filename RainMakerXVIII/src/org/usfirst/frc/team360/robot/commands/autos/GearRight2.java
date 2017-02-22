
package org.usfirst.frc.team360.robot.commands.autos;

import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearRight2 extends CommandGroup {

    public  GearRight2() {
    	addParallel(new ResetNavX());
    	addParallel(new MoveGearIntake(95, 1));
    	addSequential(new ShiftUp());
    	addSequential(new WaitCommand(.25));
    	addSequential(new DriveStraightPID(-.95, 0, 89));
    	addSequential(new PIDNavXTurn(-60));
    	addSequential(new DriveStraightPID(-.95, 0, 18));
    	addSequential(new DriveStraightPID(-.5, 0, 4));
    	addSequential(new WaitCommand(.5)); 	
    	addSequential(new DropGear());
    	addSequential(new DriveStraightPID(.95, 0, 24));
    }
}