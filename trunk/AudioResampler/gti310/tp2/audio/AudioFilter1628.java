package gti310.tp2.audio;
import java.io.FileNotFoundException;

import gti310.tp2.io.*;

public class AudioFilter1628 implements AudioFilter {

	private final int HEADER_SIZE = 44;
	
	private boolean fichierInvalide = false;
	String fichierEntree = "";
	String fichierSortie= "";
	
	public AudioFilter1628 (String fichierEntree, String fichierSortie){
		
		this.fichierEntree = fichierEntree;
		this.fichierSortie = fichierSortie;
	}
	
	public void process() {
		
		//On lit l'entête du fichier
		try {
			FileSource fs = new FileSource(fichierEntree);
			byte[] header = fs.pop(HEADER_SIZE);
			String headerHEX[] = new String[44];
			int i = 0;
			
			for (i = 0; i < HEADER_SIZE; i++){
				System.out.print(Integer.toHexString(header[i]));
				headerHEX[i] = Integer.toHexString(header[i]);
			}
		
			System.out.println();
			
			//Valide le format RIFF 
			if (!(headerHEX[0].equals("52")) || !(headerHEX[1].equals("49")) || !(headerHEX[2].equals("46")) || !(headerHEX[3].equals("46"))){
				fichierInvalide = true;
			}
			else{
				System.out.println("Format RIFF valide");
			}
				
			//Vérifie que c'est un fichier 16 bits valide
			if (!(headerHEX[34].equals("10")) || (!(headerHEX[35].equals("0")))){
				fichierInvalide = true;
			}
			else{
				System.out.println("Fichier 16 bits valide");
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		
	}

}
