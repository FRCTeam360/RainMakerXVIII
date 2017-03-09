package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class IntakeGearFromGround extends CommandGroup {

    public IntakeGearFromGround() {
    	addSequential(new SetGearIntakePositionDown());
    	addSequential(new IntakeUntilGearGrabbed());
    	addSequential(new SetGearIntakePositionCenter());
    }
}
