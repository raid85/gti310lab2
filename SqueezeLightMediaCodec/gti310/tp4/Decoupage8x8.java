package gti310.tp4;

import java.util.ArrayList;

public class Decoupage8x8 {

	private static ArrayList<ArrayList<int[][]>> listeBloc8x8 = new ArrayList<ArrayList<int[][]>>();
	
	public static ArrayList<ArrayList<int[][]>> decoupe(int[][][] matrice){
		
		//On regarde la longueur des matrices
		int size = matrice[0].length;
		
		//On la divise par 8 pour savoir le nb de matrice 8x8 qu'on aura pour chaque composante (Y-U-V)
		int nbBloc = size/8;
		
		int compteurBloc_K = 0;
		int compteurBloc_M = 0;
		boolean done = false;
		int[][] bloc8x8= new int[8][8];
		ArrayList<int[][]> liste1 = new ArrayList<int[][]>();
		ArrayList<int[][]> liste2 = new ArrayList<int[][]>();
		ArrayList<int[][]> liste3 = new ArrayList<int[][]>();
		
		int i,j,k,m,l;
		
		for (i=0; i<3; i++){
			//compteurBloc_K = 0;
			//compteurBloc_M = 0;
			for (j=0; j<nbBloc; j++){
				for(l=0; l<nbBloc; l++){
					for (k=0; k<8; k++){
						for (m=0; m<8; m++){
						
						bloc8x8[k][m] = matrice[i][k + (8*j)][m + (8*l)];
					}
				}
				if (i == 0){
					liste1.add(bloc8x8);
				}
				if (i == 1){
					liste2.add(bloc8x8);
				}
				if (i == 2){
					liste3.add(bloc8x8);
				}
		
				//compteurBloc_K = compteurBloc_K + 8;
				
				//if (compteurBloc_K >= size-7 || done){
					//compteurBloc_M = compteurBloc_M + 8;
					//compteurBloc_K = 0;
					//done = true;
				//}
				//if (compteurBloc_M >= size-7 ){
					//break;
				//}
				}
			}
		}	
		
		listeBloc8x8.add(liste1);
		listeBloc8x8.add(liste2);
		listeBloc8x8.add(liste3);
		
		return listeBloc8x8;
	}
	
public static int[][][] decoupeINV(ArrayList<ArrayList<int[][]>> listeBloc8x8){
		
		//On regarde le nombre de bloc par liste yuv
		int nbBloc =listeBloc8x8.get(0).size();
		
		//On multiplie par 8 pour savoir les dimensions de chaque matrice (y,u et v)
		int size = nbBloc*8;
		
		int compteurBloc_K = 0;
		int compteurBloc_M = 0;
		boolean done = false;
		
		int[][][] matrice = new int[3][size][size];
		
		int i,j,k,m;
		
		for (i=0; i<3; i++){
			compteurBloc_K = 0;
			compteurBloc_M = 0;
			for (j=0; j<nbBloc; j++){
				for (k=0; k<8; k++){
					for (m=0; m<8; m++){
						matrice[i][k+ compteurBloc_K][m+ compteurBloc_M] = listeBloc8x8.get(i).get(j)[k][m];
					}
				}
			
				compteurBloc_K = compteurBloc_K + 8;
				
				if (compteurBloc_K >= size-7 || done){
					compteurBloc_M = compteurBloc_M + 8;
					compteurBloc_K = 0;
					done = true;
				}
				if (compteurBloc_M >= size-7 ){
					break;
				}
			}
		}	

		return matrice;
	}
	
}
  