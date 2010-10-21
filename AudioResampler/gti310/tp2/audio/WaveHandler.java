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
	private short bps = 0;
	private byte[] data = new byte[1];
	private int BPS = 0;
	private int nbChannel = 0;
	private int chunkSize = 0;
	private int subChunk2Size = 0;
	
	FileSink fsink;
	FileSource fsource;


	/** 
	 * Constructeur de la classe	                          
	@param   String fichierEntree, String fichierSortie                          
	@see         J.S
	 */
	public WaveHandler (String fichierEntree, String fichierSortie){
		this.fichierEntree = fichierEntree;
		this.fichierSortie = fichierSortie;
		
		//On lit l'entête du fichier
		try {
			fsource = new FileSource(fichierEntree);
			fsink = new FileSink(fichierSortie);
			this.header = fsource.pop(HEADER_SIZE);
			headerHEX = new String[HEADER_SIZE];
			
			int i = 0;
			for (i = 0; i < HEADER_SIZE; i++){
				//System.out.print(Integer.toHexString(header[i]));
				headerHEX[i] = Integer.toHexString(header[i]);
			  	}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
	 * Retourne si le fichier est un fichier 16 bits valide 	                          
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
	public int getNbChannel() {
		return nbChannel;
	}
	/** 
	 * "Getter" du chunkSize	                          
	@param   void                       
	@return  short 
	 */
	public int getChunkSize() {
		return chunkSize;
	}
	/** 
	 * "Getter" du subChunk2Size                          
	@param   void                       
	@return  short 
	 */
	public int getSubChunk2Size() {
		return subChunk2Size;
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
	 * Méthode interne pour valider le fichier wave et receuillir
	 * les principales informations issues de son entête pour le programme1               
	@param   void
	@return  void                
	 */	
	public void prog1_validate (){		

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
		BPS = readBytesLittle(header[34], header[35]);
		if (!(BPS == 16)){
			fichierInvalide = true;
			System.out.println("Fichier 16 bits invalide");
		}
		else{
			System.out.println("Fichier 16 bits valide");
		}
		
		//On regarde le nombre de canaux du fichier
		nbChannel = readBytesLittle(header[22], header[23]);
		if (nbChannel == 1){
			System.out.println("Fichier Mono");
		}
		if (nbChannel == 2){
			System.out.println("Fichier Stereo");
		}
		
		//On regarde la longueur du data du fichier wav (ChunkSize)
		chunkSize = readBytesLittle(header[4], header[5], header[6], header[7]);
		subChunk2Size = readBytesLittle(header[40], header[41], header[42], header[43]);
		System.out.println("ChunkSize : " + chunkSize);
		System.out.println("SubChunk2Size : " + subChunk2Size);

	}
	
	/** 
	 * Méthode interne pour valider le fichier wave et receuillir
	 * les principales informations issues de son entête pour le programme1               
	@param   void
	@return  void                
	 */	
	public void convertHeader8bits (){
		//On met les bits par échantillons de 16 à 8
		BPS = 8;
        header[34]= (byte) BPS;
		header[35]= (byte) 0;
		
		//On change le byterate (== SampleRate * NumChannels * BitsPerSample/8)
		int sampleRate = readBytesLittle(header[24], header[25], header[26], header[27]);
		System.out.println("Samplerate_8bits : " + sampleRate);
	    int byteRate = sampleRate * nbChannel * BPS/8;
	    System.out.println("ByteRate_8bits : " + byteRate);
	    byte[] byteRateTab = intToByteArray_little(byteRate);
	    header[28]= byteRateTab[0];
		header[29]= byteRateTab[1];
		header[30]= byteRateTab[2];
		header[31]= byteRateTab[3];
	    	
		//On change le blockAlign (== NumChannels * BitsPerSample/8)
		int blockAlign = nbChannel * BPS/8;
		byte[] blockAlignTab = intToByteArray_little(blockAlign);
		System.out.println("BlockAlign_8bits : " + blockAlign);
	    header[32]= blockAlignTab[0];
		header[33]= blockAlignTab[1];
	
		//On change le SubChunk2Size (== NumSamples * NumChannels * BitsPerSample/8)
		int subChunk2Size_8bits = subChunk2Size / 2;
		byte[] subChunk2SizeTab = intToByteArray_little(subChunk2Size_8bits);
		System.out.println("subChunk2Size_8bits : " + subChunk2Size_8bits);
	    header[40]= subChunk2SizeTab[0];
		header[41]= subChunk2SizeTab[1];
		header[42]= subChunk2SizeTab[2];
		header[43]= subChunk2SizeTab[3];
		
		//On met ChunkSize = 36 + SubChunk2Size ou 4 + (8 + SubChunk1Size) + (8 + SubChunk2Size)
		int chunkSize_8bits = 36 + subChunk2Size_8bits;
		byte[] chunkSizeTab = intToByteArray_little(chunkSize_8bits);
		System.out.println("ChunkSize_8bits : " + chunkSize_8bits);
	    header[4]= chunkSizeTab[0];
		header[5]= chunkSizeTab[1];
		header[6]= chunkSizeTab[2];
		header[7]= chunkSizeTab[3];
		
		//On transmet l'entête dans le fichier de sortie
		fsink.push(header);
		
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
	 * Cette méthode à pour tâche de faire un "pop" sur le fichierSource de n bytes                        
	@param   int nbBytes
	@return  int                  
	 */
	public int pop(int nbBytes, String type){
		
		//On retourne les 2 premier bytes pour un fichier mono et pour la gauche stereo puis les 2 dernier byte pour la droite stereo
		int result =0;
		if (type.equals("mono") || type.equals("gauche")){
			byte buffer[] = fsource.pop(nbBytes);
			result = readBytesLittle(buffer[0], buffer[1]);
		}
		if (type.equals("droite")){
			byte buffer[] = fsource.pop(nbBytes);
			result = readBytesLittle(buffer[2], buffer[3]);
		}
		
		return result;
	}
	
	/** 
	 * Cette méthode à pour tâche de faire un "push" vers le fichierSortie                        
	@param   byte[] data 
	@return  void                  
	 */
	public void push(byte[] data){
		fsink.push(data);
	}
	/** 
	 * Cette méthode à pour tâche de fermer le fichierSortie                       
	@param   void
	@return  void                  
	 */
	public void close(){
		fsink.close();
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
	 * Conversion entier à bytes[4] big endian                 
	@param   int
	@return  byte[]                
	 */
	private static final byte[] intToByteArray_big(int value) {
		return new byte[]{
				(byte)(value >>> 24), (byte)(value >> 16 & 0xff), (byte)(value >> 8 & 0xff), (byte)(value & 0xff) };
	}
	/** 
	 * Conversion entier à bytes[4] little endian                 
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
