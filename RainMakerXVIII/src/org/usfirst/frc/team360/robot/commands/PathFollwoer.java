package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.FalconPathPlanner;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PathFollwoer extends Command {
	final FalconPathPlanner path;
	double kV;
	double kP;
	double kA;
	int i = 0;
    public PathFollwoer() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	long start = System.currentTimeMillis();
		//System.setProperty("java.awt.headless", "true"); //enable this to true to emulate roboRio environment


		//create waypoint path
		double[][] waypoints = new double[][]{
				{1, 1},
				{5, 1},
				{9, 12},
				{12, 9},
				{15, 6},
				{19, 12}
		}; 

		double totalTime = 8; //seconds
		double timeStep = 0.02; //period of control loop on Rio, seconds
		double robotTrackWidth = 1.71666; //distance between left and right wheels, feet

		path = new FalconPathPlanner(waypoints);
		path.calculate(totalTime, timeStep, robotTrackWidth);
		
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speedLeft = kV * path.smoothLeftVelocity[i][1] + kA * path.smoothLeftVelocity[i][1] - path.smoothLeftVelocity[i-1][1];
    	double speedRight = kV * path.smoothRightVelocity[i][1] + kA * path.smoothRightVelocity[i][1] - path.smoothRightVelocity[i-1][1];
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
