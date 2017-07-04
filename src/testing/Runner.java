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
		
		// Auskommentiert, da die Musik vermutulich zuviel Leistung der Auswertung entziehen würde.
//		Runnable musikTask= new Musik();
//		Thread musikWorker = new Thread(musikTask);
//		musikWorker.setName("Musik");
		
		// Die Augen müssen sein... im Übrigen ist der Performancebedarf minimal.
		Runnable eyesTask= new Eyes();
		Thread eyesWorker = new Thread(eyesTask);
		eyesWorker.setName("Loopy");
		
		eyesWorker.start();
//		musikWorker.start();

		Barcode barcode = new Barcode();
		barcode.add(new Ziffer(0, 1, 0, 1));
		brumm.fahre();
		
		// Balkendauer herausfinden.
		int balkenDauer = Auswertung.startZifferAuswertung(hs, brumm);
		LCD.drawInt(balkenDauer, 10, 0);
		
		// Ziffernauswertung starten. Eine Ziffer pro Schleifendurchgang. Abbrechbar mit Escape, ansonsten 
		// bei Auswertung der Endeziffer oder bei über 30 Ziffern.
		while(!Button.ESCAPE.isDown()){
			Ziffer z = Auswertung.werteEineZifferAus(hs, balkenDauer, brumm);
			barcode.add(z);
			LCD.drawString(z.toString(), 0, 0);
			if(z.istEnde() || barcode.length() > 30){
				break;
			}
		}
		hs.close();
		eyesWorker.interrupt();
//		musikWorker.interrupt();
		brumm.stoppe();brumm.close();
		Musik.beep();
		barcode.toDisplay();

		while(!Button.ENTER.isDown()){
			Delay.msDelay(100);
		}
	}//Ende main
}//Ende Runner
