package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.OI;
import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RunHanger extends Command {

    public RunHanger() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.hanger);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(OI.joyOI.getRawAxis(3)) >= .01){
    		Robot.hanger.setMotor(OI.joyOI.getRawAxis(3));
    	} else {
    		Robot.hanger.stop();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.hanger.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
