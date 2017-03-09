package org.usfirst.frc.team360.robot;

import org.usfirst.frc.team360.robot.commands.*;
import org.usfirst.frc.team360.robot.commands.autos.AutoDriveStraight;
import org.usfirst.frc.team360.robot.triggers.*;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

public class OI {
	 public static Joystick joyR = new Joystick(0);
	 public static Joystick joyL = new Joystick(1);
	 public static Joystick joyOI = new Joystick(2);   
	 
	 public static Button buttonDriveUp = new JoystickButton(joyR, 1);
	 //public static Button buttonSwitch = new JoystickButton(joyR, 2);
	 //public static Button buttonDriveStraight2 = new JoystickButton(joyR, 7);
	 
	 public static Button buttonDriveDown = new JoystickButton(joyL, 1);
	 //public static Button buttonResetDriveEncoders = new JoystickButton(joyL, 3);
	 //public static Button buttonDriveStraight1 = new JoystickButton(joyL, 7);
	 
	 public static Button buttonIntakeGearFromGround = new JoystickButton(joyOI, 1);
	 public static Button buttonDropGear = new JoystickButton(joyOI, 2);
	 public static Button buttonMoveIntakeToTop = new JoystickButton(joyOI, 3);
	 public static Button buttonIntakeGearFromDriverStation = new JoystickButton(joyOI, 4);
	 public static Button buttonToggleShooterPnuematic = new JoystickButton(joyOI, 5);
	 public static Button buttonStartShooter = new JoystickButton(joyOI, 6);
	 public static Button buttonSetGearIntakeHeightMiddle = new JoystickButton(joyOI, 8);
	 public static Button buttonCancelGearIntake = new JoystickButton(joyOI, 10);
	 
	 public OI(){
		buttonDriveUp.whenPressed(new ShiftUp());
		buttonDriveDown.whenPressed(new ShiftDown());
		buttonToggleShooterPnuematic.whenPressed(new ToggleShooterPnuematic());
		buttonDropGear.whenPressed(new DropGear());
		buttonStartShooter.whileHeld(new PIDShooterSpeed());
		buttonMoveIntakeToTop.whenPressed(new SetGearIntakePositionUp());
		buttonIntakeGearFromGround.whenPressed(new IntakeGearFromGround());
		buttonIntakeGearFromDriverStation.whenPressed(new IntakeGearFromDriverStation());
		buttonSetGearIntakeHeightMiddle.whenPressed(new SetGearIntakePositionCenter());
		buttonCancelGearIntake.whenPressed(new OverrideGearIntake());
//		buttonSwitch.whenPressed(new SwitchDirection());
//		buttonResetDriveEncoders.whenPressed(new ResetDriveEncoders());
//		buttonDriveStraight2.whenPressed(new ResetNavX());
//		buttonDriveStraight1.whenPressed(new AutoDriveStraight());
	 }
}