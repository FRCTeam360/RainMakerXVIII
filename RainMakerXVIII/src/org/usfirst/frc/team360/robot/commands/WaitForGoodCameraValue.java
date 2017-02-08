package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



/**

 *

 */

public class WaitForGoodCameraValue extends Command {

	double thing;

    public WaitForGoodCameraValue() {

        // Use requires() here to declare subsystem dependencies

        // eg. requires(chassis);

    }



    // Called just before this Command runs the first time

    protected void initialize() {

    	thing = 0;

    }



    // Called repeatedly when this Command is scheduled to run

    protected void execute() {

    	thing = 0; //changed to 0 from rpiserver

    	SmartDashboard.putNumber("thing", thing);

    }



    // Make this return true when this Command no longer needs to run execute()

    protected boolean isFinished() {

        return thing > 10 && thing < 50;

    }



    // Called once after isFinished returns true

    protected void end() {

    }



    // Called when another command which requires one or more of the same

    // subsystems is scheduled to run

    protected void interrupted() {

    }

}