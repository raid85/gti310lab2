package gti310.tp2.audio;
import java.lang.Math;
import java.util.Stack;



public class SNRFilter implements AudioFilter {

	private WaveHandler HandlerComparant ;
	private WaveHandler[]HandlerCompares = new WaveHandler[10] ;
	private int pointeur = 0;
	private short taille = 0; 
	private double tableauSNR[]= new double[10] ;
	private int SNRinter = 0;

	public SNRFilter (String fichierComparant){

		this.HandlerComparant = new WaveHandler(fichierComparant);		

	}

	public void addCompare (String Compare){

		
		WaveHandler tempo = new WaveHandler (Compare);		
		HandlerCompares[pointeur] = tempo;
		this.pointeur= pointeur +1 ;
		
	}



	public void process() {

		this.taille =(short) HandlerCompares.length;

		for(short i=0; i<=this.taille; i++){

			if((HandlerCompares[i].getDataChunkSize()==HandlerComparant.getDataChunkSize())&& (HandlerCompares[i].getNbChannel()==HandlerComparant.getNbChannel())&&(HandlerCompares[i].getBps()==HandlerComparant.getBps()))
			{

				int nbEch=HandlerCompares[i].getDataChunkSize();

				System.out.println("Les deux fichiers sont mono, 8 bits et de même taille  "+(nbEch+44)+"Bytes");				

				for(int j=0; j<=nbEch;j++){
                    
					double EchOriginal =(double)HandlerComparant.getData();					
					double EchCompare = (double)HandlerCompares[i].getData();
					
					
					//System.out.println("Process@SNRFilter : i : "+i+" j : "+j+" SNR : "+SNRinter);

				}
				
				tableauSNR[i] = SNRinter;

			}

		}

	}

}
