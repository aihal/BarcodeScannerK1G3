package testing;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

public class Runner {
	public static void main1(String[] args){
		EV3ColorSensor hs = new EV3ColorSensor(SensorPort.S1);
		hs.setCurrentMode("Red");
		int i = Farben.getFarbe(new float[]{Farben.missWert(hs)});
		float bla = Farben.missWert(hs);
		LCD.drawInt(i, 0, 0);
		LCD.drawString( String.format("Wert: %f", bla) , 0, 1);
		hs.close();
		Delay.msDelay(2000);
	}
	
	public static void main(String[] args){
		LCD.clear();
		LCD.drawString("Moooin!",  3,  3);
		Fahrwerk brumm = new Fahrwerk(30);
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
//		Musik.beep();
//		LCD.clear();
		LCD.drawString("Fahre...", 5, 0);
		LCD.drawString(String.format("b: %d", balkenDauer), 0, 1);
		while(!Button.ESCAPE.isDown()){
			Ziffer z = Auswertung.werteEineZifferAus(hs, balkenDauer);
			barcode.add(z);
//			if(z.ende()){
//				break;
//			}
			if(barcode.length() > 1){
				break;
			}
		}
		
//		Ende, aufräumen.
		hs.close();
		brumm.stoppe();brumm.close();
//		eyesWorker.interrupt();
//		musikWorker.interrupt();
		
//		Ergebnis präsentieren.
//		Musik.beep();
//		LCD.clear();
		LCD.drawString(barcode.toString(),  0,  6);
		Delay.msDelay(10000);
	}

}
