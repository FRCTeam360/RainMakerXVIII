package org.usfirst.frc.team360.robot.commands.autos;

import org.usfirst.frc.team360.robot.commands.DriveStraightPID;
//import org.usfirst.frc.team360.robot.commands.PIDTurn;
import org.usfirst.frc.team360.robot.commands.ShiftUp;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoGearsLeftBlue extends CommandGroup {



    public AutoGearsLeftBlue() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new ShiftUp());
    	addSequential(new WaitCommand(.25));
    	addSequential(new DriveStraightPID(.95, 180, 200));
    	addSequential(new DriveStraightPID(.95, 180, 2382));
//    	addSequential(new PIDTurn(245));
    	addSequential(new DriveStraightPID(.95, 245, 3386));
//    	addSequential(new PIDTurn(180));
    	
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

}
