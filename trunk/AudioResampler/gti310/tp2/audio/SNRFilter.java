package gti310.tp2.audio;
import java.io.FileNotFoundException;




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

	public SNRFilter (String fichierComparant){

		this.HandlerComparant = new WaveHandler(fichierComparant);		

	}
	
	public void addCompare (String Compare){
		
		HandlerCompares[pointeur] = new WaveHandler(Compare);
		pointeur ++;
	}

	

	public void process() {
		int taille =HandlerCompares.length;
		

	}

}
