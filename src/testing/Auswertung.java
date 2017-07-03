package testing;

import java.util.*;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class Auswertung {

	public static int startZifferAuswertung(EV3ColorSensor hs, Fahrwerk brumm) {
//		aktuelle Startfarbe bestimmen
		float[] werte = new float[3];
		werte[0] = Farben.missWert(hs);
		werte[1] = Farben.missWert(hs);
		werte[2] = Farben.missWert(hs);
		int aktuelleFarbe = Farben.getFarbe(werte);
		
//		Kalibrierung (das Auslesen des Startmusters)
		boolean weiter = true;
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben den Anfang des ersten Balkens erreicht
				brumm.resetTacho();
				aktuelleFarbe = gemesseneFarbe;
				weiter = false;
			}
		}
		
		weiter = true;
		werte = new float[3];
		werte[0] = Farben.missWert(hs);
		werte[1] = Farben.missWert(hs);
		werte[2] = Farben.missWert(hs);
		aktuelleFarbe = Farben.getFarbe(werte);
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des ersten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				weiter = false;
			}
		}
		
		werte = new float[3];
		werte[0] = Farben.missWert(hs);
		werte[1] = Farben.missWert(hs);
		werte[2] = Farben.missWert(hs);
		aktuelleFarbe = Farben.getFarbe(werte);
		weiter = true;
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des zweiten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				weiter = false;
			}
		}
		
		werte = new float[3];
		werte[0] = Farben.missWert(hs);
		werte[1] = Farben.missWert(hs);
		werte[2] = Farben.missWert(hs);
		aktuelleFarbe = Farben.getFarbe(werte);
		weiter = true;
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des dritten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				weiter = false;
			}
		}
		int balkenDauer = brumm.liesTacho() / 3;

		while( brumm.liesTacho() < (balkenDauer * 4) ){
			Delay.usDelay(500);
		}
		return balkenDauer;
	}

	public static Ziffer werteEineZifferAus(EV3ColorSensor hs, int balkenDauer, Fahrwerk brumm) {
		brumm.resetTacho();
		List<Float> werte = new ArrayList<Float>();

		while(brumm.liesTacho() < 4*balkenDauer){
			werte.add(Farben.missWert(hs));
			Delay.usDelay(500);
		}
		
		int viertel = werte.size() / 4;
		List<Float> werte0 = werte.subList(0, viertel);
		List<Float> werte1 = werte.subList(viertel, viertel*2);
		List<Float> werte2 = werte.subList(viertel*2, viertel*3);
		List<Float> werte3 = werte.subList(viertel*3, viertel*4);

		return new Ziffer(Farben.getFarbe( werte0 )
						 ,Farben.getFarbe( werte1 )
						 ,Farben.getFarbe( werte2 )
						 ,Farben.getFarbe( werte3 )
				);
	}

}
