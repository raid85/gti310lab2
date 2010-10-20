package gti310.tp2.audio;
import java.lang.Math;



public class SNRFilter implements AudioFilter {

	private WaveHandler HandlerComparant ;
	private WaveHandler[]HandlerCompares ;
	private int pointeur = 0;
	private short taille = 0; 
	private double tableauSNR[] ;
	private double SNRinter;

	public SNRFilter (String fichierComparant){

		this.HandlerComparant = new WaveHandler(fichierComparant);		

	}

	public void addCompare (String Compare){

		HandlerCompares[pointeur] = new WaveHandler(Compare);
		pointeur ++;
	}



	public void process() {

		this.taille =(short) HandlerCompares.length;

		for(short i=0; i<=this.taille; i++){

			if((HandlerCompares[i].getDataChunkSize()==HandlerComparant.getDataChunkSize())&& (HandlerCompares[i].getNbChannel()==HandlerComparant.getNbChannel())&&(HandlerCompares[i].getBps()==HandlerComparant.getBps()))
			{

				int nbEch=HandlerCompares[i].getDataChunkSize();

				System.out.println("Les deux fichiers sont mono, 8 bits et de même taille  "+(nbEch+44)+"Bytes");				

				for(int j=0; j<=nbEch;j++){

					double val1 = Math.pow(HandlerComparant.getData(),2);
					double val2 = Math.pow((HandlerComparant.getData()-HandlerCompares[i].getData()), 2);				
					SNRinter = SNRinter +  Math.log10(val1/val2);
					System.out.println("Process@SNRFilter : i : "+i+" j : "+j+" SNR : "+SNRinter);

				}
				
				tableauSNR[i] = SNRinter;

			}

		}

	}

}
