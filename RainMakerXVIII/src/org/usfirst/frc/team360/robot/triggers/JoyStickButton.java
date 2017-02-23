package org.usfirst.frc.team360.robot.triggers;

//import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;
//import org.usfirst.frc.team360.robot.OI;

/**
 *
 */
public class JoyStickButton extends Trigger {
    	private Joystick joy;
    	private int button1;
		int joyL;
    
    	public void DoubleButton(Joystick joy, int button1, int joyL) {
    		this.joy = joy;
    		this.button1 = button1;
    		this.joyL = joyL;
    	}	
    
        public boolean get() {
            return joy.getRawButton(button1) && joy.getRawButton(joyL);
        }
    }