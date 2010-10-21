package gti310.tp2.audio;
import java.lang.Math;

/**
 * La SNRFilter a la responsabilit� de trouver un Sound to noise ratio
 * (SNR) � partir d'un fichier original et d'une s�rie de fichiers 
 * � comparer recus en param�tres
 * @author Riad
 * @version 1.0
 */
public class SNRFilter implements AudioFilter {

	private WaveHandler HandlerComparant ;
	private WaveHandler[]HandlerCompares  ;
	private int pointeur = 0;
	private double tableauSNR[] ;
	private double SNRinter = 0;
	private double val1 = 0;
	private double val2=0;
	private short nbCompares = 0;

	/**
	 * Constructeur qui qui initialise le tableau de fichiers
	 * compar�s ainsi que le tableau de valeur de SNR � partir du chemin d'acces
	 * du fichier original et du nombre d'�l�ments a comparer qui sont tir�s 
	 * des arguments fournis lors de l'appel du programme.
	 * @see Application.java
	 * @param String, short
	 */	
	public SNRFilter (String fichierComparant, short nbCompares){

		this.HandlerComparant = new WaveHandler(fichierComparant);		
		this.nbCompares =nbCompares ;
		HandlerCompares = new WaveHandler[this.nbCompares];
		tableauSNR = new double[this.nbCompares];

	}
	/**
	 * M�thode qui ajoute un fichier � comparer au tableau � partir
	 * du chemin d'acc�s donn� en param�tre et, incr�mente le pointeur
	 * @param String
	 * @return void
	 */
	public void addCompare (String Compare){

		WaveHandler tempo = new WaveHandler (Compare);		
		HandlerCompares[pointeur] = tempo;
		this.pointeur= pointeur +1 ;

	}
	/**
	 * M�thode red�finie de l'interface AudioFilter qui sert � traiter les deux
	 * fichiers pour en calculer le SNR correspondant. Ensuite le SNR est plac� 
	 * dans un tableau et tri� par la m�thode de tri insertion.
	 * @param void
	 * @return void
	 */
	public void process() {


		for(short i=0; i<HandlerCompares.length; i++){

			if((HandlerCompares[i].getDataChunkSize()==HandlerComparant.getDataChunkSize())&& (HandlerCompares[i].getNbChannel()==HandlerComparant.getNbChannel())&&(HandlerCompares[i].getBps()==HandlerComparant.getBps()))
			{

				int nbEch=HandlerCompares[i].getDataChunkSize();				
				System.out.println("****************************************************************************************");
				System.out.println("Comparaison du fichier :  "+HandlerCompares[i].toString()+" Taille : "+(nbEch+44)+" Octets");				

				//valeur originale
				if(val1 == 0){
					for (int j=0; j<nbEch;j++){
						double EchOriginal =(double)HandlerComparant.getData();						
						val1 = val1 + EchOriginal;
					}
				}

				//valeur compar�e
				for(int j=0; j<nbEch;j++){
					double EchCompare = (double)HandlerCompares[i].getData();					
					val2 = val2 + EchCompare;					
				}

				//formule a lint�rieur du LOG
				double top = Math.pow(val1, 2);
				double bottom = Math.pow((val1-val2), 2);								
				SNRinter= 10 * Math.log10(top/bottom);
				val2=0;				
				tableauSNR[i] = SNRinter;
				System.out.println("SNR Correspondant:  "+tableauSNR[i]+" dB");

				


			}
			else{System.out.println("D�sol� ce programme ne prend pas en charge les fichiers de formats diff�rents");}

		}
		triInsertion();

	}
	/**
	 * M�thode qui tri le tableau � l'aide de l'algorithme de tri par insertion
	 * et contient aussi une boucle d'affichage
	 * @param  void
	 * @return void
	 */
	private void triInsertion(){
		int pointeur;
		double valInter;

		for(int i = 1; i < tableauSNR.length ; i++)
		{    
			valInter = tableauSNR[i];
			pointeur = i-1;
			
			while(pointeur>=0 && tableauSNR[pointeur]>valInter){
				
				tableauSNR[pointeur+1] = tableauSNR[pointeur];
				pointeur--;
			}
			tableauSNR[pointeur+1] = valInter;
		}
		
		//affichage
		System.out.println("*************************");
		System.out.println("Voici les valeurs tri�es ;");
		for(int j =0; j<tableauSNR.length;j++){			
			System.out.println("_______________________");
			System.out.println("|"+tableauSNR[j]+" dB |");
			System.out.println("�����������������������");

		}
	}
}
