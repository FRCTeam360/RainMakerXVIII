package org.usfirst.frc.team360.robot;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.SPI;

public class RobotMap {
	public static double angle = 0;
	public static double CameraFudgeFactor = 1.5;
	public static Relay lights = new Relay(0);
	
	public static boolean driveForward = true;
	
	public static Compressor compressor = new Compressor();
	public static String RobotState = "Disabled";
	
	public static boolean gearTargetTracked = false;
	public static double azimuthToGearTarget = 0;
	
	public static VictorSP motorR1 = new VictorSP(0);
	public static VictorSP motorR2 = new VictorSP(1);
	public static VictorSP motorL1 = new VictorSP(2);
	public static VictorSP motorL2 = new VictorSP(3);
	public static VictorSP hangerMotor1 = new VictorSP(4);
	public static VictorSP hangerMotor2 = new VictorSP(5);
	public static VictorSP shooterMotor = new VictorSP(6);
	public static VictorSP intakeHeight = new VictorSP(7);
	public static VictorSP intakeMotor = new VictorSP(8);
	public static VictorSP ballIntake = new VictorSP(9);
	
	public static Encoder shooterEncoder = new Encoder(4, 5);
	public static Encoder driveTrainEncoderLeft = new Encoder(8, 9);
	public static Encoder driveTrainEncoderRight = new Encoder(6, 7);

	public static int driveTrainEncoderLeftReset = 0;
	public static int driveTrainEncoderRightReset = 0;

	public static final double PIDShooterGainMultiplier = 0.00001;
	public static final double PIDShooterP = 0.9;
	public static final double PIDShooterI = 0;//0.14;
	public static final double PIDShooterD = 0.02;
	
	public static final double PIDNavxTurnGainMultiplier = 0.1;
	public static final double PIDNavxTurnP = 0.5;
	public static final double PIDNavxTurnI = 0.03;
	public static final double PIDNavxTurnD = 0.5;

	public static final double PIDDriveStraightGainMultiplier = 0.0075;
	public static final double PIDDriveStraightP = 0.45;
	public static final double PIDDriveStraightI = 0.012;
	public static final double PIDDriveStraightD = 0.011;

	public static final double PIDCameraAimP = 0.5;
    public static final double PIDCameraAimI = 0.03;
    public static final double PIDCameraAimD = 0.5;
    public static final double PIDCameraAimGainMultiplier = 0.15;
	
	public static boolean dangerZone = false;
	public static DoubleSolenoid shifter = new DoubleSolenoid(0, 1);
	
	public static AHRS navX = new AHRS(SPI.Port.kMXP);

	public static final double encoderCountsLeftToFeet = 22.7;
	
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public static AnalogInput ai = new AnalogInput(0);
	public static Potentiometer pot = new AnalogPotentiometer(ai, 360, 0);
}
