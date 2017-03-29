package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetGearIntakePositionUp extends CommandGroup {

    public SetGearIntakePositionUp() {
    	addSequential(new MoveGearIntake(56, 1));
    }
}
