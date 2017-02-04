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
	Value shifterValue;
	Value shifterOldValue;
	double navXValue;
	double navXOldValue;
	boolean intakeValue;
	boolean intakeOldValue;
	boolean catapultValue;
	boolean catapultOldValue;

	
	boolean canStop = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	String timeMessage;// the match/ FPGA time string
	String encodersMessage;// the encoder values
	String speedMessage;//the speed
	String shifterMessage;//Super Shifter position
	String navXMessage;
	String intakeMessage;
	String catapultMessage;
	
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
			intakeValue = false;
			intakeOldValue = false;
			catapultValue = false;
			catapultOldValue = false;
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
			encValRightValue = RobotMap.encR.get();
			encValLeftValue = RobotMap.encL.get();
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
	public void logshifter(){
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
	public void lognavX(){
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
	public void logIntake(){
		intakeValue = RobotMap.intake.get();
		try{
			if(intakeValue != intakeOldValue){
				intakeOldValue = intakeValue;
				intakeMessage = '\t' + "intake state: " + intakeValue + '\n';
				bw.write(intakeMessage);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	public void logCatapult(){
		catapultValue = RobotMap.catapult.get();
		try{
			if(catapultValue != catapultOldValue){
				catapultOldValue = catapultValue;
				catapultMessage = '\t' + "catapult State: " + catapultValue + '\n';
				bw.write(catapultMessage);
			}
		}catch (Exception e){
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
	public void logCatapultDown(){
		try{
			bw.write('\t' + "Command Run: CatapultDown" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void logCatapultUp(){
		try{
			bw.write('\t' + "Command Run: CatapultUp" + '\n');
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
	public void logIntakeArmDown(){
		try{
			bw.write('\t' + "Command Run: IntakeArmDown" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void logIntakeArmUp(){
		try{
			bw.write('\t' + "Command Run: IntakeArmUp" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void logIntakeMotors(){
		try{
			bw.write('\t' + "Command Run: IntakeMotors" + '\n');
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	public void logIntakeMotorsOut(){
		try{
			bw.write('\t' + "Command Run: IntakeMotorsOut" + '\n');
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