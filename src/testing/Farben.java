package testing;

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

	public static float[] missWerte(EV3ColorSensor hs, int anzahl) {
//		FIXME: Statt einfach stumpf jedes mal eine ms zu warten, müssen wir hier
//		stattdessen while(currentZeit < startZeit+balkenDauer){jedes Mal ein paar Messungen und dann wieder gucken
		float[] werte = new float[anzahl];
		for(int i = 0; i < anzahl; i++){
			hs.fetchSample(werte, i);
			Delay.msDelay(1);
		}
		return werte;
	}
}
