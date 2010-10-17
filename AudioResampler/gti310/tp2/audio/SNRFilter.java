package gti310.tp2.audio;
import java.util.ArrayList ;



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
	
	private final int HEADER_SIZE = 44;	
	private boolean fichierInvalide = false;
	private int nbChannel = 0;
	private String fichierOriginal = "";
	private String fichierCompare= "";
	private ArrayList compares ;

	
	public void process() {
		// TODO Auto-generated method stub
		
		
	}

}
