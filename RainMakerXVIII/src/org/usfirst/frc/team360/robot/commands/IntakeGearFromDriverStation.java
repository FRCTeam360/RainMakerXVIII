package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeGearFromDriverStation extends CommandGroup {

    public IntakeGearFromDriverStation() {
    	addSequential(new MoveGearIntake(165, 1));
    	addSequential(new IntakeUntilGearGrabbed());
    	addSequential(new MoveGearIntake(248, 1));
    }
}
