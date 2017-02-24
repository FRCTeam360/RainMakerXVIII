package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ResetDriveEncoders extends Command {

    public ResetDriveEncoders() {

    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.drivetrain.resetEncs();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }
    
    protected void interrupted() {
    }
}
