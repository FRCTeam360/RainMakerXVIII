package org.usfirst.frc.team360.robot.commands.autos;

import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoShootBoilerBlue extends CommandGroup {
    public AutoShootBoilerBlue() {
    	addParallel(new ResetNavX());
    	addParallel(new SetGearIntakePositionCenter());
    	addParallel(new ShiftUp());
    	addParallel(new PIDShooterSpeed());
    	addSequential(new WaitCommand(4.5));
    	addSequential(new OpenShooterPneumatic());
    	addSequential(new WaitCommand(5.5));
    	addSequential(new PIDDriveStraight(-.95, 0, 30));
		addSequential(new PIDNavXTurnSuperLoose(-70));
		addSequential(new PIDDriveStraight(-.95, -70, 80));
		addSequential(new ShiftDown());
    }
}
