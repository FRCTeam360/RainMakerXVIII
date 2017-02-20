package org.usfirst.frc.team360.robot;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team360.robot.commands.*;
import org.usfirst.frc.team360.robot.commands.autos.ExampleAuto;
import org.usfirst.frc.team360.robot.commands.autos.GearCenter;
import org.usfirst.frc.team360.robot.commands.autos.GearRight;
import org.usfirst.frc.team360.robot.subsystems.*;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

	Command getEnc;
	Command m_USBSave;
	Command autonomousCommand;
	// SendableChooser chooser;

	@Override
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
		// SmartDashboard.putData("Auto mode", chooser);

		RobotMap.lights = new Relay(0);

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

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		logger.closeLogger();

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("navx angle", RobotMap.navX.getAngle());
		SmartDashboard.putNumber("navx pitch",RobotMap.navX.getPitch());
		SmartDashboard.putNumber("navx roll", RobotMap.navX.getRoll());
		SmartDashboard.putNumber("navx yaw", RobotMap.navX.getYaw());
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		RobotMap.RobotState = "Autonomous";
		logger.initLogger();
		// autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		autonomousCommand = new GearRight();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder", drivetrain.getLHardEnc());
		SmartDashboard.putNumber("Right Encoder", drivetrain.getRHardEnc());
	}

	@Override
	public void teleopInit() {

		RobotMap.RobotState = "Teleop";
		// Object logger;
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		logger.initLogger();
		m_USBSave.start();
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	int i = 0;

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		System.out.println(RobotMap.shooterEncoder.get());
		SmartDashboard.putNumber("Arm Pot", RobotMap.pot.get());
//		SmartDashboard.putNumber("Left Motor Currect", RobotMap.pdp.getCurrent(1));
//		SmartDashboard.putNumber("Right Motor Currect", RobotMap.pdp.getCurrent(3));
//		SmartDashboard.putNumber("Intake Motor Currect", RobotMap.pdp.getCurrent(12));
		SmartDashboard.putNumber("Left Encoder", drivetrain.getLHardEnc());
		SmartDashboard.putNumber("Right Encoder", drivetrain.getRHardEnc());
//		SmartDashboard.putNumber("navx angle", RobotMap.navX.getAngle());
//		SmartDashboard.putNumber("navx pitch",RobotMap.navX.getPitch());
//		SmartDashboard.putNumber("navx roll", RobotMap.navX.getRoll());
//		SmartDashboard.putNumber("navx yaw", RobotMap.navX.getYaw());
	}
	

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
