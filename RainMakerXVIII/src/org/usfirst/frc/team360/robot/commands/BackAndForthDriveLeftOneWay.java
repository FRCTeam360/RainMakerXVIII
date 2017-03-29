package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BackAndForthDriveLeftOneWay extends Command {
	
	double direction = 0;
	double currentAngle = 0;
    double error = 0;
    double speed = 0;
    public BackAndForthDriveLeftOneWay(double direction) {
    //	this.motorSpeed = motorSpeed;
    //	this.distance = distance;
    	this.direction = direction - 7;
    	requires(Robot.drivetrain); 
    }

    protected void initialize() {
    	//Robot.navX.resetNavX();
    	 currentAngle = 0;
         error = 0;
         speed = 0.2;
    }

    protected void execute() {
    	currentAngle = Robot.navX.getNavXAngle();
    	error = direction - currentAngle;
      	if(error > 0){
    		Robot.drivetrain.driveR((speed));
      	} else {
      		Robot.drivetrain.driveL(-(speed));	
      	}
    }

    protected boolean isFinished() {
        return (Robot.navX.getNavXAngle() < .5 + direction && Robot.navX.getNavXAngle() > direction - .5);// || time.get() > 3;

    	//return false;
    }

    protected void end() {
    	Robot.drivetrain.stop();
    	SmartDashboard.putString("done", "done");
    }

    protected void interrupted() {
    	end();
    }
}