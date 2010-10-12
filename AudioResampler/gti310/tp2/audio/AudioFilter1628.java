package gti310.tp2.audio;
import java.io.FileNotFoundException;

import gti310.tp2.io.*;

public class AudioFilter1628 implements AudioFilter {

	private final int HEADER_SIZE = 44;
	
	private boolean fichierInvalide = false;
	private int nbChannel = 0;
	private String fichierEntree = "";
	private String fichierSortie= "";
	
	public AudioFilter1628 (String fichierEntree, String fichierSortie){
		this.fichierEntree = fichierEntree;
		this.fichierSortie = fichierSortie;
	}
	
	public void process() {
		
		//On lit l'ent�te du fichier
		try {
			FileSource fsource = new FileSource(fichierEntree);
			FileSink fsink = new FileSink(fichierSortie);
			byte[] header = fsource.pop(HEADER_SIZE);
			String headerHEX[] = new String[HEADER_SIZE];
			int i = 0;
			
			for (i = 0; i < HEADER_SIZE; i++){
				System.out.print(Integer.toHexString(header[i]));
				headerHEX[i] = Integer.toHexString(header[i]);
			}
		
			System.out.println();
			
			//Valide le type de fichier RIFF 
			if (!(headerHEX[0].equals("52")) || !(headerHEX[1].equals("49")) || !(headerHEX[2].equals("46")) || !(headerHEX[3].equals("46"))){
				fichierInvalide = true;
				System.out.println("Type de fichier invalide");
			}
			else{
				System.out.println("Fichier RIFF valide");
			}
			
			//Valide le format PCM
			int PCM = readBytesLittle(header[20], header[21]);
			if (!(PCM == 1)){
				fichierInvalide = true;
				System.out.println("Format PCM invalide");
			}
			else{
				System.out.println("Format PCM valide");
			}
			
			//V�rifie que c'est un fichier 16 bits par �chantillon valide
			int BPS = readBytesLittle(header[34], header[35]);
			if (!(BPS == 16)){
				fichierInvalide = true;
				System.out.println("Fichier 16 bits invalide");
			}
			else{
				System.out.println("Fichier 16 bits valide");
			}
			
			//On regarde le nombre de canaux du fichier
			int nbChannel = readBytesLittle(header[22], header[23]);
			if (nbChannel == 1){
				System.out.println("Fichier Mono");
			}
			if (nbChannel == 2){
				System.out.println("Fichier Stereo");
			}
			
			//On regarde la longueur du data du fichier wav (ChunkSize)
			int chunkSize = readBytesLittle(header[4], header[5], header[6], header[7]);
			int subChunkSize = readBytesLittle(header[40], header[41], header[42], header[43]);
			System.out.println("Taille du fichier : " + chunkSize);
			System.out.println("SubChunkSize : " + subChunkSize);
			
			//Si le fichier est valide on effectue la conversion 16 � 8 bits
			if (fichierInvalide == false){
				
				//On modife l'ent�te du nouveau fichier
				//8 bits, taille, etc
				//fsink.push(header);
				
				//Boucle qui parcours chaque �chantillon (2 ou 4 octets depend du nbChannel)
				for (int k=0;k<(subChunkSize/(nbChannel*2));k++){
					if (nbChannel == 1){
						byte[] buffer = fsource.pop(2);
				
						int valeur16 = readBytesLittle(buffer[0], buffer[1]);
						int valeur8 = 0;
						
						if (valeur16 >= 32767){
							valeur8 = (valeur16 - 32767)/256;
						}
						if (valeur16 < 32767){
							valeur8 = 128 + (valeur16 / 256);
						}
						
						//System.out.println("Valeur_16bits : " + k + " : " +  valeur16);
						//System.out.println("Valeur_8bits : " + k + " : " +  valeur8);
						
					}
					if (nbChannel == 2){
						byte[] buffer = fsource.pop(4);
						
						//Gauche
						int valeurLeft16 = readBytesLittle(buffer[0], buffer[1]);
						int valeurLeft8 = 0;
						if (valeurLeft16 >= 32767){
							valeurLeft8 = (valeurLeft16 - 32767)/256;
						}
						if (valeurLeft16 < 32767){
							valeurLeft8 = 128 + (valeurLeft16 / 256);
						}
						
						//Droite
						int valeurRight16 = readBytesLittle(buffer[0], buffer[1]);
						int valeurRight8 = 0;
						if (valeurRight16 >= 32767){
							valeurRight8 = (valeurRight16 - 32767)/256;
						}
						if (valeurRight16 < 32767){
							valeurRight8 = 128 + (valeurRight16 / 256);
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
	}
	
	//Retourne la valeur "unsigned" des octets en little endian
	public int readBytesLittle(byte B1, byte B2){
		int firstByte = 0;
        int secondByte = 0;
        char result = 0;
        
        //On inverse car on est en little endian
        firstByte = 0xFF & (int)B2;
        secondByte = 0xFF & (int)B1;
        
        result  = (char) (firstByte << 8 | secondByte);
        
        return (int)result;
	}
	
	//Retourne la valeur "unsigned" des octets en little endian
	public int readBytesLittle(byte B1, byte B2, byte B3, byte B4){
		int firstByte = 0;
        int secondByte = 0;
        int thirdByte = 0;
        int fourthByte = 0;
        long result = 0;
        
        //On inverse car on est en little endian
        firstByte = 0xFF & (int)B4;
        secondByte = 0xFF & (int)B3;
        thirdByte = 0xFF & (int)B2;
        fourthByte = 0xFF & (int)B1;
        
        result = ((long) (firstByte << 24 | secondByte << 16| thirdByte << 8| fourthByte));
        
        return (int) result;
	}

	//Retourne la valeur "signed" des octets en little endian
//	public long readBytesLittleSigned(byte B1, byte B2){
//		int firstByte = 0;
//        int secondByte = 0;
//        char result = 0;
//        
//        //On inverse car on est en little endian
//        firstByte = 0xFF & (int)B2;
//        secondByte = 0xFF & (int)B1;
//        
//        result  = (char) (firstByte << 8 | secondByte);
//        
//        return (long)result;
//	}

}