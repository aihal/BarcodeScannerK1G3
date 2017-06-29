package testing;
import lejos.hardware.Sound;

public class Musik implements Runnable {
	
	@Override
	public void run(){
//		TODO wav Dateien?
		while(true){
			Sound.playNote(new int[]{4, 25, 500, 7000, 5}, 220, 500);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e){
				break;
			}
		}
//		Sound.beepSequenceUp();
//		Delay.msDelay(1000);
//		Sound.playTone(440, 500, 10 );
//		Delay.msDelay(1000);

//		Sound.playTone(412, 500, 10);
//		Sound.playTone(550, 750, 10);
//		Sound.playTone(925, 250, 10);
//		Sound.playTone(734, 800, 10);
//		Sound.beepSequenceUp();

	}

	public static void beep() {
		Sound.playTone(440,  500, 10);
	}

}
