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
		this.speed = speed;
		brummi.setSpeed(this.speed);
	}
	
	public Fahrwerk() {
		brummi.setSpeed(this.speed);
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

	/**
	 * Resette den TachoCount (die gefahrene Strecke in Umdregungsgraden)
	 */
	public void resetTacho() {
		brummi.resetTachoCount();
	}
	
	/**
	 * Lies den Tacho aus (gefahrene Strecke in Umdrehungsgraden)
	 * @return
	 */
	public int liesTacho() {
		return brummi.getTachoCount();
	}
}
