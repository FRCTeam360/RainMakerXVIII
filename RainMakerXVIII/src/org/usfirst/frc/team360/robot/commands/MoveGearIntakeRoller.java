package org.usfirst.frc.team360.robot.commands;

import org.usfirst.frc.team360.robot.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class MoveGearIntakeRoller extends Command {
	double timeWait = 0;
	double speed = 0;
	Timer time;
    public MoveGearIntakeRoller(double speed, double timeWait) {
    	requires(Robot.m_intakeMotor);
    	this.timeWait = timeWait;
    	this.speed = speed;
    }

    protected void initialize() {
    	time = new Timer();
    	time.start();
    }

    protected void execute() {
    	Robot.m_intakeMotor.setMotor(speed);
    }

    protected boolean isFinished() {
        return timeWait < time.get();
    }

    protected void end() {
    	Robot.m_intakeMotor.stopMotor();
    }

    protected void interrupted() {
    	end();
    }
}