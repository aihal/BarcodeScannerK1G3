package scannerTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lejos.hardware.motor.Motor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;

/**
 * Die folgende Klasse dient dazu, eine Messreihe aufzunehmen, zur späteren 
 * statistischen Auswertung auf dem Computer. Es wurde bei der Auswertung 
 * festgestellt, dass der ColorSensor sehr unverlässlich arbeit. Mal gibt er
 * ein Dutzend Werte pro Grad (Tachostand, implizit Zeit) aus, mal ein paar 
 * Hundert, mal keine.
 * @author Jonathan.Lutze
 *
 */
public class SensorTester {

	public static void main(String[] args) {
		EV3ColorSensor hs = new EV3ColorSensor(SensorPort.S1);
		hs.setCurrentMode("Red");
		NXTRegulatedMotor brumm = Motor.D;
		brumm.setSpeed(90);
		brumm.forward();
		brumm.resetTachoCount();
		
		// Eine Arrayliste von Triple's erstellen, und zwar Triples 
		// mit den Datentypen Long, Integer, Float. Darin zu speichernde Werte
		// sind:
		// Uhrzeit in Millisekunden (long)
		// Distanz in tachCount Grad (int)
		// Konkreter Messwert (float)
        List<Triple<Long, Integer, Float>> werte = new ArrayList<>();
		float[] sample = new float[hs.sampleSize()];//Hilfsvariable für fetchSample
		
		// Diese Schleife prüft, ob der Roboter schon 720° gefahren ist, wenn nicht, Schleifendurchgang
		while(brumm.getTachoCount() < 720){
			hs.fetchSample(sample, 0);
			werte.add( new Triple<Long, Integer, Float>( System.currentTimeMillis()
					, brumm.getTachoCount()
					, sample[0] ) );
		}
		
		// Aufräumen
		hs.close();
		brumm.stop();
		brumm.close();
		
		// Durch die gesammelten Messwerte schleifen und ein Tupel pro Zeile, mit ; getrennte Werte
		// ausdrucken. Der Ausdruck wird dann in einer Datei (durch das System) gesammelt, die vom
		// Brick auf dem Computer runtergeladen wird.
		for(Triple<Long, Integer, Float> e : werte){
			System.out.print(e.first);
			System.out.print(";");
			System.out.print(e.second);
			System.out.print(";");
			System.out.println(e.third);
		}
	}
}
