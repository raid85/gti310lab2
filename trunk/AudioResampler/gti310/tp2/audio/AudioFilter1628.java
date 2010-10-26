package gti310.tp2.audio;
import java.io.FileNotFoundException;

import gti310.tp2.io.*;

public class AudioFilter1628 implements AudioFilter {

	private WaveHandler waveHandler;
	
	public AudioFilter1628 (String fichierEntree, String fichierSortie){
		//On se cr�e une isntance de waveHandler afin de g�rer la lecture/�criture des fichiers wav
		this.waveHandler = new WaveHandler(fichierEntree, fichierSortie);
	}
	
	/** 
	 * Processus qui regroupe l'ensemble des m�thodes n�cessaire � la conversion
	 * de 16 bits � 8 bits	 
	 * 
	 * Analyse de complexit�: Le processus suivant s'ex�cutera le m�me nombre de fois dans
	 * le meilleur des cas comme dans le pire des cas du moment que le fichier .wav est valide.
	 * Si le fichier .wav est invalide la complexit� sera d'ordre O(12) � O(22) 
	 * Si le fichier .wav est valide la complexit� est d'ordre O(n + 22 + 30 + 8 * nbCannaux)                                       
	 */
	public void process() {
	
		//On valide le fichier
		waveHandler.prog1_validate();
		
		//Si le fichier est valide on effectue la conversion 16 � 8 bits
		if (waveHandler.isValid()){

			//On modife l'ent�te du nouveau fichier
			waveHandler.convertHeader8bits();
				
			//Boucle qui parcours chaque �chantillon (de 2 ou 4 octets, dependant du nbChannel)
			int nbChannel = waveHandler.getNbChannel();
				
			for (int k=0;k<(waveHandler.getSubChunk2Size()/(nbChannel*2));k++){
				if (nbChannel == 1){
					//lit un sample de 2 octets
					int valeur16 = waveHandler.pop(nbChannel*2, "mono");
						
					//Effectue la conversion 8 bits
					int valeur8 = 0;
						
					if (valeur16 >= 32767){
						valeur8 = (valeur16 - 32767)/256;
					}
					if (valeur16 < 32767){
						valeur8 = 128 + (valeur16 / 256);
					}
						
					byte[] newData = {(byte) valeur8};
						
					//On envoie les donn�es au fichier de sortie
					waveHandler.push(newData);
						
				}
				if (nbChannel == 2){
					//Effectue la conversion 8 bits
					//Gauche
					int valeurLeft16 = waveHandler.pop(nbChannel*2, "gauche");
					int valeurLeft8 = 0;
					if (valeurLeft16 >= 32767){
						valeurLeft8 = (valeurLeft16 - 32767)/256;
					}
					if (valeurLeft16 < 32767){
						valeurLeft8 = 128 + (valeurLeft16 / 256);
					}
						
					byte[] newDataLeft = {(byte) valeurLeft8};
						
					//On envoie les donn�es au fichier de sortie
					waveHandler.push(newDataLeft);
						
					//Droite
					int valeurRight16 = waveHandler.pop(nbChannel*2, "droite");
					int valeurRight8 = 0;
					if (valeurRight16 >= 32767){
						valeurRight8 = (valeurRight16 - 32767)/256;
					}
					if (valeurRight16 < 32767){
						valeurRight8 = 128 + (valeurRight16 / 256);
					}
						
					byte[] newDataRight = {(byte) valeurRight8};
						
					//On envoie les donn�es au fichier de sortie
					waveHandler.push(newDataRight);
				}
			}
		}
			
		//On ferme le fichier
		waveHandler.close();
	}
}
