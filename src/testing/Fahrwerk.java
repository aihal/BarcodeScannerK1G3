package testing;
import lejos.hardware.motor.Motor;
//import lejos.utility.Delay;

public class Fahrwerk {
	private lejos.hardware.motor.NXTRegulatedMotor brummi = Motor.D;
	public void fahre(){
		brummi.setSpeed(50);
		brummi.forward();
	}
	public void stoppe(){
		brummi.stop();
	}
	public int getTacho() {
//		Der Tachostand ist immer 0-360, er zählt nicht hoch.
		return brummi.getTachoCount();
	}
}
