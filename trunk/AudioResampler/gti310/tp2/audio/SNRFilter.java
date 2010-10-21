package gti310.tp2.audio;
import java.lang.Math;
import java.util.Stack;



public class SNRFilter implements AudioFilter {

	private WaveHandler HandlerComparant ;
	private WaveHandler[]HandlerCompares = new WaveHandler[10] ;
	private int pointeur = 0;
	private short taille = 0; 
	private double tableauSNR[]= new double[10] ;
	private double SNRinter = 0;
	double val1 = 0;
	double val2=0;

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
					
					val1= val1+Math.pow(EchOriginal, 2);
					val2 = val2+Math.pow((EchOriginal-EchCompare), 2);
					//System.out.println("Val1 "+val1+" Val2 "+val2);
					
					//System.out.println("Process@SNRFilter : i : "+i+" j : "+j+" SNR : "+SNRinter);

				}
				
				System.out.println("Val1 "+val1+" Val2 "+val2);
				SNRinter= 10 * Math.log10(val1/val2);
				System.out.println("SNRinter.."+SNRinter);
				System.out.println("I.."+i);
				tableauSNR[i] = SNRinter;

			}

		}

	}

}
