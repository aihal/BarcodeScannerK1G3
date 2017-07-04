package testing;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class Runner {
	public static void main(String[] args){
		LCD.clear();
		LCD.drawString("Moooin!",  3,  3);
		Fahrwerk brumm = new Fahrwerk(90);
		EV3ColorSensor hs = new EV3ColorSensor(SensorPort.S1);
		hs.setCurrentMode("Red");
		
		Runnable musikTask= new Musik();
		Thread musikWorker = new Thread(musikTask);
		musikWorker.setName("Musik");
		
		Runnable eyesTask= new Eyes();
		Thread eyesWorker = new Thread(eyesTask);
		eyesWorker.setName("Loopy");
		
		eyesWorker.start();
		musikWorker.start();

		Barcode barcode = new Barcode();
		brumm.fahre();
		
		int balkenDauer = Auswertung.startZifferAuswertung(hs, brumm);
		LCD.drawInt(balkenDauer, 10, 0);
		while(!Button.ESCAPE.isDown()){
			Ziffer z = Auswertung.werteEineZifferAus(hs, balkenDauer, brumm);
			barcode.add(z);
			LCD.drawString(z.toString(), 0, 0);
			if(z.istEnde() || barcode.length() > 20){
				break;
			}
		}
		hs.close();
		eyesWorker.interrupt();
		musikWorker.interrupt();
		brumm.stoppe();brumm.close();
		Musik.beep();
		barcode.toDisplay();

		while(!Button.ESCAPE.isDown()){
			Delay.msDelay(100);
		}
	}//Ende main
}//Ende Runner
