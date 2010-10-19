package gti310.tp2.audio;
import java.io.FileNotFoundException;
import gti310.tp2.io.*;
/**
 * La classe WaveHandler à la responsabilité de traiter les fichiers
 * wave, principalemet de démêler l'encodage de l'entête. 
*@author Riad
*@version 0.1
*@
*
*/
public class WaveHandler {
	
	private final int HEADER_SIZE = 44;	
	private boolean fichierInvalide = false;
	private int nbChannel = 0;
	private String fichierEntree = "";
	private String fichierSortie= "";
	private byte[] header ;
	private String headerHEX[];

	
	/** 
	 * Constructeur de la classe	                          
	@param   String fichierEntree, String fichierSortie                          
	@see         J.S
	 */
	public WaveHandler (String fichierEntree, String fichierSortie){
		this.fichierEntree = fichierEntree;
		this.fichierSortie = fichierSortie;
	}
	/** 
	 * Constructeur de la classe qui place l'entête du fichier dans deux tableaux
	 * le premier qui représente l'entête et le second qui représente le 
	 * bloc de data	                          
	@param   String fichierEntree                    
	 */
	public WaveHandler (String fichierEntree){
		try{
			FileSource fsource = new FileSource(fichierEntree);			
			this.header = fsource.pop(HEADER_SIZE);
		    this.headerHEX = new String[HEADER_SIZE];
			
			for (int i = 0; i < HEADER_SIZE; i++){
				System.out.print(Integer.toHexString(header[i]));
				headerHEX[i] = Integer.toHexString(header[i]);
			}
		}
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
	/** 
	 * Cette méthode à pour tâche de retourner un entier correspondant
	 * aux deux bytes recu en format little endian                          
	@param   byte B1, byte B2
	@return  int                  
	 */
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
	/** 
	 * Cette méthode à pour tâche de retourner un entier correspondant
	 * aux quatres bytes recu en format little endian                          
	@param   byte B1, byte B2, byte B3, byte B4
	@return  int                  
	 */
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
	/** 
	 * big endian ?                 
	@param   int
	@return  byte[]                
	 */
	public static final byte[] intToByteArray_big(int value) {
		return new byte[]{
				(byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };
	}
	/** 
	 * little endian ?                 
	@param   int
	@return  byte[]                
	 */
	public static final byte[] intToByteArray_little(int value) {
		return new byte[]{
				(byte)(value & 0xff), (byte)(value >> 8 & 0xff), (byte)(value >> 16 & 0xff), (byte)(value >>> 24) };
	}

	
}
