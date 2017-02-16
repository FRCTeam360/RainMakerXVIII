package org.usfirst.frc.team360.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static double angle = 0;
	public static double CameraFudgeFactor = 1.5;
	public static Relay lights;
	
	public static boolean driveForward = true;
	
	public static Compressor compressor = new Compressor();
	public static String RobotState = "Disabled";
	
	public static VictorSP motorR1 = new VictorSP(0);
	public static VictorSP motorR2 = new VictorSP(1);
	public static VictorSP motorL1 = new VictorSP(2);
	public static VictorSP motorL2 = new VictorSP(3);
	public static VictorSP dualWheelShooterMotor = new VictorSP(5);
	public static VictorSP intakeHeight = new VictorSP(6);
	public static VictorSP intakeMotor = new VictorSP(7);
	
	public static Encoder dualWheelShooterEncoder = new Encoder(4, 5);
	public static Encoder encR = new Encoder(2, 3);
	public static Encoder encL = new Encoder(0, 1);

	public static int encRReset = 0;
	public static int encLReset = 0;

	public static final double PIDShooterGainMultiplier = 0.00001;
	public static final double PIDShooterP = 0.9;
	public static final double PIDShooterI = 0.14;
	public static final double PIDShooterD = 0.02;

	public static final double PIDTurnMultiplier = 0.1;
	public static final double PIDTurnP = 0.5;
	public static final double PIDTurnI = 0.03;
	public static final double PIDTurnD = 0.5;

	public static final double PIDDriveStraightGainMultiplier = 0.15;
	public static final double PIDDriveStraightP = 0.45;
	public static final double PIDDriveStraightI = 0.012;
	public static final double PIDDriveStraightD = 0.011;
	
	public static boolean dangerZone = false;
	public static DoubleSolenoid shifter = new DoubleSolenoid(0, 1);
	
	public static AHRS navX = new AHRS(SPI.Port.kMXP);
	
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public static AnalogInput ai = new AnalogInput(1);
	public static Potentiometer pot = new AnalogPotentiometer(ai, 360, 30);
}
