package testing;
import java.io.File;
import lejos.hardware.Sound;

public class Musik implements Runnable {
	public static void eyeOfTheTiger() {
		Sound.playSample(new File("IntroEyeoftheTiger1.wav"), 50);
	}
	@Override
	public void run(){
		while(true){
//			Sound.playNote(new int[]{4, 25, 500, 7000, 5}, 220, 500);
			try {
				Musik.eyeOfTheTiger();
				Thread.sleep(100);
			} catch (InterruptedException e){
				break;
			}
		}
	}

	/**
	 * Spiele ein einzelnes, leises beep
	 */
	public static void beep() {
		Sound.playTone(440,  200, 5);
	}

}
