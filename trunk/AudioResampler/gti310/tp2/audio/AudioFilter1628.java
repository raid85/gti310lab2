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
		
		//On lit l'entête du fichier
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
			
			//Vérifie que c'est un fichier 16 bits par échantillon valide
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
			int subChunk2Size = readBytesLittle(header[40], header[41], header[42], header[43]);
			System.out.println("ChunkSize : " + chunkSize);
			System.out.println("SubChunk2Size : " + subChunk2Size);
			
			//Si le fichier est valide on effectue la conversion 16 à 8 bits
			if (fichierInvalide == false){
				
				//On modife l'entête du nouveau fichier
				
				//On met les bits par échantillons de 16 à 8
				BPS = 8;
                header[34]= (byte) BPS;
				header[35]= (byte) 0;
				
				//On change le byterate (== SampleRate * NumChannels * BitsPerSample/8)
				int sampleRate = readBytesLittle(header[24], header[25], header[26], header[27]);
				System.out.println("Samplerate : " + sampleRate);
			    int byteRate = sampleRate * nbChannel * BPS/8;
			    System.out.println("ByteRate : " + byteRate);
			    byte[] byteRateTab = intToByteArray_little(byteRate);
			    header[28]= byteRateTab[0];
				header[29]= byteRateTab[1];
				header[30]= byteRateTab[2];
				header[31]= byteRateTab[3];
			    	
				//On change le blockAlign (== NumChannels * BitsPerSample/8)
				int blockAlign = nbChannel * BPS/8;
				byte[] blockAlignTab = intToByteArray_little(blockAlign);
				System.out.println("BlockAlign : " + blockAlign);
			    header[32]= blockAlignTab[0];
				header[33]= blockAlignTab[1];
			
				//On change le SubChunk2Size (== NumSamples * NumChannels * BitsPerSample/8)
				int subChunk2Size_8bits = subChunk2Size / 2;
				byte[] subChunk2SizeTab = intToByteArray_little(subChunk2Size_8bits);
			    header[40]= subChunk2SizeTab[0];
				header[41]= subChunk2SizeTab[1];
				header[42]= subChunk2SizeTab[2];
				header[43]= subChunk2SizeTab[3];
				
				//On met ChunkSize = 36 + SubChunk2Size ou 4 + (8 + SubChunk1Size) + (8 + SubChunk2Size)
				int chunkSize_8bits = 36 + subChunk2Size_8bits;
				byte[] chunkSizeTab = intToByteArray_little(chunkSize_8bits);
			    header[4]= chunkSizeTab[0];
				header[5]= chunkSizeTab[1];
				header[6]= chunkSizeTab[2];
				header[7]= chunkSizeTab[3];
				
				System.out.println("ChunkSize_8bits : " + chunkSize_8bits);
				System.out.println("subChunk2Size_8bits : " + subChunk2Size_8bits);
			
				fsink.push(header);
				
				//Boucle qui parcours chaque échantillon (de 2 ou 4 octets, dependant du nbChannel)
				for (int k=0;k<(subChunk2Size/(nbChannel*2));k++){
					if (nbChannel == 1){
						//lit un sample de 2 octets
						byte[] buffer = fsource.pop(nbChannel*2);
						
						int valeur16 = readBytesLittle(buffer[0], buffer[1]);
						int valeur8 = 0;
						
						if (valeur16 >= 32767){
							valeur8 = (valeur16 - 32767)/256;
						}
						if (valeur16 < 32767){
							valeur8 = 128 + (valeur16 / 256);
						}
						
						byte[] newData = {(byte) valeur8};
						fsink.push(newData);
						
					}
					if (nbChannel == 2){
						//lit un sample de 4 octets
						byte[] buffer = fsource.pop(nbChannel*2);

						//Gauche
						int valeurLeft16 = readBytesLittle(buffer[0], buffer[1]);
						int valeurLeft8 = 0;
						if (valeurLeft16 >= 32767){
							valeurLeft8 = (valeurLeft16 - 32767)/256;
						}
						if (valeurLeft16 < 32767){
							valeurLeft8 = 128 + (valeurLeft16 / 256);
						}
						
						byte[] newDataLeft = {(byte) valeurLeft8};
						fsink.push(newDataLeft);
						
						//Droite
						int valeurRight16 = readBytesLittle(buffer[0], buffer[1]);
						int valeurRight8 = 0;
						if (valeurRight16 >= 32767){
							valeurRight8 = (valeurRight16 - 32767)/256;
						}
						if (valeurRight16 < 32767){
							valeurRight8 = 128 + (valeurRight16 / 256);
						}
						
						byte[] newDataRight = {(byte) valeurRight8};
						fsink.push(newDataRight);
					}
				}
			}
			
			fsink.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Retourne la valeur int "unsigned" des octets en little endian
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
	
	//Retourne la valeur int "unsigned" des octets en little endian
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
	
	//Conversion entier à bytes[4] big-endian?
	public static final byte[] intToByteArray_big(int value) {
		return new byte[]{
		(byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };
		}
	
	//Conversion entier à bytes[4] little-endian?
	public static final byte[] intToByteArray_little(int value) {
		return new byte[]{
		 (byte)(value), (byte)(value >> 8 ), (byte)(value >> 16 ), (byte)(value >>> 24) };
		}
	
	//Conversion entier à bytes[4] little-endian?
	//public static final byte[] intToByteArray_little(int value) {
		//return new byte[]{
		// (byte)(value & 0xff), (byte)(value >> 8 & 0xff), (byte)(value >> 16 & 0xff), (byte)(value >>> 24) };
		//}
	
	//Conversion entier à bytes[2] little-endian?
	public static final byte[] intToByteArray2_little(int value) {
		return new byte[]{
		 (byte)(value & 0xff), (byte)(value >> 8 & 0xff)};
		}
}
