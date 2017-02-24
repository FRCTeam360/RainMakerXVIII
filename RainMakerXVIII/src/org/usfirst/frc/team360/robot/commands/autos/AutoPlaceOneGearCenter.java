
package org.usfirst.frc.team360.robot.commands.autos;

import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoPlaceOneGearCenter extends CommandGroup {

    public  AutoPlaceOneGearCenter() {
    	addParallel(new ResetNavX());
    	addParallel(new MoveGearIntake(95, 1));
    	addSequential(new ShiftUp());
    	addSequential(new WaitCommand(.25));
    	addSequential(new PIDDriveStraight(-.95, 0, 63));
    	addSequential(new PIDDriveStraight(-.5, 0, 4));
    	addSequential(new WaitCommand(.5));
    	addSequential(new DropGear());
    	addSequential(new PIDDriveStraight(.95, 0, 24));
//    	addSequential(new DriveStraightPID(.95, 180, 2382));
//    	addSequential(new PIDNavXTurn(245));
//    	addSequential(new DriveStraightPID(.95, 245, 3386));
//    	addSequential(new PIDNavXTurn(180));
    }
}