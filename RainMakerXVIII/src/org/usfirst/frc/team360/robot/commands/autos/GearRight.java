
package org.usfirst.frc.team360.robot.commands.autos;

import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class GearRight extends CommandGroup {

    public  GearRight() {
    	addParallel(new ResetNavX());
    	addParallel(new MoveGearIntake(258, 1));
    	addSequential(new ShiftUp());
    	addSequential(new WaitCommand(.25));
    	addSequential(new DriveStraightPID(-.95, 0, 82));
    	addSequential(new PIDNavXTurn(-60));
    	addSequential(new DriveStraightPID(-.95, -60, 18));
    	addSequential(new DriveStraightPID(-.5, -60, 4));
    	addSequential(new WaitCommand(.5)); 	
    	addSequential(new DropGear());
    	addSequential(new PIDNavXTurn(-40));
    	addParallel(new MoveGearIntake(175, 1));
    	addSequential(new DriveStraightPID(.95, -40, 108));
    }
}