package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDCameraAim extends Command {
	
	double motorSpeed = 0.4;
	double direction = 0;
	double currentAngle = 0;
	double distance = 0;
    double error = 0;
    double pAdjustment = 0;
    double iAdjustment = 0;
    double dAdjustment = 0;
    double lastError = 0;
    double PIDAdjustment = 0;
    double speed = 0;
    double way = 1;
    int n = 0;
    Timer time;
    boolean pid = false;
    public PIDCameraAim() {
    	requires(Robot.drivetrain); 
    }
    
    protected void initialize() {
    	dAdjustment = 0;
    	iAdjustment = 0;
    	n = 0;
    	pAdjustment = 0;
    	error = 0;
    	lastError = 0;
    	PIDAdjustment = 0;
    	direction = RobotMap.azimuthToGearTarget + Robot.navX.getNavXAngle() + RobotMap.gearCameraOffSetInDegrees;
    	time = new Timer(); 
    	time.reset();
    	time.start();
    }
    
    protected void execute() {
    	System.out.println(direction + "direction");
    	currentAngle = Robot.navX.getNavXAngle();
    	error = direction - currentAngle;
    	System.out.println(error + "error");
    	pAdjustment = error * RobotMap.PIDCameraAimP * RobotMap.PIDCameraAimGainMultiplier;
    	iAdjustment = iAdjustment + (error * RobotMap.PIDCameraAimI * RobotMap.PIDCameraAimGainMultiplier);
    	dAdjustment = (error - lastError) * RobotMap.PIDCameraAimD * RobotMap.PIDCameraAimGainMultiplier;
    	lastError = error;
    	PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
      	if(Robot.navX.getNavXAngle() < 10 + direction && Robot.navX.getNavXAngle() > direction - 10 && pid == false){
      		iAdjustment = 0;
      		pid = true;
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
  		if(Robot.navX.getNavXAngle() < .5 + direction && Robot.navX.getNavXAngle() > direction - .5){
  			n++;
  		} else {
  			n = 0;
  		}
  		SmartDashboard.putNumber("speed: ", speed);
    }

    protected boolean isFinished() {
        return (Robot.navX.getNavXAngle() < .5 + direction && Robot.navX.getNavXAngle() > direction - .5 && n > 5);
    }

    protected void end() {
    	Robot.drivetrain.stop();
    	SmartDashboard.putString("done", "done");
    }

    protected void interrupted() {
    	end();
    }
}