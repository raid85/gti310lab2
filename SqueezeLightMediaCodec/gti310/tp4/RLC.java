package gti310.tp4;

import java.util.ArrayList;

public class RLC {

public static void process(ArrayList<ArrayList<int[]>> listeTab64){
		
		for (int i=0;i<listeTab64.size();i++){
			for (int j=0;j<listeTab64.get(0).size();j++){
				
				int nbRep = 0;
				int valeurRep = 0;
				
				for (int k=1;k<64;k++){
					
					if (k==1){
						valeurRep = listeTab64.get(i).get(j)[k];
					}
					
					int valeur = listeTab64.get(i).get(j)[k];
					
					//On incrémente si on a une repetition
					if (valeur == valeurRep){
						nbRep++;
						if (k==63){
							Entropy.writeAC(nbRep, valeurRep);
						}
					}else{
						Entropy.writeAC(nbRep, valeurRep);
						nbRep = 0;
						valeurRep = valeur;
					}
				}				
			}
		}
	}

public static void processINV(){
	
	ArrayList<ArrayList<int[]>> listeTab64 = new ArrayList<ArrayList<int[]>>();
	listeTab64.add(new ArrayList<int[]>() );
	listeTab64.add(new ArrayList<int[]>() );
	listeTab64.add(new ArrayList<int[]>() );
	
	boolean loop = true;
	
	while(loop){
		Object valeur = null;
		
		valeur = Entropy.readAC();
		
		if (valeur==null){
			loop = false;
		}else{
			
			int[] paire = (int[]) valeur;
			int nbRep = paire[0];
			int val = paire[1];
			
			//traite la paire...
		}
	}
	
	
}
}
