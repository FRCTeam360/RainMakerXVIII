
package org.usfirst.frc.team360.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.internal.HardwareTimer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;
import org.usfirst.frc.team360.robot.commands.getEncs;
import org.usfirst.frc.team360.robot.subsystems.*;
import org.usfirst.frc.team360.robot.commands.*;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
		public static DriveTrain drivetrain;
		public static Pneumatics pneumatics;
		public static OI oi;
		public static IntakeHeight m_intakeHeight;
		public static IntakeMotor m_intakeMotor;
		public static DualWheelShooter m_dualWheelShooter;
		public static Lights lights;
		public static Shifter shifter;
		public static NavX navX;
		public static HardwareTimer RoboRioTimer;
		public static Logger logger;
		
		Command getenc;
		Command usbsave1;
		Command autonomousCommand;
		SendableChooser chooser;
		
		public void robotInit() {
			lights = new Lights();
			logger = new Logger();
			shifter = new Shifter();
			drivetrain = new DriveTrain();
			pneumatics = new Pneumatics();
			getenc = new getEncs();
			navX = new NavX();
			RoboRioTimer = new HardwareTimer();
			m_dualWheelShooter = new DualWheelShooter();
			m_intakeMotor = new IntakeMotor();
			m_intakeHeight = new IntakeHeight();
			usbsave1 = new UsbSave2();
			//SmartDashboard.putData("Auto mode", chooser);
			
			RobotMap.lights = new Relay(0);
			
			new Thread(() -> {
				
				UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	            camera.setResolution(640, 480);
	            
	            CvSink cvSink = CameraServer.getInstance().getVideo();
	            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
	            
	            Mat source = new Mat();
	            Mat output = new Mat();
	            
	            while(true) {
	            	
	                cvSink.grabFrame(source);
	                BufferedImage blank = new BufferedImage(source.width(), source.height(), BufferedImage.TYPE_BYTE_GRAY);
	                byte[] data = ((DataBufferByte) blank.getRaster().getDataBuffer()).getData();
					output.get(0, 0, data);
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
			autonomousCommand = (Command) chooser.getSelected();
			
			/*
			 * String autoSelected = SmartDashboard.getString("Auto Selector",
			 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
			 * = new MyAutoCommand(); break; case "Default Auto": default:
			 * autonomousCommand = new ExampleCommand(); break; }
			 */

			// schedule the autonomous command (example)
			if (autonomousCommand != null) autonomousCommand.start();
		}

		/**
		 * This function is called periodically during autonomous
		 */
		@Override
		public void autonomousPeriodic() {
			Scheduler.getInstance().run();
		}

		@Override
		public void teleopInit() {
			
			RobotMap.RobotState = "Teleop";
			//Object logger;
			// This makes sure that the autonomous stops running when
			// teleop starts running. If you want the autonomous to
			// continue until interrupted by another command, remove
			// this line or comment it out.
			logger.initLogger();
			usbsave1.start();
			if (autonomousCommand != null)
				autonomousCommand.cancel();
		}

		/**
		 * This function is called periodically during operator control
		 */
		int i = 0;
		@Override
		public void teleopPeriodic() {
			SmartDashboard.putNumber("Potentiometer", RobotMap.pot.get());
			Scheduler.getInstance().run();
			System.out.println(RobotMap.encL.get());
			RobotMap.encL.reset();
			if(i >= 50){
				//SmartDashboard.putDouble("leqweft   enc", drivetrain.getLSoftEnc());
				//SmartDashboard.putDouble("rigeqwht   enc", drivetrain.getRSoftEnc())
				System.out.println(drivetrain.getLSoftEnc());;
			
			drivetrain.softResetL();
			drivetrain.softResetR();
			i++;
			} else {
				i=0;
			}
		}

		/**
		 * This function is called periodically during test mode
		 */
		@Override
		public void testPeriodic() {
			LiveWindow.run();
		}
	}
