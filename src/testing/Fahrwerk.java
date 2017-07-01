package testing;
import lejos.hardware.motor.Motor;
//import lejos.utility.Delay;

public class Fahrwerk {
	private final lejos.hardware.motor.NXTRegulatedMotor brummi = Motor.D;
	private int speed = 25;
	
	/**
	 * 
	 * @param int speed
	 */
	public Fahrwerk(int speed) {
		brummi.setSpeed(this.speed);
		this.speed = speed;
	}
	
	public Fahrwerk() {
	}
	
	/**
	 * Fahre los.
	 */
	public void fahre(){
		brummi.forward();
	}
	
	/**
	 * Stoppe den Motor.
	 */
	public void stoppe(){
		brummi.stop();
	}
	
	/**
	 * .close() den Motor.
	 */
	public void close() {
		brummi.close();
	}
}
