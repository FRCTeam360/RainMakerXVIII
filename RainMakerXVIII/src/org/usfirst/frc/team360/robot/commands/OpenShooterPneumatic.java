package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

public class OpenShooterPneumatic extends Command {

    public OpenShooterPneumatic() {
    	requires(Robot.shooterPnuematic);
    }

    protected void initialize() {
    	Robot.shooterPnuematic.close();
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