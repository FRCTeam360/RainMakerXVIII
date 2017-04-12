package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDNavXTurnSuperLoose extends Command {
	
	double motorSpeed = 0.4;
	double direction = 0;
	double currentAngle = 0;
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
    public PIDNavXTurnSuperLoose(double direction) {
    //	this.motorSpeed = motorSpeed;
    //	this.distance = distance;
    	this.direction = direction;
    	requires(Robot.drivetrain); 
    }

    protected void initialize() {
    	//Robot.navX.resetNavX();
    	 motorSpeed = 0.4;
    	 currentAngle = 0;
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

    protected void execute() {

    	SmartDashboard.putNumber("angle: ", Robot.navX.getNavXAngle());
      	SmartDashboard.putNumber("angle target: ", direction);
    	currentAngle = Robot.navX.getNavXAngle();
    	error = direction - currentAngle;
    	pAdjustment = error * RobotMap.PIDNavxTurnP * RobotMap.PIDNavxTurnGainMultiplier;
    	iAdjustment = iAdjustment + (error * RobotMap.PIDNavxTurnI * RobotMap.PIDNavxTurnGainMultiplier);
    	dAdjustment = (error - lastError) * RobotMap.PIDNavxTurnD * RobotMap.PIDNavxTurnGainMultiplier;
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
      		
  		if(Robot.navX.getNavXAngle() < 5 + direction && Robot.navX.getNavXAngle() > direction - 5){
  			n++;
  			i++;
  		} else {
  			n = 0;
  		}
  		SmartDashboard.putNumber("speed: ", speed);
    }

    protected boolean isFinished() {
        return (Robot.navX.getNavXAngle() < .5 + direction && Robot.navX.getNavXAngle() > direction - .5 && n > 5);// || time.get() > 3;

    	//return false;
    }

    protected void end() {
    	pid = false;
    	Robot.drivetrain.stop();
    	SmartDashboard.putString("done", "done");
    }

    protected void interrupted() {
    	end();
    }
}