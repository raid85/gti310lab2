package gti310.tp2.audio;
import java.io.FileNotFoundException;

import gti310.tp2.io.*;

public class AudioFilter1628 implements AudioFilter {

	private final int HEADER_SIZE = 44;
	
	private boolean fichierInvalide = false;
	private int nbCanaux = 0;
	private String fichierEntree = "";
	private String fichierSortie= "";
	
	public AudioFilter1628 (String fichierEntree, String fichierSortie){
		
		this.fichierEntree = fichierEntree;
		this.fichierSortie = fichierSortie;
	}
	
	public void process() {
		
		//On lit l'entête du fichier
		try {
			FileSource fs = new FileSource(fichierEntree);
			byte[] header = fs.pop(HEADER_SIZE);
			String headerHEX[] = new String[HEADER_SIZE];
			int i = 0;
			
			for (i = 0; i < HEADER_SIZE; i++){
				System.out.print(Integer.toHexString(header[i]));
				headerHEX[i] = Integer.toHexString(header[i]);
			}
		
			System.out.println();
			System.out.println(headerHEX[22]);
			
			//Valide le type de fichier RIFF 
			if (!(headerHEX[0].equals("52")) || !(headerHEX[1].equals("49")) || !(headerHEX[2].equals("46")) || !(headerHEX[3].equals("46"))){
				fichierInvalide = true;
				System.out.println("Type de fichier invalide");
			}
			else{
				System.out.println("Fichier RIFF valide");
			}
			
			//Valide le format PCM
			if (!(headerHEX[20].equals("1"))){
				fichierInvalide = true;
				System.out.println("Format PCM invalide");
			}
			else{
				System.out.println("Format PCM valide");
			}
			
			//Vérifie que c'est un fichier 16 bits valide
			if (!(headerHEX[34].equals("10")) || (!(headerHEX[35].equals("0")))){
				fichierInvalide = true;
				System.out.println("Fichier 16 bits invalide");
			}
			else{
				System.out.println("Fichier 16 bits valide");
			}
			
			//On regarde le nombre de canaux du fichier
			if (!(headerHEX[22].equals("1"))){
				nbCanaux = 1;
				System.out.println("Fichier Mono");
			}
			if (!(headerHEX[22].equals("2"))){
				nbCanaux = 2;
				System.out.println("Fichier Stereo");
			}
			
			//Si le fichier est valide on effectue les manipulations
			if (fichierInvalide = false){
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		
	}

}
