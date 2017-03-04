package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class ToggleShooterPnuematic extends Command {

    public ToggleShooterPnuematic() {
    	requires(Robot.shooterPnuematic);
    }

    protected void initialize() {
    	if(RobotMap.shooterThing.get().equals(Value.kForward)){
    		Robot.shooterPnuematic.close();
    	} else {
    		Robot.shooterPnuematic.open();
    	}
    }

    protected void execute() {
    }
    
    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}