package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WaitForGoodCameraValue extends Command {
	double thing;
    public WaitForGoodCameraValue() {
    }

    protected void initialize() {
    	thing = 0;
    }
    
    protected void execute() {
    	thing = 0; //changed to 0 from rpiserver
    	SmartDashboard.putNumber("thing", thing);
    }

    protected boolean isFinished() {
        return thing > 10 && thing < 50;
    }
    
    protected void end() {

    }

    protected void interrupted() {

    }
}