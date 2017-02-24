package org.usfirst.frc.team360.robot.subsystems;

import org.usfirst.frc.team360.robot.*;
import org.usfirst.frc.team360.robot.commands.*;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.sf2.frc.navXSensor;
import com.kauailabs.sf2.orientation.OrientationHistory;

public class NavX extends Subsystem {
	AHRS m_navX = RobotMap.navX;
	navXSensor navxSensor;
	OrientationHistory  orientation_history = null;
	public NavX(){
		navxSensor = new navXSensor(RobotMap.navX, "Drivetrain Orientation");
		orientation_history = new OrientationHistory(navxSensor, RobotMap.navX.getRequestedUpdateRate() * 10);
	}

	public double getNavXAngle(){
		return m_navX.getAngle();
	}

	public void resetNavX(){
		m_navX.reset();
	}

	public float getHistoricalYaw(long time){
		return  orientation_history.getYawDegreesAtTime(time);
	}
	
	public float getHistoricalRoll(long time){
		return  orientation_history.getRollDegreesAtTime(time);
	}
	public float getHistoricalPitch(long time){
		return orientation_history.getPitchDegreesAtTime(time);
	}
	public float getDeltaPitch(){
		return getIMUPitch() - getHistoricalPitch(m_navX.getLastSensorTimestamp()-1000);
	}
	public float getDeltaRoll(){
		return getIMURoll() - getHistoricalRoll(m_navX.getLastSensorTimestamp()-1000);	
	}
	public float getDeltaYaw(){
		return getIMUYaw() - getHistoricalYaw(m_navX.getLastSensorTimestamp()-1000);
	}
	public float getIMURoll(){
		return  m_navX.getRoll();
	}
	public float getIMUYaw(){
		return  m_navX.getYaw();
	}
	public float getIMUPitch(){
		return  m_navX.getPitch();
	}
	        
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new GetNavX());
	}
	        
}