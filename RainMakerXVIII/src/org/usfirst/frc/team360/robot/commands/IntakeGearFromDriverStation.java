package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeGearFromDriverStation extends CommandGroup {

    public IntakeGearFromDriverStation() {
    	addSequential(new SetGearIntakePositionUp());
    	addSequential(new IntakeUntilGearGrabbed());
    	addSequential(new SetGearIntakePositionCenter());
    }
}
