package testing;
import java.util.*;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.utility.Delay;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

/**
 * @author Nina Kadenbach, Roberto Gresla, Jonathan Lutze
 */
public class Runner {
	public static void main(String[] args){
		LCD.clear();
		LCD.drawString("Moooin!",  3,  3);
		Fahrwerk brumm = new Fahrwerk();
		EV3ColorSensor hs = new EV3ColorSensor(SensorPort.S1);
		hs.setCurrentMode("Red");

		Runnable musikTask= new Musik();
		Thread musikWorker = new Thread(musikTask);
		musikWorker.setName("Musik!");
		
		Runnable eyesTask= new Eyes();
		Thread eyesWorker = new Thread(eyesTask);
		eyesWorker.setName("Loopy!");

		
		Barcode barcode = new Barcode();
//		GoGoGo!
		brumm.fahre();
		eyesWorker.start();
//		musikWorker.start();
		
//		Startmuster auswerten
		long balkenDauer = Auswertung.startZifferAuswertung(hs);
		while(!Button.ESCAPE.isDown()){
			Ziffer z = Auswertung.werteEineZifferAus(hs, balkenDauer);
			if(!z.ende()){
				barcode.add(z);
			}else{
				break;
			}
		}
		
//		Ende, aufräumen.
		brumm.stoppe();
		eyesWorker.interrupt();
//		musikWorker.interrupt();
		
//		Ergebnis präsentieren.
		Musik.beep();
		LCD.clear();
		LCD.drawString(barcode.toString(),  0,  0);
		Delay.msDelay(15000);
	}
//####################################################################
	public static void mainAlt(String[] args) {
		LCD.clear();
		LCD.drawString("Moooin!",  3,  3);
		Fahrwerk brumm = new Fahrwerk();
		EV3ColorSensor hs = new EV3ColorSensor(SensorPort.S1);
		hs.setCurrentMode("Red");
//		Werteliste initialisieren.
		List<Float> werte = new ArrayList<Float>();
		
		Runnable musikTask= new Musik();
		Thread musikWorker = new Thread(musikTask);
		musikWorker.setName("Musik!");
		
		Runnable eyesTask= new Eyes();
		Thread eyesWorker = new Thread(eyesTask);
		eyesWorker.setName("Loopy!");

//		GoGoGo!
		brumm.fahre();
		eyesWorker.start();
//		musikWorker.start();
		int tachoStand = 0;
		float[] sample = new float[1];
		for(int i = 0; i < 200; i++){
			hs.fetchSample(sample, 0);
			werte.add(sample[0]);
			Delay.msDelay(50);
			tachoStand = brumm.getTacho();
		}
		
//		Full Stop!
		brumm.stoppe();
		eyesWorker.interrupt();
		musikWorker.interrupt();
		hs.close();
		
//		Werte auf dem Display ausgeben
		LCD.clear();
		for(int i = 0; i<werte.size(); i++){
			LCD.drawString(Float.toString(werte.get(i)),  0,  i);
			LCD.drawString("##",  10,  i);
			LCD.drawString(Integer.toString(i),  13,  i);
		}
		LCD.drawInt(tachoStand, 0, 0);
		LCD.drawString("      ", 3, 0);
		
		// Barcodetest:
		Barcode barcode = new Barcode();
		barcode.add( new Ziffer(0, 1, 0, 1) );
		barcode.add( new Ziffer(1, 1, 0, 1) );
		barcode.add( new Ziffer(0, 1, 1, 1) );
		barcode.add( new Ziffer(1, 0, 1, 0) );
		LCD.clear();
		LCD.drawString( barcode.toString(), 0, 0);
//		Aktuelle Systemzeit in Epochsekunden ausgeben
//		LCD.drawString(Long.toString(System.currentTimeMillis()), 0, 0);
		
//		Pause machen, damit das Display gelesen werden kann.
//		In der finalen Version muss dann statt dem Timeout ein Warten auf Tastendruck (Escape) stehen
		Delay.msDelay(15000);
	}
}
