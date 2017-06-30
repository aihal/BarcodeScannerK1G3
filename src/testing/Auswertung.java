package testing;

import java.util.*;

import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.utility.Delay;

public class Auswertung {

	public static long startZifferAuswertung(EV3ColorSensor hs) {
		// Variable deklarieren / initialisieren
		float[] werte;
		long balkenDauer = 1l;
		boolean weiter = true;
		long startZeit = System.currentTimeMillis();
		long endeZeit = System.currentTimeMillis();
		// Wir nehmen einfach an, dass wir auf weiß/gelb anfangen
		int aktuelleFarbe = 0;
		int gemesseneFarbe = 0;
		
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben den Anfang des ersten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				startZeit = System.currentTimeMillis();
				weiter = false;
			}
		}
		
		weiter = true;
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des ersten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				endeZeit = System.currentTimeMillis();
				weiter = false;
			}
		}
		balkenDauer = endeZeit - startZeit;
		LCD.drawInt((int) balkenDauer, 0, 5);
		while(System.currentTimeMillis() < startZeit + (4 * balkenDauer)){
			Delay.msDelay(1);
		}
		return balkenDauer;
	}
	public static long startZifferAuswertungAlt(EV3ColorSensor hs) {
		long anfangsZeit = System.currentTimeMillis();
//		aktuelle Startfarbe bestimmen
		float[] werte = new float[10];
		for(int i = 0; i < 10; i++){
			hs.fetchSample(werte, i);
			Delay.msDelay(1);
		}
		int aktuelleFarbe = Farben.getFarbe(werte);
//		Kalibrierung (das Auslesen des Startmusters)
		long balkenDauer1 = 0l;
		long balkenDauer2 = 0l;
		long balkenDauer3 = 0l;
		boolean weiter = true;
		long startZeit = System.currentTimeMillis();
		long endeZeit = System.currentTimeMillis();
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben den Anfang des ersten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				startZeit = System.currentTimeMillis();
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
				endeZeit = System.currentTimeMillis();
				balkenDauer1 = endeZeit - startZeit;
				weiter = false;
			}
		}
		
		werte = new float[3];
		werte[0] = Farben.missWert(hs);
		werte[1] = Farben.missWert(hs);
		werte[2] = Farben.missWert(hs);
		aktuelleFarbe = Farben.getFarbe(werte);
		weiter = true;
		startZeit = System.currentTimeMillis(); // Beginn zweiter Balken
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des zweiten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				endeZeit = System.currentTimeMillis();
				balkenDauer2 = endeZeit - startZeit;
				weiter = false;
			}
		}
		
		werte = new float[3];
		werte[0] = Farben.missWert(hs);
		werte[1] = Farben.missWert(hs);
		werte[2] = Farben.missWert(hs);
		aktuelleFarbe = Farben.getFarbe(werte);
		weiter = true;
		startZeit = System.currentTimeMillis(); // Beginn dritter Balken
		while(weiter){
			werte = new float[3];
			werte[0] = Farben.missWert(hs);
			werte[1] = Farben.missWert(hs);
			werte[2] = Farben.missWert(hs);
			int gemesseneFarbe = Farben.getFarbe(werte);
			if(gemesseneFarbe != aktuelleFarbe){ // Wir haben das Ende des dritten Balkens erreicht
				aktuelleFarbe = gemesseneFarbe;
				endeZeit = System.currentTimeMillis();
				balkenDauer3 = endeZeit - startZeit;
				weiter = false;
			}
		}
		long balkenDauer = (balkenDauer1 + balkenDauer2 + balkenDauer3) / 3;
		LCD.drawInt( (int)balkenDauer1, 5, 5);
		LCD.drawInt( (int)balkenDauer2, 5, 6);
		LCD.drawInt( (int)balkenDauer3, 5, 7);
		int zaehler = 0;
		while(anfangsZeit + 4 * balkenDauer < System.currentTimeMillis()){
			Delay.msDelay(1);
			zaehler++;
			if(zaehler > 5000){
				break;
			}
		}
		return balkenDauer;
	}

	public static Ziffer werteEineZifferAusAlt(EV3ColorSensor hs, int d) {
		int[] balken = new int[4];
		long startZeit = System.currentTimeMillis();
		List<Float> werte;
		int w = d % 10;
		for(int i = 0; i < 4; i++){
			werte = new ArrayList<Float>();
			while(startZeit + d - 5 > System.currentTimeMillis()){
				werte.add(Farben.missWert(hs));
				Delay.msDelay(5);
			}
			balken[i] = Farben.getFarbe( werte );
		}
		Musik.beep();
		return new Ziffer(balken[0], balken[1], balken[2], balken[3]);
	}
	
	public static Ziffer werteEineZifferAus(EV3ColorSensor hs, long balkenDauer) {
		int[] balken = new int[4];
		long startZeit = System.currentTimeMillis();
		long zielZeit = startZeit + (4 * balkenDauer) - 5; // 5 ms einsparen für die kommende Auswertung?
		List<Float> werte = new ArrayList<Float>();

		while(System.currentTimeMillis() < zielZeit){
			werte.add(Farben.missWert(hs));
			Delay.msDelay(1);
		}
		
		int viertel = werte.size() % 4;
		List<Float> werte0 = werte.subList(0, viertel);
		List<Float> werte1 = werte.subList(viertel, viertel*2);
		List<Float> werte2 = werte.subList(viertel*2, viertel*3);
		List<Float> werte3 = werte.subList(viertel*3, viertel*4);

		balken[0] = Farben.getFarbe( werte0 );
		balken[1] = Farben.getFarbe( werte1 );
		balken[2] = Farben.getFarbe( werte2 );
		balken[3] = Farben.getFarbe( werte3 );

//		Musik.beep();
		return new Ziffer(balken[0], balken[1], balken[2], balken[3]);
	}

}
