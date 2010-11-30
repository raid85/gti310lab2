package gti310.tp4;

import java.util.ArrayList;

public class DPCM {

	public static void process(ArrayList<ArrayList<int[]>> listeTab64){
		
		for (int i=0;i<listeTab64.size();i++){
			
			//On prend la valeur de DC de reference
			int premierDC = listeTab64.get(i).get(0)[0];
			
			for (int j=0;j<listeTab64.get(0).size();j++){
				
				if (j==0){
					Entropy.writeDC(premierDC);
				}else{
					int valeur = listeTab64.get(i).get(j)[0] - premierDC;
					Entropy.writeDC(valeur);
				}				
			}
		}
	}
}
