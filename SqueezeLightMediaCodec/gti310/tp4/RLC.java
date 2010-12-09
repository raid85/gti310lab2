package gti310.tp4;

import java.util.ArrayList;

public class RLC {

public static void process(ArrayList<ArrayList<int[]>> listeTab64){
		
		for (int i=0;i<listeTab64.size();i++){
			for (int j=0;j<listeTab64.get(0).size();j++){
				
				int nbRep = -1;
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
						nbRep = -1;
						valeurRep = valeur;
					}
				}				
			}
		}
	}

public static ArrayList<ArrayList<int[]>> processINV(ArrayList<ArrayList<int[]>> listeTab64){
	
	boolean loop = true;
	int[] tab64 = new int[64];
	int count = 1;
	int yuv = 0;
	int nbTotalTab64 = (Main.hauteur)/8; // remplacer par (largeur image / 8)
	int incTab64 = 0;
	
	while(loop){
		Object valeur = null;
		
		valeur = Entropy.readAC();
		
		if (valeur==null){
			loop = false;
		}else{
			
			int[] paire = (int[]) valeur;
			int nbRep = paire[0];
			int val = paire[1];
			
	if (count>=64){
				
				//On ajoute le tableau de 64 à la liste pour le bon indice yuv
				listeTab64.get(yuv).add(tab64);
				
				count=1;
				incTab64++;
				
				//On regarde si on a passé toute les tableau pour y,u, ou v
				if (incTab64 >=nbTotalTab64){
					incTab64 = 0;
					yuv++;
					if(yuv>=3){
						//a enlever
						System.out.println(" Erreur yuv > 3...");
						break;
					}
				}
			}
	
			//traite la paire
			if (nbRep==0){
				tab64[count]= val;
				count++;
			}else{
				for (int i=0;i<nbRep;i++){
					if (count >= 64){
						//a enlever
						System.out.println(" Erreur debordement tab 64 ...");
						break;
					}
					tab64[count]= val;
					count++;
				}
			}
			
		
	
		}
	}
	
	return listeTab64;
}
}
