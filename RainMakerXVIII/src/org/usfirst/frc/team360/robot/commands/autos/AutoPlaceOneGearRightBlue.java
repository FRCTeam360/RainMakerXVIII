package org.usfirst.frc.team360.robot.commands.autos;

import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoPlaceOneGearRightBlue extends CommandGroup {

    public AutoPlaceOneGearRightBlue() {

    	addParallel(new ResetNavX());
    	addParallel(new SetGearIntakePositionCenter());
    	addSequential(new ShiftUp());
    	addSequential(new WaitCommand(.25));
    	addSequential(new PIDDriveStraight(-.95, 0, 78));
    	addSequential(new PIDNavXTurn(-60));
    	addSequential(new PIDDriveStraight(-.5, -60, 27));
    	addSequential(new CameraAim());
    	addSequential(new WaitCommand(.5)); 	
    	addSequential(new DropGear());
    	addSequential(new PIDDriveStraight(.95, -60, 24));
    	addParallel(new SetGearIntakePositionUp());
    }
}
