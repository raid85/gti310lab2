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
	
	public static ArrayList<ArrayList<int[]>> processINV(){
		
		ArrayList<ArrayList<int[]>> listeTab64 = new ArrayList<ArrayList<int[]>>();
		listeTab64.add(new ArrayList<int[]>() );
		listeTab64.add(new ArrayList<int[]>() );
		listeTab64.add(new ArrayList<int[]>() );
		
		boolean loop = true;
		int[] tab64 = new int[64];
		int yuv = 0;
		int nbTotalTab64 = (Main.hauteur)/8; // remplacer par (largeur image / 8)
		int incTab64 = 0;
		
		while(loop){
			int valeur = 0;
			
			valeur = Entropy.readDC();
			
			if (valeur == 0xffffffff){
				loop = false;
			}else{
			
				tab64[0]= valeur;
				listeTab64.get(yuv).add(tab64);
				
				incTab64++;
				
				if (incTab64>=nbTotalTab64-1){
					yuv++;
					incTab64 = 0;
					if (yuv>=3){
						//a enlever
						System.out.println(" Erreur yuv > 3...");
						break;
					}
				}
				
			}
		
	}
		return listeTab64;
}
}
