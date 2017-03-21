package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SetGearIntakePositionDown extends CommandGroup {

    public SetGearIntakePositionDown() {
    	//addSequential(new MoveGearIntake(312, 1));//comp bot
    	addSequential(new MoveGearIntake(346, 1));
    }
}
