package org.usfirst.frc.team360.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.VictorSP;

import edu.wpi.first.wpilibj.SPI;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
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

	public static double gainMultiplierDualWheelShooter = 0.00001;
	public static double pGainDualWheelShooter = 0.9;
	public static double iGainDualWheelShooter = 0.1;
	public static double dGainDualWheelShooter = 0.02;
	
	public static boolean dangerZone = false;
	public static DoubleSolenoid shifter = new DoubleSolenoid(0, 1);
	
	public static AHRS navX = new AHRS(SPI.Port.kMXP);


}
