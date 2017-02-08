package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightPID extends Command {

		double motorSpeed = 0;

		double direction = 0;

		double currentAngle = 0;

		double distance = 0;

	    double gainMultiplier = 0.15;

	    double kPStraight = 0.45;

	    double kIStraight = 0.012;

	    double kDStraight = 0.011;

	    double error = 0;

	    double pAdjustment = 0;

	    double iAdjustment = 0;

	    double dAdjustment = 0;

	    double lastError = 0;

	    double PIDAdjustment = 0;

/*

	    public void practiceBotForward(){

	    	 motorSpeed = 0; // practice bot forward

			 direction = 0;

			 currentAngle = 0;

			 distance = 0;

		     gainMultiplier = 0.15;

		     kPStraight = 0.45;

		     kIStraight = 0.012;

		     kDStraight = 0.011;

		     error = 0;

		     pAdjustment = 0;

		     iAdjustment = 0;

		     dAdjustment = 0;

		     lastError = 0;

		     PIDAdjustment = 0;

	    }

	    

	    public void practiceBotBack(){

	    	 motorSpeed = 0; // practice bot back

	    	 direction = 0;

	    	 currentAngle = 0;

	    	 distance = 0;

	         gainMultiplier = 0.4;

	         kPStraight = 0.45;

	         kIStraight = 0.0015;

	         kDStraight = 0.022;

	         error = 0;

	         pAdjustment = 0;

	         iAdjustment = -.08;

	         dAdjustment = 0;

	         lastError = 0;

	         PIDAdjustment = 0;

	    }*/

	    

		/*double motorSpeed = 0;// COMP forward

		double direction = 0;

		double currentAngle = 0;

		double distance = 0;

	    double gainMultiplier = 0.05;

	    double kPStraight = 0.45;

	    double kIStraight = 0.012;

	    double kDStraight = 0.011;

	    double error = 0;

	    double pAdjustment = 0;

	    double iAdjustment = 0.512;

	    double dAdjustment = 0;

	    double lastError = 0;

	    double PIDAdjustment = 0;*/

	    // Called repeatedly when this Command is scheduled to run

	    

	

    public DriveStraightPID(double motorSpeed, double direction, double distance) {

        // Use requires() here to declare subsystem dependencies

        // eg. requires(chassis);

    	this.motorSpeed = motorSpeed;

    	this.direction = direction;

    	this.distance = distance;

    	requires(Robot.drivetrain); 

    }

    // Called just before this Command runs the first time

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

    protected void execute() {

    	currentAngle = Robot.navX.getNavXAngle();

    	error = direction - currentAngle;

    	pAdjustment = (direction - currentAngle) * .45 * .03;

    	iAdjustment = iAdjustment + (error * kIStraight * gainMultiplier);

    	//iAdjustment = kIStraight + (error * kIStraight * gainMultiplier);

    	dAdjustment = (error - lastError) * kDStraight * gainMultiplier;

    	lastError = error;

    	PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;

      	//drive motors may be reverse 10/10 should fix

    	Robot.drivetrain.driveR(motorSpeed - PIDAdjustment);

    	Robot.drivetrain.driveL(motorSpeed + PIDAdjustment);

    	SmartDashboard.putNumber("IAdjustment", iAdjustment);

    }



    // Make this return true when this Command no longer needs to run execute()

    protected boolean isFinished() {

        return Math.abs(Robot.drivetrain.getRSoftEnc()) > Math.abs(distance);

    }



    // Called once after isFinished returns true

    protected void end() {

    	SmartDashboard.putNumber("i", iAdjustment);

    	Robot.drivetrain.stop();

    }



    // Called when another command which requires one or more of the same

    // subsystems is scheduled to run

    protected void interrupted() {

    	end();

    }

}