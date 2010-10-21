package gti310.tp2.audio;
import java.lang.Math;

/**
 * La SNRFilter a la responsabilité de trouver un Sound to noise ratio
 * (SNR) à partir d'un fichier original et d'une série de fichiers 
 * à comparer recus en paramètres
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
	 * comparés ainsi que le tableau de valeur de SNR à partir du chemin d'acces
	 * du fichier original et du nombre d'éléments a comparer qui sont tirés 
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
	 * Méthode qui ajoute un fichier à comparer au tableau à partir
	 * du chemin d'accès donné en paramètre et, incrémente le pointeur
	 * @param String
	 * @return void
	 */
	public void addCompare (String Compare){

		WaveHandler tempo = new WaveHandler (Compare);		
		HandlerCompares[pointeur] = tempo;
		this.pointeur= pointeur +1 ;

	}
	/**
	 * Méthode redéfinie de l'interface AudioFilter qui sert à traiter les deux
	 * fichiers pour en calculer le SNR correspondant. Ensuite le SNR est placé 
	 * dans un tableau et trié par la méthode de tri insertion.
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

				//valeur comparée
				for(int j=0; j<nbEch;j++){
					double EchCompare = (double)HandlerCompares[i].getData();					
					val2 = val2 + EchCompare;					
				}

				//formule a lintérieur du LOG
				double top = Math.pow(val1, 2);
				double bottom = Math.pow((val1-val2), 2);								
				SNRinter= 10 * Math.log10(top/bottom);
				val2=0;				
				tableauSNR[i] = SNRinter;
				System.out.println("SNR Correspondant:  "+tableauSNR[i]+" dB");

				


			}
			else{System.out.println("Désolé ce programme ne prend pas en charge les fichiers de formats différents");}

		}
		triInsertion();

	}
	/**
	 * Méthode qui tri le tableau à l'aide de l'algorithme de tri par insertion
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
		System.out.println("Voici les valeurs triées ;");
		for(int j =0; j<tableauSNR.length;j++){			
			System.out.println("_______________________");
			System.out.println("|"+tableauSNR[j]+" dB |");
			System.out.println("¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯");

		}
	}
}
