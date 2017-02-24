package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class SwitchDirection extends Command {
	double timeWait = .02;
	Timer time;
    public SwitchDirection() {
    	requires(Robot.drivetrain);
    }

    protected void initialize() {
    	time = new Timer();
    	time.start();
    }

    protected void execute() {
    	if(Math.abs(OI.joyR.getRawAxis(1)) <= .3 && Math.abs(OI.joyL.getRawAxis(1)) <= .3){
    		timeWait = .25;
    		RobotMap.driveForward = ! RobotMap.driveForward;
    	} 
    }

    protected boolean isFinished() {
        return timeWait < time.get();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}