package testing;

import java.util.*;
import lejos.hardware.sensor.EV3ColorSensor;

public class Farben {
	private static float weissSchwellwert = 0.45f; // Ab dem Wert und höher -> weiß
	
	/**
	 * Gibt die Farbe in 0 oder 1 aus. Mittelt über alle gegebenen Messwerte.
	 * @param werte Array mit floating Messwerten vom Sensor
	 * @return Die Farbe als int:<br/><b>0</b>: Schwarz<br/> <b>1</b>: weiß.
	 */
	public static int getFarbe(float[] werte){
		int out = 0;
		float summe = 0f;
		for(float f : werte){
			summe += f;
		}
		if( (summe / werte.length) > weissSchwellwert ){
			out = 1;
		}
		return out;
	}
	
	/**
	 * Overloaded getFarbe(float[]), macht intern einen float[] und ruft dann die andere Methode auf.
	 * @param werte als List<Float>
	 * @return Die Farbe als int:<br/><b>0</b>: Schwarz<br/> <b>1</b>: weiß.
	 */
	public static int getFarbe(List<Float> werte){
		float[] r = new float[werte.size()];
		for(int i = 0; i< r.length; i++){
			r[i] = werte.get(i);
		}
		return getFarbe(r);
	}

	/**
	 * Macht eine einzelne Helligkeitsmessung und gibt diese zurück.
	 * @param hs Eine Instanz vom EV3ColorSensor.
	 * @return <b>float</b> ein einzelner Messwert vom Sensor.
	 */
	public static float missWert(EV3ColorSensor hs) {
		float[] wert = new float[1];
		hs.fetchSample(wert, 0);
		return wert[0];
	}
	
}
