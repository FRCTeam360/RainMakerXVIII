package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.RobotMap;
import org.usfirst.frc.team360.robot.commands.RunHanger;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Hanger extends Subsystem {
	VictorSP hangerMotor1 = RobotMap.hangerMotor1;
	VictorSP hangerMotor2 = RobotMap.hangerMotor2;
	
	public void setMotor(double speed){
		hangerMotor1.set(speed);
		hangerMotor2.set(speed);
	}
	
	public void stop(){
		hangerMotor1.stopMotor();
		hangerMotor2.stopMotor();
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RunHanger());
    }
}

