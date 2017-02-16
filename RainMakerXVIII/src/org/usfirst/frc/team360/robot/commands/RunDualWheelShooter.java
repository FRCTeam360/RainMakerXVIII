package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team360.robot.*;
/**
 *
 */
public class RunDualWheelShooter extends Command {
	boolean PIDControl = true;
	double currentRPM = 0;
	double t0 = 0;
	double t1 = 0;
	double t2 = 0;
	double error = 0;
	double last_Error = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double PID_Adjust = 0;
	double setPointRPM = 3350;
	double wheel_RPM = 0;
	double shooterMotor = 0.5;
    public RunDualWheelShooter() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_dualWheelShooter);
    	//super("JoystickTankDrive");
    }

	// Called just before this Command runs the first time
    protected void initialize() {
    	shouldRun = true;
		Thread t = new Thread(new RPMDetecter());
		t.start();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_dualWheelShooter.setMotor(calculateMotor());
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	shouldRun = false;
    	Robot.m_dualWheelShooter.stopMotor();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
    protected class RPMDetecter implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(shouldRun){
			//	Thread t = new Thread(new Helper());
			//	t.start();

			SmartDashboard.putDouble("Shooter RPM", findRPM());
			Robot.m_dualWheelShooter.resetEnc();
				System.out.println("Dsa");
				try{
					Thread.sleep(20);
				} catch(Exception e){
					
				}
			}
		}
		protected class Helper implements Runnable{

			@Override
			public void run() {
				// TODO Auto-generated method stub
				SmartDashboard.putDouble("Shooter RPM", Robot.m_dualWheelShooter.getEnc());
				Robot.m_dualWheelShooter.resetEnc();
			}
			
		}
    	
    }
    boolean shouldRun = false;
    public double findRPM(){
		return -1 * ((((double)Robot.m_dualWheelShooter.getEnc())/360)/.02)*60;
    }
    protected double calculateMotor() {
    	t2 = t1;
    	t1 = t0;
    	t0 = findRPM();
    //	currentRPM = (t2+t1+t0)/3;
    	currentRPM = findRPM();
		//SmartDashboard.putDouble("shooting encoder RPM", currentRPM);
    //			SmartDashboard.putBoolean("PIDCOntrol stat", PIDControl);
    	if(currentRPM > (.95 * setPointRPM) && !PIDControl){
       	 PIDControl = true;
       	}
    	if (PIDControl){
    		error = setPointRPM - currentRPM;
    	//	SmartDashboard.putDouble("error" , error);
    		pAdjustment = error * RobotMap.pGainDualWheelShooter * RobotMap.gainMultiplierDualWheelShooter;
    	//	SmartDashboard.putDouble("p" , pAdjustment);
    		iAdjustment = error * RobotMap.iGainDualWheelShooter * RobotMap.gainMultiplierDualWheelShooter;
    	//	SmartDashboard.putDouble("i" , iAdjustment);
    		 dAdjustment = (error - last_Error) * RobotMap.dGainDualWheelShooter * RobotMap.gainMultiplierDualWheelShooter;
    //		 SmartDashboard.putDouble("d" , dAdjustment);
    		last_Error = error;
    		
			PID_Adjust = pAdjustment + iAdjustment + dAdjustment;
    		shooterMotor = shooterMotor + PID_Adjust;
    	}
    	else{
    		shooterMotor = .71;
    	}
    	shooterMotor = .5;
    //	SmartDashboard.putDouble("shooterMotor", shooterMotor);
    	if(shooterMotor >1){
    		shooterMotor = 1;
    	}
    	return shooterMotor;
    }

}