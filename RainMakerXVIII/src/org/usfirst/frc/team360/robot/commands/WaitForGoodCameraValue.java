package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class WaitForGoodCameraValue extends Command {
	Timer time;
    public WaitForGoodCameraValue() {
    }

    protected void initialize() {
    	time = new Timer();
    	time.reset();
    	time.start();
    }
    
    protected void execute() {

    }

    protected boolean isFinished() {
        return time.get() > .25 && RobotMap.azimuthToGearTarget >= 5 && 
        		RobotMap.azimuthToGearTarget <= 55 && 
        		RobotMap.gearTargetTracked && RobotMap.visionConnected;
    }
    
    protected void end() {

    }

    protected void interrupted() {

    }
}