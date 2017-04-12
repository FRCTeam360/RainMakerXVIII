package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetGearIntakePositionCenter extends CommandGroup {

    public SetGearIntakePositionCenter() {
    	addSequential(new MoveGearIntake(197, 1));
    }
}
