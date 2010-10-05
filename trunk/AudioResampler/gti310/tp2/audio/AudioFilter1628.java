package gti310.tp2.audio;
import java.io.FileNotFoundException;

import gti310.tp2.io.*;

public class AudioFilter1628 implements AudioFilter {

	String fichierEntree = "";
	String fichierSortie= "";
	
	public AudioFilter1628 (String fichierEntree, String fichierSortie){
		
		this.fichierEntree = fichierEntree;
		this.fichierSortie = fichierSortie;
	}
	
	public void process() {
		
		try {
			FileSource fs = new FileSource(fichierEntree);
			
			byte[] header = fs.pop(44);
			
			int i = 0;
			
			for (i = 0; i < 44; i++){
				System.out.print(Integer.toHexString(header[i]));	
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		
	}

}
