package org.usfirst.frc.team360.robot.commands;
import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CatapultUp extends Command {
	Timer time; 
    int i;
    public CatapultUp() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.catapult);
    	requires(Robot.intakearm);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	time = new Timer(); 
    	time.reset();

    	//Robot.catapult.close(); 
        i = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(RobotMap.dangerZone == false){
    		if(RobotMap.intake.get() == true){
    			Robot.intakearm.close();
    		}else{

    			if(i == 0){
    				Timer.delay(.1);
    				time.start();
    				i++;
    			}
    			Robot.catapult.close();
    		}
    	} else {
    		if(i == 0){
				Timer.delay(.1);
				time.start();
				i++;
			}
			Robot.catapult.close();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return time.get() > .4; 
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	time.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}