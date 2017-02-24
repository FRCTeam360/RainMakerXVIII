package org.usfirst.frc.team360.robot.commands;



import org.usfirst.frc.team360.robot.*;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTimeBangBang extends Command {
	double timeWait = 0;
	double speed = 0;
	Timer time;
    public DriveTimeBangBang(double speed, double timeWait) {
    	requires(Robot.drivetrain);
    	this.timeWait = timeWait;
    	this.speed = speed;
    }

    protected void initialize() {
    	time = new Timer();
    	time.start();
    }

    protected void execute() {
    	Robot.drivetrain.drive(speed, speed);
    }

    protected boolean isFinished() {
        return timeWait < time.get();
    }
    
    protected void end() {
    	Robot.drivetrain.stop();
    }

    protected void interrupted() {
    	end();
    }
}