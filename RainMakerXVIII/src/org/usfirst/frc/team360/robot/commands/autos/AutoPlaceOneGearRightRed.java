package org.usfirst.frc.team360.robot.commands.autos;

import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoPlaceOneGearRightRed extends CommandGroup {

    public  AutoPlaceOneGearRightRed() {
    	addParallel(new ResetNavX());
    	addParallel(new SetGearIntakePositionCenter());
    	addSequential(new ShiftUp());
    	addSequential(new WaitCommand(.25));
    	addSequential(new PIDDriveStraight(-.95, 0, 89));
    	addSequential(new PIDNavXTurn(-60));
    	addSequential(new PIDDriveStraight(-.95, -60, 18));
    	addSequential(new PIDDriveStraight(-.5, -60, 4));
    	addSequential(new WaitCommand(.5)); 	
    	addSequential(new DropGear());
    	addSequential(new PIDDriveStraight(.95, -60, 24));
    }
}