package org.usfirst.frc.team360.robot.commands.autos;

import org.usfirst.frc.team360.robot.commands.PIDDriveStraight;
import org.usfirst.frc.team360.robot.commands.ResetNavX;
import org.usfirst.frc.team360.robot.commands.SetGearIntakePositionCenter;
import org.usfirst.frc.team360.robot.commands.ShiftDown;
import org.usfirst.frc.team360.robot.commands.ShiftUp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoDriveStraight extends CommandGroup {

    public AutoDriveStraight() {
    	addParallel(new ResetNavX());
    	addParallel(new SetGearIntakePositionCenter());
    	addSequential(new ShiftUp());
    	addSequential(new WaitCommand(.25));
    	addSequential(new PIDDriveStraight(-.95, 0, 63));
    	addSequential(new ShiftDown());
    }
}
