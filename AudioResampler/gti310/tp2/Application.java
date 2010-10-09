package gti310.tp2;

import gti310.tp2.io.FileSource;
import gti310.tp2.audio.AudioFilter1628;

import java.io.FileNotFoundException;

public class Application {

	
	/**
	 * Launch the application
	 * @param args This parameter is ignored
	 */
	public static void main(String args[]) {
		System.out.println("Audio Resample project!");
		
		String programme = "";
		String fichierEntree = "";
		String fichierSortie= "";
		
		//On lance le programme avec les arguments
		if (args.length < 3){
			System.out.println("Il vous manque des arguments !");
			System.out.println("(java <programme1> <fichier d'entrée> <fichier de sortie>)");
			System.out.println("(java <programme2> <fichier de référence> <fichier à analyser>)");
		}
		else if (args[0] != null && args[1] != null && args[2] != null) {
			programme = args[0];
			fichierEntree = args[1];
			fichierSortie = args[2];
		}
		
		//Si c'est le programme1 on passe le fichier dans le filtre audio 16 à 8 bits
		if (programme.equals("programme1")) {
			AudioFilter1628 audioFilter = new AudioFilter1628(fichierEntree, fichierSortie);
			audioFilter.process();
		}
		
		//Si c'est le programme2 on...
		if (programme.equals("programme2")) {
			
			//...
			
		}
		if (!(programme.equals("programme1")) && !(programme.equals("programme2"))){
			System.out.println("Le premier argument doit être <programme1> ou <programme2>");
		}
		
	}
}
