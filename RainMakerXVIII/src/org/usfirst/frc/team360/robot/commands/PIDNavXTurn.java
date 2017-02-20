package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDNavXTurn extends Command {
	
	double motorSpeed = 0.4;
	double direction = 0;
	double currentAngle = 0;
    double gainMultiplier = 0.1;
    double kPStraight = 0.5;
    double kIStraight = 0.01;
    double kDStraight = 0.5;
    double error = 0;
    double pAdjustment = 0;
    double iAdjustment = 0;
    double dAdjustment = 0;
    double lastError = 0;
    double PIDAdjustment = 0;
    double speed = 0;
    double way = 1;
    int n = 0;
    int i = 0;
    boolean pid = false;
    public PIDNavXTurn(double direction) { //direction is called as 270
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    //	this.motorSpeed = motorSpeed;
    //	this.distance = distance;
    	this.direction = direction;
    	requires(Robot.drivetrain); 
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.navX.resetNavX();
    	 motorSpeed = 0.4;
    	 currentAngle = 0;
         gainMultiplier = 0.1;
         kPStraight = 0.5;
         kIStraight = 0.03;
         kDStraight = 0.5;
         error = 0;
         pAdjustment = 0;
         iAdjustment = 0;
         dAdjustment = 0;
         lastError = 0;
         PIDAdjustment = 0;
         speed = 0;
         way = 1;
         n = 0;
         i = 0;
         pid = false;
    }
    // Called repeatedly when this Command is scheduled to run

    protected void execute() {

    	SmartDashboard.putNumber("angle: ", Robot.navX.getNavXAngle());
      	SmartDashboard.putNumber("angle target: ", direction);
    	currentAngle = Robot.navX.getNavXAngle();
    	error = direction - currentAngle;
    	pAdjustment = error * kPStraight * gainMultiplier;
    	iAdjustment = iAdjustment + (error * kIStraight * gainMultiplier);
    	dAdjustment = (error - lastError) * kDStraight * gainMultiplier;
    	lastError = error;
    	SmartDashboard.putNumber("error: ", error);
    	SmartDashboard.putNumber("prop:  ", pAdjustment);
      	SmartDashboard.putNumber("inte: ", iAdjustment);
    	PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
    	SmartDashboard.putNumber("deriv: ", dAdjustment);
    	SmartDashboard.putNumber("prop: ", motorSpeed);
      	SmartDashboard.putNumber("inte: ", iAdjustment);
      	SmartDashboard.putBoolean("pid stat", pid);
      	SmartDashboard.putNumber("right: ", motorSpeed);
      	SmartDashboard.putNumber("left: ", motorSpeed + PIDAdjustment);

      	//drive motors may be reverse 10/10 should fix

      	if(Robot.navX.getNavXAngle() < 10 + direction && Robot.navX.getNavXAngle() > direction - 10){
      		if(pid == false){
      			iAdjustment = 0;
      			pid = true;
      		}
      	} else {
      		pid = false;
      	}

      	if(pid == true){
      		if(PIDAdjustment > .3){
      			speed = .3;
      		} else if(PIDAdjustment < -.3){
      			speed = -.3;
      		} else {
      			speed = PIDAdjustment;
      		}
      	} else {
      		if(error > 0){
      			speed = motorSpeed;
      		} else {
      			speed = -motorSpeed;
      		}
      	}
    		Robot.drivetrain.driveR((speed));
      		Robot.drivetrain.driveL(-(speed));	
      		
  		if(Robot.navX.getNavXAngle() < .25 + direction && Robot.navX.getNavXAngle() > direction - .25){
  			n++;
  			i++;
  		} else {
  			n = 0;
  		}
  		SmartDashboard.putNumber("speed: ", speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Robot.navX.getNavXAngle() < .25 + direction && Robot.navX.getNavXAngle() > direction - .25 && n > 5);// || time.get() > 3;

    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid = false;
    	Robot.drivetrain.stop();
    	SmartDashboard.putString("done", "done");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}