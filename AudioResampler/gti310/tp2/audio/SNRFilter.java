package gti310.tp2.audio;
import java.lang.Math;




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

		}

	}

}
