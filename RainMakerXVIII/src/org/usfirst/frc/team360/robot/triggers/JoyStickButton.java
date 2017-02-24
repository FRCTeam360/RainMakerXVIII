package org.usfirst.frc.team360.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class JoyStickButton extends Trigger {
    private Joystick joy;
    double threshold;
   	int axis;
    public void DoubleButton(Joystick joy, int axis, double threshold) {
    	this.joy = joy;
    	this.threshold = threshold;
    	this.axis = axis;
    }	
    public boolean get() {
    	return Math.abs(joy.getRawAxis(axis)) > Math.abs(axis);
    }
}