package org.usfirst.frc.team360.robot.subsystems;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.usfirst.frc.team360.robot.Robot;
import org.usfirst.frc.team360.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Logger extends Subsystem {
	String content; //content written to the Log
    FileWriter fw;
    BufferedWriter bw;
	File Log;
	double matchTime;
	double encValRightValue;
	double encRightOldValue;
	double encValLeftValue;
	double encValLeftOldValue;
	double motorR1Value;
	double motorR1OldValue;
	double motorL1Value;
	double motorL1OldValue;
	double hangerMotor1Value;
	double hangerMotor1OldValue;
	double hangerMotor2Value;
	double hangerMotor2OldValue;
	Value shifterValue;
	Value shifterOldValue;
	double navXValue;
	double navXOldValue;
	double intakeMotorValue;
	double intakeMotorOldValue;
	double intakeHeightValue;
	double intakeHeightOldValue;
	double shooterMotorValue;
	double shooterMotorOldValue;
	double ballIntakeValue;
	double ballIntakeOldValue;
	boolean catapultValue;
	boolean catapultOldValue;
	double potentiometerValue;
	double potentiometerOldValue;

	
	boolean canStop = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	String timeMessage;// the match/ FPGA time string
	String encodersMessage;// the encoder values
	String speedMessage;//the speed
	String shifterMessage;//Super Shifter position
	String navXMessage;
	String intakeMotorMessage;
	String intakeHeightMessage;
	String catapultMessage;
	String hanger1Message;
	String hanger2Message;
	String shooterMotorMessage;
	String ballIntakeMessage;
	String potentiometerMessage;
	
	public void initLogger(){
		try {
			canStop = true;
			encValRightValue = 0;
			encRightOldValue = 0;
			encValLeftValue = 0;
			encValLeftOldValue = 0;
			motorR1Value = 0;
			motorR1OldValue = 0;
			motorL1Value = 0;
			motorL1OldValue = 0;
			navXValue = 0;
			navXOldValue = 0;
			intakeMotorValue = 0;
			intakeMotorOldValue = 0;
			intakeHeightValue = 0;
			intakeHeightOldValue = 0;
			catapultValue = false;
			catapultOldValue = false;
			hangerMotor1Value = 0;
			hangerMotor1OldValue = 0;
			hangerMotor2Value = 0;
			hangerMotor2OldValue = 0;
			potentiometerValue = 0;
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			Date date = new Date();
			System.out.println(dateFormat.format(date));
			String name = dateFormat.format(date);
    		Log = new File("u/" + RobotMap.RobotState + "_" + name + ".txt");//Log  location
			if (!Log.exists()) {
				Log.createNewFile();
			}
			fw = new FileWriter(Log.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.flush();
		} catch (Exception e) {
			e.toString();
		}
	}
	public void logTime(){
		try {
			matchTime = ((double)((int)(Robot.RoboRioTimer.getMatchTime()*100))/100);
    		//content = (Robot.drivetrain.getMotor());
			if(matchTime != -1){
				timeMessage = "Match Time: " + ((Double)matchTime).toString() + (" seconds") + '\n';
			} else {
				timeMessage = "FPGA Time: " + ((Double)((double)((int)(Robot.RoboRioTimer.getFPGATimestamp()*100))/100)).toString() + " seconds" + '\n';
			}
			bw.write(timeMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void logRobotState(){
		try{
			//RobotState = RobotMap.state.equals(null);
			bw.write('\t' + "Command Run: Robotstate" + '\n');
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logEncoders(){
		try{
			encValRightValue = RobotMap.driveTrainEncoderRight.get();
			encValLeftValue = RobotMap.driveTrainEncoderLeft.get();
			if(encValRightValue != encRightOldValue || encValLeftValue != encValLeftOldValue){
				encValLeftOldValue = encValLeftValue;
				encRightOldValue = encValRightValue;
				encodersMessage = '\t' + "Right Encoder: " + encValRightValue + ", Left Encoder: " + encValLeftValue + '\n';
				bw.write(encodersMessage);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logSpeed(){
		motorR1Value = RobotMap.motorR1.get();
		motorL1Value = RobotMap.motorL1.get();
		try{
		if(motorR1Value != motorR1OldValue || motorL1Value != motorL1OldValue){
			motorR1OldValue = motorR1Value;
			motorL1OldValue = motorL1Value;
			speedMessage = '\t' + "Right Speed: " + motorR1Value + ", Left Speed: " + motorL1Value + '\n';
			bw.write(speedMessage);
		}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void logShifter(){
		shifterValue = RobotMap.shifter.get();
		try{
			if(shifterValue != shifterOldValue){
				shifterOldValue = shifterValue;
				shifterMessage = '\t' + "Shifter Position:" + shifterValue + '\n';
				bw.write(shifterMessage);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logNavXValue(){
		navXValue = RobotMap.navX.getAngle();
		try{
			if(navXValue != navXOldValue){
				navXOldValue = navXValue;
				navXMessage = '\t' + "navX Angle: " + navXValue + '\n';
				bw.write(navXMessage);
			}
		}catch (Exception e) {
			e.printStackTrace();
			}
	}
	public void logShiftUp(){
		try{
			bw.write('\t' + "Command Run: ShiftUp" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void logNavX(){
		try{
			bw.write('\t' + "Command Run: NavX" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void logJoystickTankDrive(){
		try{
			bw.write('\t' + "Command Run: JoystickTankDrive" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void logPressurize(){
		try{
			bw.write('\t' + "Command Run: Pressurize" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void logShiftDown(){
		try{
			bw.write('\t' + "Command Run: ShiftDown" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void loghangerMotor1(){
		hangerMotor1Value = RobotMap.hangerMotor1.get();
		try{
			if(hangerMotor1Value != hangerMotor1OldValue){
				hangerMotor1OldValue = hangerMotor1Value;
				hanger1Message = '\t' + "Hanger1 Position:" + hangerMotor1Value + '\n';
				bw.write(shifterMessage);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void loghangerMotor2(){
		hangerMotor2Value = RobotMap.hangerMotor2.get();
		try{
			if(hangerMotor2Value != hangerMotor2OldValue){
				hangerMotor2OldValue = hangerMotor2Value;
				hanger2Message = '\t' + "Hanger2 Position:" + hangerMotor2Value + '\n';
				bw.write(shifterMessage);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logintakeMotor(){
		intakeMotorValue = RobotMap.intakeMotor.get();
		try{
			if(intakeMotorValue != intakeMotorOldValue){
				intakeMotorOldValue = intakeMotorValue;
				intakeMotorMessage = '\t' + "Intake Position:" + intakeMotorValue + '\n';
				bw.write(shifterMessage);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logintakeHeight(){
		intakeHeightValue = RobotMap.intakeHeight.get();
		try{
			if(intakeHeightValue != intakeHeightOldValue){
				intakeHeightOldValue = intakeHeightValue;
				intakeHeightMessage = '\t' + "Intake Position:" + intakeHeightValue + '\n';
				bw.write(shifterMessage);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logshooterMotor(){
		shooterMotorValue = RobotMap.shooterMotor.get();
		try{
			if(shooterMotorValue != shooterMotorOldValue){
				shooterMotorOldValue = shooterMotorValue;
				shooterMotorMessage = '\t' + "Intake Position:" + shooterMotorValue + '\n';
				bw.write(shifterMessage);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logBallIntake(){
		ballIntakeValue = RobotMap.ballIntake.get();
		try{
			if(ballIntakeValue != ballIntakeOldValue){
				ballIntakeOldValue = ballIntakeValue;
				ballIntakeMessage = '\t' + "Intake Position:" + ballIntakeValue + '\n';
				bw.write(shifterMessage);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void logpotentiometer(){
		potentiometerValue = RobotMap.pot.get();
		try{
			if(potentiometerValue != potentiometerOldValue){
				potentiometerOldValue = potentiometerValue;
				potentiometerMessage = '\t' + "Intake Position:" + potentiometerValue + '\n';
				bw.write(shifterMessage);
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void closeLogger(){
		try {
			if(canStop){
				bw.close();	
				canStop = false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}