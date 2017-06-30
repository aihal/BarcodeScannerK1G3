package testing;

import java.util.*;

public class Barcode {
	private List<Ziffer> ziffern;

	public Barcode() {
		this.ziffern = new ArrayList<Ziffer>();
	}
	
	public void add(Ziffer z){
		this.ziffern.add(z);
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Ziffer z : this.ziffern){
			if(z.anfang() || z.ende()){
				
			}else{
				sb.append(z.toString());
			}
		}
		return sb.toString();
	}

	public int length() {
		return this.ziffern.size();
	}
}
