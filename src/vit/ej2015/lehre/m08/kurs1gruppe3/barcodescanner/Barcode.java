package vit.ej2015.lehre.m08.kurs1gruppe3.barcodescanner;

import java.util.*;
import lejos.hardware.lcd.LCD;

public class Barcode {
	private List<Ziffer> ziffern;

	/**
	 * Konstruktor.
	 */
	public Barcode() {
		this.ziffern = new ArrayList<Ziffer>();
	}
	
	/**
	 * F�ge eine Ziffer dem Barcode hinzu.
	 * @param z Ziffer
	 */
	public void add(Ziffer z){
		this.ziffern.add(z);
	}
	
	/**
	 * Gebe eine Stringrepr�sentation des Barcodes zur�ck. Dazu wird von
	 * jeder Ziffer im Barcode deren toString() Methode aufgerufen.
	 * @return Stringrepr�sentation des Barcodes.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Ziffer z : this.ziffern){
			sb.append(" ").append(z.toString());
		}
		return sb.toString();
	}

	/**
	 * Drucke den Barcode auf dem Display des Roboters ab. Beachtet eine Zeilenl�nge von 16 Zeichen.
	 */
	public void toDisplay(){
		// 18 Spalten, 8 Zeilen
		// Sicherheitshalber 16*6, ergibt 96 Zeichen. Wenn mit Leerzeichen, k�nnen
		// 48 separate Barcodeziffern dargestellt werden.�
		LCD.clear();
		String s = this.toString(); // Die Stringrepr�sentation des Barcodes erstellen.
		for(int i = 0 // Diese Schleife schleift durch die Zeilen des Displays (=16 Zeichen L�nge) 
				; i < ((s.length() / 16) + 1) // berechnet die ben�tigte Anzahl an Zeilen
				; i++){
			LCD.drawString(
					 // nimm einen Substring von s, von i*16 bis i*16+16, also zum Beispiel bei i=0:
					// 0*16 = 0, bis 0*16+16 = 16
					s.substring(i*16, Math.min(s.length(), i*16+16)) 
					, 0
					, i);
		}		
	}
	
	/**
	 * @return int L�nge des Barcodes in Anzahl an Ziffern.
	 */
	public int length() {
		return this.ziffern.size();
	}
}
