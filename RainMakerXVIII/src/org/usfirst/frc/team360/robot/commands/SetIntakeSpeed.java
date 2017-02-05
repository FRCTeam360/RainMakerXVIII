package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team360.robot.*;
/**
 *
 */
public class SetIntakeSpeed extends Command {

    public SetIntakeSpeed() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_intakeMotor);
    	//super("JoystickTankDrive");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(OI.joyOI.getRawAxis(5)) >= .01){
    		Robot.m_intakeMotor.setMotor(OI.joyOI.getRawAxis(5));
    	} else {
    		Robot.m_intakeMotor.stopMotor();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_intakeMotor.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}