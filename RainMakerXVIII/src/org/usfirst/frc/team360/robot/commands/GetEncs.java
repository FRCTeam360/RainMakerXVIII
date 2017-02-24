package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GetEncs extends Command {

    public GetEncs() {
  
    }

    protected void initialize() {
    }

    protected void execute() {
    	SmartDashboard.putNumber("encR", Robot.drivetrain.getRHardEnc());
    	SmartDashboard.putNumber("encL", Robot.drivetrain.getLHardEnc());
    }

    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    }

    protected void interrupted() {
    }
}
