package org.usfirst.frc.team360.robot;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team360.robot.OI;
import org.usfirst.frc.team360.robot.RobotMap;
import org.usfirst.frc.team360.robot.commands.*;
import org.usfirst.frc.team360.robot.commands.autos.*;
import org.usfirst.frc.team360.robot.subsystems.*;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {
	public static DriveTrain drivetrain;
	public static Pneumatics pneumatics;
	public static OI oi;
	public static IntakeHeight m_intakeHeight;
	public static IntakeMotor m_intakeMotor;
	public static Shooter m_shooter;
	public static Lights lights;
	public static Shifter shifter;
	public static NavX navX;
	public static HardwareTimer RoboRioTimer;
	public static Logger logger;
	public static Hanger hanger;
	public static BallIntake ballIntake;
	public static RPIConnection rpiConnection;
	Command getEnc;
	Command m_USBSave;
	Command autonomousCommand;
	public static enum AutoModeRB {RED, BLUE};
	public static enum AutoModeType {NONE, PLACEONEGEARCENTER, PLACEONEGEARLEFT, PLACEONEGEARRIGHT, SHOOTBOILER, PLACEGEARRIGHTANDSHOOTERBOILER};
	AutoModeRB autoChoiceRB;
	static AutoModeType autoModeType;
	SendableChooser<AutoModeRB> autoRB;
	SendableChooser<AutoModeType> autoType;

	public void robotInit() {
		lights = new Lights();
		logger = new Logger();
		shifter = new Shifter();
		drivetrain = new DriveTrain();
		pneumatics = new Pneumatics();
		getEnc = new GetEncs();
		navX = new NavX();
		RoboRioTimer = new HardwareTimer();
		m_shooter = new Shooter();
		m_intakeMotor = new IntakeMotor();
		m_intakeHeight = new IntakeHeight();
		m_USBSave = new UsbSave();
		hanger = new Hanger();
		ballIntake = new BallIntake();
		rpiConnection = new RPIConnection();
		autoRB = new SendableChooser<AutoModeRB>();
		autoType = new SendableChooser<AutoModeType>();
		SmartDashboard.putData("Auto Action", autoType);
		SmartDashboard.putData("Driver Station Side", autoRB);
		autoRB.addDefault("Red",  AutoModeRB.RED);
		autoRB.addObject("Blue",  AutoModeRB.BLUE);
		autoType.addDefault("None",  AutoModeType.NONE);
		autoType.addObject("Place One Gear Center", AutoModeType.PLACEONEGEARCENTER);
		autoType.addObject("Place One Gear Left", AutoModeType.PLACEONEGEARLEFT);
		autoType.addObject("Place One Gear Right", AutoModeType.PLACEONEGEARRIGHT);
		autoType.addObject("Shoot Boiler", AutoModeType.SHOOTBOILER);
		autoType.addObject("Place Gear Right and Shoot Boiler", AutoModeType.PLACEGEARRIGHTANDSHOOTERBOILER);
		new Thread(() -> {
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
			camera.setResolution(640, 480);
			CvSink cvSink = CameraServer.getInstance().getVideo();
			CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
			Mat source = new Mat();
			Mat output = new Mat();
			while (true) {
				cvSink.grabFrame(source);
				Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
				outputStream.putFrame(output);
			}
		}).start();
		oi = new OI();
	}

	public void disabledInit() {
		logger.closeLogger();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void autonomousInit() {
		RobotMap.RobotState = "AutonomousRB";
		logger.initLogger();
		 autoChoiceRB = (AutoModeRB) autoRB.getSelected();
		 autoModeType = (AutoModeType) autoType.getSelected();
		if (autoChoiceRB == AutoModeRB.RED){
			if (autoModeType == AutoModeType.NONE){
				autonomousCommand = new AutoNone();
			} else if(autoModeType == AutoModeType.PLACEONEGEARCENTER){
				autonomousCommand = new AutoPlaceOneGearCenter();
			} else if(autoModeType == AutoModeType.PLACEONEGEARLEFT){
				autonomousCommand = new AutoPlaceOneGearLeftRed();
			} else if(autoModeType == AutoModeType.PLACEONEGEARRIGHT){
				autonomousCommand = new AutoPlaceOneGearRightRed();
			} else if(autoModeType == AutoModeType.SHOOTBOILER){
				autonomousCommand = new AutoShootBoilerRed();
			} else if(autoModeType == AutoModeType.PLACEGEARRIGHTANDSHOOTERBOILER){
				autonomousCommand = new AutoPlaceOneGearRightAndShootRightRed();
			} 
		} else if(autoChoiceRB == AutoModeRB.BLUE){
			if (autoModeType == AutoModeType.NONE){
				autonomousCommand = new AutoNone();
			} else if(autoModeType == AutoModeType.PLACEONEGEARCENTER){
				autonomousCommand = new AutoPlaceOneGearCenter();
			} else if(autoModeType == AutoModeType.PLACEONEGEARLEFT){
				autonomousCommand = new AutoPlaceOneGearLeftBlue();
			} else if(autoModeType == AutoModeType.PLACEONEGEARRIGHT){
				autonomousCommand = new AutoPlaceOneGearRightBlue();
			} else if(autoModeType == AutoModeType.PLACEONEGEARRIGHT){
				autonomousCommand = new AutoShootBoilerBlue();
			} else if(autoModeType == AutoModeType.SHOOTBOILER){
				autonomousCommand = new AutoShootBoilerBlue();
			} else if(autoModeType == AutoModeType.PLACEGEARRIGHTANDSHOOTERBOILER){
				autonomousCommand = new AutoPlaceOneGearRightAndShootRightBlue();
			} 
		}
		if (autonomousCommand != null){
				autonomousCommand.start();	
		}
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleopInit() {
		RobotMap.RobotState = "Teleop";
		logger.initLogger();
		m_USBSave.start();
		if (autonomousCommand != null){
			autonomousCommand.cancel();
		}
	}
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(RobotMap.shooterEncoder.get());
		SmartDashboard.putNumber("Arm Pot", RobotMap.pot.get());
		SmartDashboard.putNumber("Arm Current", RobotMap.pdp.getCurrent(5));
//		SmartDashboard.putNumber("Left Motor Currect", RobotMap.pdp.getCurrent(1));
//		SmartDashboard.putNumber("Right Motor Currect", RobotMap.pdp.getCurrent(3));
//		SmartDashboard.putNumber("Intake Motor Currect", RobotMap.pdp.getCurrent(12));
		SmartDashboard.putNumber("Left Encoder", drivetrain.getLHardEnc());
		SmartDashboard.putNumber("Right Encoder", drivetrain.getRHardEnc());
		SmartDashboard.putNumber("Shooter Encoder", m_shooter.getEnc());
//		SmartDashboard.putNumber("navx angle", RobotMap.navX.getAngle());
//		SmartDashboard.putNumber("navx pitch",RobotMap.navX.getPitch());
//		SmartDashboard.putNumber("navx roll", RobotMap.navX.getRoll());
//		SmartDashboard.putNumber("navx yaw", RobotMap.navX.getYaw());
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
