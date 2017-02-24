package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team360.robot.*;

public class SetIntakeSpeed extends Command {

    public SetIntakeSpeed() {
        requires(Robot.m_intakeMotor);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(Math.abs(OI.joyOI.getRawAxis(5)) >= .01){
    		Robot.m_intakeMotor.setMotor(OI.joyOI.getRawAxis(5));
    	} else {
    		Robot.m_intakeMotor.stopMotor();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.m_intakeMotor.stopMotor();
    }

    protected void interrupted() {
    	end();
    }
}