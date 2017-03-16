package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDCameraDriveStraight extends Command {

	double motorSpeed = -.5;
	double direction = 0;
	double currentAngle = 0;
	double distance = 0;
	double error = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double lastError = 0;
	double PIDAdjustment = 0;
	    
	 public PIDCameraDriveStraight() {
	    	requires(Robot.drivetrain);
	    }
	    
		protected void initialize() {
	    	Robot.drivetrain.resetEncs();
	    	//Robot.navX.resetNavX();
	    	dAdjustment = 0;
	    	if(motorSpeed > 0){
	    		//iAdjustment = 0.25;
	    	//	practiceBotForward();
	    	} else {
	    	//	practiceBotBack();
	    	}
	    	iAdjustment = 0;
	    	pAdjustment = 0;
	    	error = 0;
	    	lastError = 0;
	    	PIDAdjustment = 0;
	    	distance = RobotMap.encoderCountsLeftToFeet * RobotMap.distanceToGearTarget;
	    	direction = Robot.navX.getNavXAngle();
	    }
	
    
	
	protected void execute() {
    	currentAngle = Robot.navX.getNavXAngle();
    	error = direction - currentAngle;
    	pAdjustment = (direction - currentAngle) * RobotMap.PIDDriveStraightP * RobotMap.PIDDriveStraightGainMultiplier;
    	iAdjustment = iAdjustment + (error * RobotMap.PIDDriveStraightI * RobotMap.PIDDriveStraightGainMultiplier);
    	dAdjustment = (error - lastError) * RobotMap.PIDDriveStraightD * RobotMap.PIDDriveStraightGainMultiplier;
    	lastError = error;
    	PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
    	SmartDashboard.putNumber("Integral", iAdjustment);
    	Robot.drivetrain.driveR(motorSpeed + PIDAdjustment);
    	Robot.drivetrain.driveL(motorSpeed - PIDAdjustment);
    	SmartDashboard.putNumber("IAdjustment", iAdjustment);
    }

	protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getRSoftEnc()) > Math.abs(distance);
    }

	protected void end() {
    	SmartDashboard.putNumber("i", iAdjustment);
    	Robot.drivetrain.stop();
    }

	protected void interrupted() {
    	end();
    }
}