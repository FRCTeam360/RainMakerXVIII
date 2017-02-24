package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team360.robot.Robot;

public class UsbSave extends Command {
    public UsbSave() {
    }
    
    protected void initialize() {
    	
    	
    }

    protected void execute() {
    	try{
    	Robot.logger.logTime();
    	Robot.logger.logEncoders();
    	Robot.logger.logSpeed();
    	Robot.logger.logShifter();
    	}catch(Exception e){
    		
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}