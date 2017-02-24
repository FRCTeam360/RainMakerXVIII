package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.OI;
import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RunHanger extends Command {

    public RunHanger() {
    	requires(Robot.hanger);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(Math.abs(OI.joyOI.getRawAxis(3)) >= .01){
    		Robot.hanger.setMotor(OI.joyOI.getRawAxis(3));
    	} else {
    		Robot.hanger.stop();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
		Robot.hanger.stop();
    }

    protected void interrupted() {
    	end();
    }
}
