package testing;

import java.util.*;

public class Barcode {
	private List<Ziffer> ziffern;

	public Barcode() {
		this.ziffern = new ArrayList<Ziffer>();
	}
	
	/**
	 * Füge eine Ziffer dem Barcode hinzu.
	 * @param z Ziffer
	 */
	public void add(Ziffer z){
		this.ziffern.add(z);
	}
	
	/**
	 * Gebe eine Stringrepräsentation des Barcodes zurück. 
	 * Dazu wird von jeder Ziffer im Barcode deren toString() Methode aufgerufen.
	 */
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Ziffer z : this.ziffern){
			sb.append(" ").append(z.toString());
		}
		return sb.toString();
	}

	/**
	 * 
	 * @return int Länge des Barcodes in Anzahl an Ziffern.
	 */
	public int length() {
		return this.ziffern.size();
	}
}
