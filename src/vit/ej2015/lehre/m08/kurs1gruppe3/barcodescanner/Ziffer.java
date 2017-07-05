package vit.ej2015.lehre.m08.kurs1gruppe3.barcodescanner;

public class Ziffer {
	private boolean anfang = false;
	private boolean ende = false;
	private int zahlWert;
	
	/**
	 * Konstruktor. Anhand der übergebenen Balkenfarben bestimmen, welche Ziffer gerade ausgelesen wurde.
	 * Bei Anfang oder Ende auch entsprechend die boolean flag setzen. Wenn die gelesenen Balken unbekannt sind,
	 * dann setze den Wert auf -1.
	 * @param b1 int 0 oder 1 (schwarz oder weiß)
	 * @param b2 int 0 oder 1 (schwarz oder weiß)
	 * @param b3 int 0 oder 1 (schwarz oder weiß)
	 * @param b4 int 0 oder 1 (schwarz oder weiß)
	 */
	public Ziffer(int b1, int b2, int b3, int b4){
		if(b1 == 0 && b2 == 1 && b3 == 0 && b4 == 1){
			//s w s w -> Anfang
			this.anfang = true;
			this.zahlWert = -2;
		}else if(b1 == 1 && b2 == 1 && b3 == 1 && b4 == 1){
			this.zahlWert = 0;
		} else if(b1 == 0 && b2 == 1 && b3 == 1 && b4 == 1){
			this.zahlWert = 1;
		}else if(b1 == 1 && b2 == 0 && b3 == 1 && b4 == 1){
			this.zahlWert = 2;
		}else if(b1 == 1 && b2 == 1 && b3 == 0 && b4 == 1){
			this.zahlWert = 3;
		}else if(b1 == 1 && b2 == 1 && b3 == 1 && b4 == 0){
			this.zahlWert = 4;
		}else if(b1 == 0 && b2 == 0 && b3 == 1 && b4 == 1){
			this.zahlWert = 5;
		}else if(b1 == 1 && b2 == 0 && b3 == 0 && b4 == 1){
			this.zahlWert = 6;
		}else if(b1 == 1 && b2 == 1 && b3 == 0 && b4 == 0){
			this.zahlWert = 7;
		}else if(b1 == 0 && b2 == 1 && b3 == 1 && b4 == 0){
			this.zahlWert = 8;
		}else if(b1 == 0 && b2 == 1 && b3 == 0 && b4 == 0){
			this.zahlWert = 9;
		}else if(b1 == 1 && b2 == 0 && b3 == 1 && b4 == 0){
			//w s w s -> Ende
			this.ende = true;
			this.zahlWert = -2;
		}else {
			// Error, Zahl gibt es nicht
			this.zahlWert = -1;
		}
	}
	
	/**
	 * @return <b>boolean</b> true wenn es die Anfangsziffer war.
	 */
	public boolean istAnfang() {
		return this.anfang;
	}
	
	/**
	 * @return <b>boolean</b> true wenn es die Endeziffer war.
	 */
	public boolean istEnde() {
		return this.ende;
	}
	
	/**
	 * Gibt den Wert als String zurück. Bei Anfang "<b>A</b>", bei Ende "<b>E</b>".
	 * @return <b>String</b> Gibt eine Stringrepräsentation der Ziffer zurück.
	 */
	public String toString(){
		if(this.anfang){
			return "A";
		}else if(this.ende){
			return "E";
		}else{
			return Integer.toString(this.zahlWert);
		}
	}
}
