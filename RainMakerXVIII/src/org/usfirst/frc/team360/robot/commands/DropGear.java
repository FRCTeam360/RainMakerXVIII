package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DropGear extends CommandGroup {

    public DropGear() {
    	addParallel(new MoveGearIntakeRoller(-1, .65));
    	addSequential(new SetGearIntakePositionDown());
    }
}
