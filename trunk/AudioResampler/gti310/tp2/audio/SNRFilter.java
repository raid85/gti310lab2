package gti310.tp2.audio;
import gti310.tp2.io.FileSink;
import gti310.tp2.io.FileSource;

import java.io.FileNotFoundException;
import java.util.ArrayList ;



/*Il s'agit d'avoir les deux "paths" des deux fichiers (l'original et le sampled
 * ensuite on compare les fichiers (en petit morceaux)valeur par valeur dans la 
 * formule a la page 2 des notes de lab.
 *  1.FileSource
 *  2.FileSink (s)
 *  for i = 0,nbfilesink
 *  2.Split	 *
 *  3.Read
 *  4.Calculate(......)
 *  5.Store
 *  --
 *  6.Sort
 *  7.Display
 *  ,*/

public class SNRFilter implements AudioFilter {

	private final int HEADER_SIZE = 44;	
	private boolean fichierInvalide = false;
	private boolean fichier8bits = false ;
	private int nbChannel = 0;
	private String fichierOriginal = "";
	private String fichierCompare= "";
	private ArrayList compares ;

    
	public void process() {

		try {
			FileSource fsource = new FileSource(fichierOriginal);
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
			int subChunkSize = readBytesLittle(header[40], header[41], header[42], header[43]);
			System.out.println("Taille du fichier : " + chunkSize);
			System.out.println("SubChunkSize : " + subChunkSize);
		}

		catch (FileNotFoundException e) {
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
	
	//big-endian?
	public static final byte[] intToByteArray_big(int value) {
		return new byte[]{
		(byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };
		}
	
	//little-endian?
	public static final byte[] intToByteArray_little(int value) {
		return new byte[]{
		 (byte)(value & 0xff), (byte)(value >> 8 & 0xff), (byte)(value >> 16 & 0xff), (byte)(value >>> 24) };
		}

	}
