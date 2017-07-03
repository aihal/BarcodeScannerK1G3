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
		Fahrwerk brumm = new Fahrwerk(120);
		EV3ColorSensor hs = new EV3ColorSensor(SensorPort.S1);
		hs.setCurrentMode("Red");

		Barcode barcode = new Barcode();
		brumm.fahre();
		
		int balkenDauer = Auswertung.startZifferAuswertung(hs, brumm);
		LCD.drawInt(balkenDauer, 10, 0);
		while(!Button.ESCAPE.isDown()){
			Ziffer z = Auswertung.werteEineZifferAus(hs, balkenDauer, brumm);
			barcode.add(z);
			LCD.drawString(z.toString(), 0, 0);
			if(z.istEnde()){
				break;
			}
		}
		hs.close();
		brumm.stoppe();brumm.close();
		LCD.clear();
		LCD.drawString(barcode.toString(),  0,  3);
		LCD.drawString(String.format("balkenDauer: %d", balkenDauer), 0, 4);
		Delay.msDelay(10000);
	}

}
