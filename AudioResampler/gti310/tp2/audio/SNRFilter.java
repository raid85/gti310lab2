package gti310.tp2.audio;
import java.lang.Math;
import java.util.Stack;



public class SNRFilter implements AudioFilter {

	private WaveHandler HandlerComparant ;
	private WaveHandler[]HandlerCompares  ;
	private int pointeur = 0;
	private double tableauSNR[] ;
	private double SNRinter = 0;
	private double val1 = 0;
	private double val2=0;
	private short nbCompares = 0;

	public SNRFilter (String fichierComparant, short nbCompares){

		this.HandlerComparant = new WaveHandler(fichierComparant);		
		this.nbCompares =nbCompares ;
		HandlerCompares = new WaveHandler[this.nbCompares];
		tableauSNR = new double[this.nbCompares];

	}

	public void addCompare (String Compare){
		
		WaveHandler tempo = new WaveHandler (Compare);		
		HandlerCompares[pointeur] = tempo;
		this.pointeur= pointeur +1 ;
		
	}



	public void process() {

	

		for(short i=0; i<HandlerCompares.length; i++){

			if((HandlerCompares[i].getDataChunkSize()==HandlerComparant.getDataChunkSize())&& (HandlerCompares[i].getNbChannel()==HandlerComparant.getNbChannel())&&(HandlerCompares[i].getBps()==HandlerComparant.getBps()))
			{

				int nbEch=HandlerCompares[i].getDataChunkSize();

				System.out.println("Les deux fichiers sont mono, 8 bits et de même taille  "+(nbEch+44)+"Bytes");				
				
				//valeur originale
				if(val1 == 0){
					for (int j=0; j<nbEch;j++){
						double EchOriginal =(double)HandlerComparant.getData();
						//val1 = val1+Math.pow(EchOriginal, 2);
						val1 = val1 + EchOriginal;
					}
				}
				
				//valeur comparée
				for(int j=0; j<nbEch;j++){
                    double EchCompare = (double)HandlerCompares[i].getData();
					//System.out.println("EchOriginal "+EchOriginal+" EchCompare "+EchCompare+" j  "+j);
					val2 = val2 + EchCompare;
					//val2 = val2+Math.pow((EchCompare), 2);
					//System.out.println("Val1 "+val1+" Val2 "+val2);					
					//System.out.println("Process@SNRFilter : i : "+i+" j : "+j+" SNR : "+SNRinter);

				}
				//formule a lintérieur du LOG
				double top = Math.pow(val1, 2);
				double bottom = Math.pow(val1-val2, 2);
				System.out.println("Val1 "+top+" Val2 "+bottom);
				
				SNRinter= 10 * Math.log10(top/bottom);
				val2=0;
				System.out.println("SNRinter.."+SNRinter);
				System.out.println("I.."+i);
				tableauSNR[i] = SNRinter;
				

			}

		}

	}

}
