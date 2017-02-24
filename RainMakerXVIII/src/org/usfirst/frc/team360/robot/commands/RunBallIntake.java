package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.OI;
import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class RunBallIntake extends Command {

    public RunBallIntake() {
    	requires(Robot.ballIntake);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(Math.abs(OI.joyOI.getRawAxis(2)) >= .01){
    		Robot.ballIntake.setMotor(OI.joyOI.getRawAxis(2));
    	} else {
    		Robot.ballIntake.stop();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
		Robot.ballIntake.stop();
    }

    protected void interrupted() {
    	end();
    }
}
