package testing;
import java.util.*;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class Runner {
	public static void main3(String[] args){
		EV3ColorSensor hs = new EV3ColorSensor(SensorPort.S1);
		hs.setCurrentMode("Red");
		int i = Farben.getFarbe(Farben.missWerte(hs, 20));
		LCD.drawInt(i, 0, 0);
		hs.close();
		Delay.msDelay(10000);
	}
	public static void main(String[] args){
		LCD.clear();
		LCD.drawString("Moooin!",  3,  3);
		Fahrwerk brumm = new Fahrwerk();
		EV3ColorSensor hs = new EV3ColorSensor(SensorPort.S1);
		hs.setCurrentMode("Red");

//		Runnable musikTask= new Musik();
//		Thread musikWorker = new Thread(musikTask);
//		musikWorker.setName("Musik!");
//		
//		Runnable eyesTask= new Eyes();
//		Thread eyesWorker = new Thread(eyesTask);
//		eyesWorker.setName("Loopy!");
		
		Barcode barcode = new Barcode();
//		GoGoGo!
		brumm.fahre();
//		eyesWorker.start();
//		musikWorker.start();
		
//		Startmuster auswerten
		long balkenDauer = Auswertung.startZifferAuswertung(hs);
		Musik.beep();
//		LCD.clear();
		LCD.drawString("Fahre...", 0, 0);
		LCD.drawString(String.format("b: %d", balkenDauer), 0, 1);
		while(!Button.ESCAPE.isDown()){
			Ziffer z = Auswertung.werteEineZifferAus(hs, balkenDauer);
			barcode.add(z);
//			if(z.ende()){
//				break;
//			}
			if(barcode.length() > 0){
				break;
			}
		}
		
//		Ende, aufräumen.
		hs.close();
		brumm.stoppe();
//		eyesWorker.interrupt();
//		musikWorker.interrupt();
		
//		Ergebnis präsentieren.
//		Musik.beep();
		LCD.clear();
		LCD.drawString(barcode.toString(),  0,  0);
		Delay.msDelay(15000);
	}

}
