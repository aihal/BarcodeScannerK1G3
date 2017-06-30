package testing;

import java.util.*;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class Farben {
	// Wert kalibrieren interaktiv zu Beginn? Bin ich dagegen
	// Der Wert ist 0.5 um 10% Fehler zu erlauben. Eigentlich ist so ab 0.6 wenn der Sensor weiß scannt.
	private static float weissSchwellwert = 0.5f;
	
	/**
	 * Gibt die Farbe in 0 oder 1 aus. Mittelt über alle gegebenen Messwerte.
	 * @param werte Array mit floating Messwerten vom Sensor
	 * @return <b>0</b>: Schwarz<br/> <b>1</b>: weiß.
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
	public static int getFarbe(List<Float> werte){
		float[] r = new float[werte.size()];
		for(int i = 0; i< r.length; i++){
			r[i] = werte.get(i);
		}
		return getFarbe(r);
	}

	public static List<Float> missWerteAlt(EV3ColorSensor hs, int anzahl) {
//		FIXME: Statt einfach stumpf jedes mal eine ms zu warten, müssen wir hier
//		stattdessen while(currentZeit < startZeit+balkenDauer){jedes Mal ein paar Messungen und dann wieder gucken
		long zielZeit = System.currentTimeMillis() + anzahl;
//		float[] werte = new float[anzahl];
		List<Float> werte = new ArrayList<Float>();
		float[] sample = new float[1];
		while(System.currentTimeMillis() < zielZeit){
			hs.fetchSample(sample, 0);
			werte.add(sample[0]);
			Delay.msDelay(5);
		}
		return werte;
	}
	public static float missWert(EV3ColorSensor hs) {
		float[] wert = new float[1];
		hs.fetchSample(wert, 0);
		return wert[0];
	}
	public static float[] missWerte(EV3ColorSensor hs, int anzahl) {
		int c = 3;
		int d = 5;
		if(anzahl > 20){
			c = anzahl%10;
			d = 20;
		}
		if(anzahl > 500){
			c = anzahl%100;
			d = 100;
		}
		float[] werte = new float[c];;
		for(int i=0;i<c;i++){
			hs.fetchSample(werte, i);
			Delay.msDelay(d);
		}
		return werte;
	}
}
