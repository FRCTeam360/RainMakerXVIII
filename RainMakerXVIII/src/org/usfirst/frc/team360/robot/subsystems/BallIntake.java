package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.RobotMap;
import org.usfirst.frc.team360.robot.commands.*;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BallIntake extends Subsystem {
	VictorSP ballIntake = RobotMap.ballIntake;
	
	public void setMotor(double speed){
		ballIntake.set(speed);
	}
	
	public void stop(){
		ballIntake.stopMotor();
	}

    public void initDefaultCommand() {
        setDefaultCommand(new RunBallIntake());
    }
}
