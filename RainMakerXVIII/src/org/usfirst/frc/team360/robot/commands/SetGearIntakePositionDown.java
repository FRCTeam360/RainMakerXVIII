package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetGearIntakePositionDown extends CommandGroup {

    public SetGearIntakePositionDown() {
    	addSequential(new MoveGearIntake(359, 1));
    }
}
