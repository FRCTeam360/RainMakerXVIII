package org.usfirst.frc.team360.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team360.robot.*;

public class PIDShooterSpeed extends Command {
	boolean PIDControl = true;
	static double currentRPM = 0;
	double t0 = 0;
	double t1 = 0;
	double t2 = 0;
	double error = 0;
	double last_Error = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double PID_Adjust = 0;
	double setPointRPM = 1700;
	double wheel_RPM = 0;
	double shooterMotor = 0.5;
    boolean shouldRun = false;
    public PIDShooterSpeed() {
        requires(Robot.m_shooter);
    }

    protected void initialize() {
		Robot.m_shooter.resetEnc();
    	iAdjustment = 0;
    	shouldRun = true;
		Thread t = new Thread(new RPMDetecter());
		t.start();
    }

    protected void execute() {
    	Robot.m_shooter.setMotor(calculateMotor());
    	//Robot.m_shooter.setMotor(.8);
    	SmartDashboard.putNumber("shooter motor", RobotMap.shooterMotor.get());
		
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.m_shooter.stopMotor();
    	shouldRun = false;
    }
    
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
				currentRPM = findRPM();
				SmartDashboard.putNumber("Shooter's RPM", currentRPM);
				Robot.m_shooter.resetEnc();
				try{
					Thread.sleep(20);
				} catch(Exception e){
					
				}
			}
		}
//		protected class Helper implements Runnable{
//
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				SmartDashboard.putDouble("Shooter RPM", Robot.m_shooter.getEnc());
//				Robot.m_shooter.resetEnc();
//			}
//			
//		}
    	
    }
    public double findRPM(){
		return -1 * ((((double)Robot.m_shooter.getEnc())/360)/.02)*60;
    }
    protected double calculateMotor() {
    	PIDControl = true;
    	if(currentRPM > (.95 * setPointRPM) && !PIDControl){
       	 PIDControl = true;
       	}
    	if (PIDControl){
    		//RobotMap.PIDShooterGainMultiplier = 0.00001
    		// RobotMap.PIDShooterP = .09
    		// RobotMap.PIDShooterI = 0
    		 //RobotMap.PIDShooterD = 0.02
    		error = setPointRPM - currentRPM;
    		//SmartDashboard.putDouble("error" , error);
    		pAdjustment = error * RobotMap.PIDShooterP * RobotMap.PIDShooterGainMultiplier;
    		//SmartDashboard.putDouble("p" , pAdjustment);
    		iAdjustment = error * RobotMap.PIDShooterI * RobotMap.PIDShooterGainMultiplier;
    		//SmartDashboard.putDouble("i" , iAdjustment);
    		 dAdjustment = (error - last_Error) * RobotMap.PIDShooterD * RobotMap.PIDShooterGainMultiplier;
    		// SmartDashboard.putDouble("d" , dAdjustment);
    		last_Error = error;
    		
			PID_Adjust = pAdjustment + iAdjustment + dAdjustment;
    		shooterMotor = shooterMotor + PID_Adjust;
    	}
    	else{
    		shooterMotor = .71;
    	}
    	//SmartDashboard.putDouble("shooterMotor", shooterMotor);
    	if(shooterMotor >1){
    		shooterMotor = 1;
    	} else if(shooterMotor < -.1){
    		SmartDashboard.putNumber("bad value", shooterMotor);
    		shooterMotor=0;
    	}
    	shooterMotor = .7;
    	return shooterMotor;
    }

}