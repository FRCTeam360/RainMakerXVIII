package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;
import org.usfirst.frc.team360.robot.commands.GetNavX;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class NavX extends Subsystem {
AHRS m_navX = RobotMap.navX;
	public double getNavXAngle(){
		return m_navX.getAngle();
	}
	public void resetNavX(){
		m_navX.reset();
		
	}

	@Override
	protected void initDefaultCommand() {
		
		setDefaultCommand(new GetNavX());
		// TODO Auto-generated method stub
	}
}