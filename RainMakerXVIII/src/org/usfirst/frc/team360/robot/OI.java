package org.usfirst.frc.team360.robot;

import org.usfirst.frc.team360.robot.commands.*;
import org.usfirst.frc.team360.robot.triggers.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	 public static Joystick joyR = new Joystick(0);
	    public static Joystick joyL = new Joystick(1);
	    public static Joystick joyOI = new Joystick(2);
	    
	    public static Button buttonDriveUp = new JoystickButton(joyR, 1);
	    public static Button buttonDriveDown = new JoystickButton(joyL, 1);
	    public static Button buttonDriveStraight1 = new JoystickButton(joyL, 7);
	    public static Button buttonDriveStraight2 = new JoystickButton(joyR, 7);
	    public static Button buttonResetDriveEncoders = new JoystickButton(joyL, 3);
	    public static Button buttonSwitch = new JoystickButton(joyR, 2);
	    public static Button buttonStartShooter = new JoystickButton(joyOI, 6);
	    public static Button buttonIntakeGearFromGround = new JoystickButton(joyOI, 1);
	    public static Button buttonDropGear = new JoystickButton(joyOI, 2);
	    public static Button buttonMoveIntakeToMiddle = new JoystickButton(joyOI, 3);
	    public static Button buttonIntakeGearFromDriverStation = new JoystickButton(joyOI, 4);
	    //Command runShooter = new RunShooter();
	    Command intakeGear = new IntakeGearFromGround();
	    public OI(){
	    	buttonSwitch.whenPressed(new SwitchDirection());
	    	buttonDriveUp.whenPressed(new ShiftUp());
	    	buttonResetDriveEncoders.whenPressed(new ResetDriveEncoders());
	    	buttonDriveDown.whenPressed(new ShiftDown());
	    	buttonDropGear.whenPressed(new DropGear());
	    	buttonStartShooter.whileHeld(new PIDShooterSpeed());
	    	buttonMoveIntakeToMiddle.whenPressed(new MoveGearIntake(165, 1));
	    	buttonIntakeGearFromGround.whenPressed(new IntakeGearFromGround());
	    	buttonIntakeGearFromDriverStation.whenPressed(new IntakeGearFromDriverStation());

	    	buttonDriveStraight2.whenPressed(new ResetNavX());
	    	buttonDriveStraight1.whenPressed(new PIDDriveStraight(.95, 0, 120));
	    }
}