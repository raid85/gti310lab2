package gti310.tp4;

import java.util.ArrayList;

public class DCT {

	private static int[][]  blocSortie = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE]; 
	
	public static ArrayList<ArrayList<int[][]>> process(ArrayList<ArrayList<int[][]>> listeBloc8x8){
		
		for (int i=0;i<listeBloc8x8.size();i++){
			for (int j=0;j<listeBloc8x8.get(0).size();j++){
				int[][] bloc = listeBloc8x8.get(i).get(j);
					dct(bloc);
				listeBloc8x8.get(i).set(j,bloc);
			}
		}
		
		return listeBloc8x8;
	}
	public static int[][] dct(int[][]  blocEntree){
		
		int u,v,i,j;
		double somme = 0;
		
		for (u=0; u<Main.BLOCK_SIZE; u++){
			for (v=0; v<Main.BLOCK_SIZE; v++){
				for (i=0; i<Main.BLOCK_SIZE; i++){
					for (j=0; j<Main.BLOCK_SIZE; j++){
						somme = somme + Math.cos(((2*i+1)*u*Math.PI)/16)*Math.cos(((2*j+1)*v*Math.PI)/16);
					}
				}
				blocSortie[u][v] = (int) ((C((double)u)*C((double)v))/4*somme);
			}
		}	
		return blocSortie;
	}
	
	public static double C(double valeur){
		if (valeur == 0){
			valeur = (1 / (Math.sqrt(2))); 
	    }else{
	    	valeur = 1;
	    }
		return valeur;
	}
}
