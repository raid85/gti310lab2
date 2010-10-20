package gti310.tp2.audio;
import java.io.FileNotFoundException;
import java.lang.Math;




/*Il s'agit d'avoir les deux "paths" des deux fichiers (l'original et le sampled
 * ensuite on compare les fichiers (en petit morceaux)valeur par valeur dans la 
 * formule a la page 2 des notes de lab.
 *  1.FileSource
 *  2.FileSink (s)
 *  for i = 0,nbfilesink
 *  2.Split	 *
 *  3.Read
 *  4.Calculate(......)
 *  5.Store
 *  --
 *  6.Sort
 *  7.Display
 *  ,*/

public class SNRFilter implements AudioFilter {

	private WaveHandler HandlerComparant ;
	private WaveHandler[]HandlerCompares ;
	private int pointeur = 0;
	private short taille = 0; 
	private double tableauSNR[] ;
	private double SNRinter [];

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

			if((HandlerCompares[i].getDataChunkSize()==HandlerComparant.getDataChunkSize())&& (HandlerCompares[i].getNbChannel()==HandlerComparant.getNbChannel())&&(HandlerCompares[i].getBps()==HandlerComparant.getBps())){
				
				int nbEch=HandlerCompares[i].getDataChunkSize();
				
				System.out.println("Les deux fichiers sont mono, 8 bits et de même taille  "+(nbEch+44)+"Bytes");				
				
				for(int j=0; j<=nbEch;j++){
					
					double snr = 10 * Math.log10((Math.pow(HandlerComparant.getData(),2))/(Math.pow((HandlerComparant.getData()-HandlerCompares[i].getData()), 2))) ;
					
					SNRinter[j]= snr;

				}
				
				for (int k = 0; k<= SNRinter.length;k++){
					
					
				}
				
			}

		}

	}

}
