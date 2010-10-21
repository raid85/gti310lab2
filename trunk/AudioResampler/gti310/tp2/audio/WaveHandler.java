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
	private String fichierEntree = "";
	private String fichierSortie= "";
	private String nomFichier;
	private byte[] header ;
	private String headerHEX[];
	private int dataChunkSize = 0 ;
	private FileSource FichierOriginal ;
	private short bps = 0 ;
	private short nbChannel = 0;
	private byte[] data = new byte[1];


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
			this.nomFichier=fichierEntree;
			FichierOriginal = new FileSource(fichierEntree);			
			this.header = FichierOriginal.pop(HEADER_SIZE);
			this.headerHEX = new String[HEADER_SIZE];

			for (int i = 0; i < HEADER_SIZE; i++){			
				headerHEX[i] = Integer.toHexString(header[i]);
			}
		}
		catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
		validate ();
	}


	/** 
	 * Retourne si le fichier est valide ()	                          
	@param   void
	@return boolean                          
	@see         J.S
	 */
	public boolean isValid (){
		return !fichierInvalide ;
	}
	/** 
	 * "Getter" du nombre de bits par echantillons	                          
	@param   void                       
	@return  short (bps)
	 */
	public short getBps() {
		return bps;
	}
	/** 
	 * "Getter" du nombre de canaux	                          
	@param   void                       
	@return  short 
	 */
	public short getNbChannel() {
		return nbChannel;
	}
	/** 
	 * Méthode qui retourne un entier représentant la taille de la
	 * partie de données du fichier wave                 
	@param   none
	@return  int                
	 */
	public int getDataChunkSize (){

		this.dataChunkSize = readBytesLittle(header[40], header[41], header[42], header[43]);
		//System.out.println("DATA CHUNK SIZE"+this.toString());
		return this.dataChunkSize;
	}
	/** 
	 * Methode qui retourne le prochain Byte lu converti en
	 * entier	                          
	@param    none                          
	@return double
	 */
	public double getData() {

		data=FichierOriginal.pop(1);
		double datai = 0;
		datai= 0xFF & data[0];
		//System.out.println("BYTE CONVERTI ENVOYE @ SNR FILTER par WaveHandler  "+datai+"Trace  "+this.toString());
		return datai;

	}
	/** 
	 * Méthode interne pour valider le fichier wave et receuillir
	 * les principales informations issues de son entête                
	@param   void
	@return  void                
	 */	
	private void validate (){		

		int PCM = readBytesLittle(header[20], header[21]);
		if (!(PCM == 1)){			
			fichierInvalide = true;
			System.out.println("Format PCM invalide");
		}


		this.bps = (short) readBytesLittle(header[34], header[35]);

		if (!(bps == 16)&&!(bps == 8)){			
			fichierInvalide = true;
			System.out.println("Fichier invalide (nombre de bits) = "+bps);
		}		
		//On regarde le nombre de canaux du fichier
		this.nbChannel = (short) readBytesLittle(header[22], header[23]);

	}
	/** 
	 * Cette méthode à pour tâche de retourner un entier correspondant
	 * aux deux bytes recu en format little endian                          
	@param   byte B1, byte B2
	@return  int                  
	 */
	private int readBytesLittle(byte B1, byte B2){

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
	private int readBytesLittle(byte B1, byte B2, byte B3, byte B4){

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
	private static final byte[] intToByteArray_big(int value) {
		return new byte[]{
				(byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };
	}
	/** 
	 * little endian ?                 
	@param   int
	@return  byte[]                
	 */
	private static final byte[] intToByteArray_little(int value) {
		return new byte[]{
				(byte)(value & 0xff), (byte)(value >> 8 & 0xff), (byte)(value >> 16 & 0xff), (byte)(value >>> 24) };
	}

	public String toString(){
		
		return this.nomFichier;
		
	}

}
