package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GetNavX extends Command {
	

    public GetNavX() {
    	requires(Robot.navX);
    }

    protected void initialize() {
    }

	protected void execute() {
    	SmartDashboard.putNumber("NavX Angle", Robot.navX.getNavXAngle());
    	System.out.println(Robot.navX.getNavXAngle());
    }

    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	
    }

    protected void interrupted() {
    }
}