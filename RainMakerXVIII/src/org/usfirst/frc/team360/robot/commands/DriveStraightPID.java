package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightPID extends Command {

		double motorSpeed = 0;
		double direction = 0;
		double currentAngle = 0;
		double distance = 0;
	    double error = 0;
	    double pAdjustment = 0;
	    double iAdjustment = 0;
	    double dAdjustment = 0;
	    double lastError = 0;
	    double PIDAdjustment = 0;
	    
    public DriveStraightPID(double motorSpeed, double direction, double distance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.motorSpeed = motorSpeed;
    	this.direction = direction;
    	this.distance = distance;
    	requires(Robot.drivetrain);
    }
    // Called just before this Command runs the first time
    @Override
	protected void initialize() {
    	dAdjustment = 0;
    	if(motorSpeed > 0){
    		//iAdjustment = 0.25;
    	//	practiceBotForward();
    	} else {
    	//	practiceBotBack();
    	}
    	pAdjustment = 0;
    	error = 0;
    	lastError = 0;
    	PIDAdjustment = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
	protected void execute() {
    	currentAngle = Robot.navX.getNavXAngle();
    	error = direction - currentAngle;
    	pAdjustment = (direction - currentAngle) * RobotMap.PIDDriveStraightP * RobotMap.PIDDriveStraightGainMultiplier;
    	iAdjustment = iAdjustment + (error * RobotMap.PIDDriveStraightI * RobotMap.PIDDriveStraightGainMultiplier);
    	dAdjustment = (error - lastError) * RobotMap.PIDDriveStraightD * RobotMap.PIDDriveStraightGainMultiplier;
    	lastError = error;
    	PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;
    	Robot.drivetrain.driveR(motorSpeed - PIDAdjustment);
    	Robot.drivetrain.driveL(motorSpeed + PIDAdjustment);
    	SmartDashboard.putNumber("IAdjustment", iAdjustment);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
	protected boolean isFinished() {
        return Math.abs(Robot.drivetrain.getRSoftEnc()) > Math.abs(distance);
    }

    // Called once after isFinished returns true
    @Override
	protected void end() {
    	SmartDashboard.putNumber("i", iAdjustment);
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
	protected void interrupted() {
    	end();
    }
}