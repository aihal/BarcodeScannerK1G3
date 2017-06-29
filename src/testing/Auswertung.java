package testing;

import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class Auswertung {

	public static long startZifferAuswertung(EV3ColorSensor hs) {
//		aktuelle Startfarbe bestimmen
		float[] werte = new float[20];
		werte = Farben.missWerte(hs, 20);
		int aktuelleFarbe = Farben.getFarbe(werte);
//		Kalibrierung (das Auslesen des Startmusters
		long startZeit = System.currentTimeMillis();
		long endeZeit = System.currentTimeMillis();
		long balkenDauer1 = 0l;
		long balkenDauer2 = 0l;
		long balkenDauer3 = 0l;
		boolean weiter = true;
		while(weiter){
			werte = Farben.missWerte(hs, 20);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben den Anfang des ersten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				startZeit = System.currentTimeMillis();
				weiter = false;
			}
		}
		weiter = true;
		while(weiter){
			werte = Farben.missWerte(hs, 20);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des ersten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				endeZeit = System.currentTimeMillis();
				balkenDauer1 = endeZeit - startZeit;
				weiter = false;
			}
		}
		weiter = true;
		startZeit = System.currentTimeMillis(); // Beginn zweiter Balken
		while(weiter){
			werte = Farben.missWerte(hs, 20);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des zweiten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				endeZeit = System.currentTimeMillis();
				balkenDauer2 = endeZeit - startZeit;
				weiter = false;
			}
		}
		weiter = true;
		startZeit = System.currentTimeMillis(); // Beginn dritter Balken
		while(weiter){
			werte = Farben.missWerte(hs, 20);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des dritten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				endeZeit = System.currentTimeMillis();
				balkenDauer3 = endeZeit - startZeit;
				weiter = false;
			}
		}
		long balkenDauer = (balkenDauer1 + balkenDauer2 + balkenDauer3) / 3;
		Delay.msDelay(balkenDauer); // Hiernach haben wir das Ende des letzten Balkens vom Startmuster erreicht
		return balkenDauer;
	}

	public static Ziffer werteEineZifferAus(EV3ColorSensor hs, long balkenDauer) {
//		NOTE: Ein Barcode, der aus ganz vielen Nullen nacheinander besteht, 
//		wird vollkommen weiß sein über viele Balken hinweg... man kann also leider keine Abbruchbedingung machen wie
//		Wenn 5 gleichfarbige Balken überfahren wurde oops
		return null;
	}

}
