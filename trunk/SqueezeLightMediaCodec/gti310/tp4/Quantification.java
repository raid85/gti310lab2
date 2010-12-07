package gti310.tp4;

import java.util.ArrayList;

public class Quantification {
	
	static double factQ;
	static int[][] Qy =  {
		      { 46, 41, 40, 46, 54, 60, 61, 71 },
		      { 42, 42, 44, 49, 56, 58, 70, 75 },
		      { 44, 43, 46, 54, 50, 57, 69, 56 },
		      { 44, 47, 42, 49, 61, 87, 80, 62 },
		      { 48, 42, 47, 56, 68, 109, 103, 77 },
		      { 44, 45, 55, 64, 81, 104, 113, 92 },
		      { 49, 64, 78, 87, 103, 121, 120, 101 },
		      { 72, 92, 95, 98, 112, 100, 103, 95 }
		    };
	
	static int[][] Quv =  {
		      { 47, 48, 44, 57, 95, 95, 95, 95 },
		      { 48, 41, 46, 66, 95, 95, 95, 95 },
		      { 44, 46, 56, 59, 95, 95, 95, 95 },
		      { 47, 66, 95, 95, 95, 95, 95, 95 },
		      { 95, 95, 95, 95, 95, 95, 95, 95 },
		      { 95, 95, 95, 95, 95, 95, 95, 95 },
		      { 95, 95, 95, 95, 95, 95, 95, 95 },
		      { 95, 95, 95, 95, 95, 95, 95, 95}
		    };

	public static ArrayList<ArrayList<int[][]>> process(ArrayList<ArrayList<int[][]>> listeBloc8x8, double factQu){
		factQ = factQu;
		
		for (int i=0;i<listeBloc8x8.size();i++){
			for (int j=0;j<listeBloc8x8.get(0).size();j++){
				int[][] bloc = listeBloc8x8.get(i).get(j);
				listeBloc8x8.get(i).set(j,quantification(bloc, i));
			}
		}
		
		return listeBloc8x8;
	}
	
	public static int[][] quantification(int[][]  blocEntree, int yuv){
		int[][]  blocSortie = new int[8][8]; 
		double alpha=0;
		
		if (factQ==100){
			blocSortie = blocEntree;
		}else{
			for(int u=0;u<Main.BLOCK_SIZE;u++){
				for(int v=0;v<Main.BLOCK_SIZE;v++){
					if(factQ <= 49){
						alpha = 50/factQ;
					}
					if(factQ >= 50 && factQ <= 99){
						alpha = (200 - (2*factQ)) / 100;
					}
					if(yuv==0){
						blocSortie[u][v]=(int) Math.round(blocEntree[u][v]/(alpha*Qy[u][v]));
					}
					if(yuv==1 || yuv ==2){
						blocSortie[u][v]= (int) Math.round(blocEntree[u][v]/(alpha*Quv[u][v]));
					}
				}
			}
		}
		
		return blocSortie;
	}
	
	public static ArrayList<ArrayList<int[][]>> processINV(ArrayList<ArrayList<int[][]>> listeBloc8x8, double factQu){
	
		factQ = factQu;
		
		for (int i=0;i<listeBloc8x8.size();i++){
			for (int j=0;j<listeBloc8x8.get(0).size();j++){
				int[][] bloc = listeBloc8x8.get(i).get(j);
				listeBloc8x8.get(i).set(j,quantificationINV(bloc, i));
			}
		}
		
		return listeBloc8x8;
		
	}
	
	
	public static int[][] quantificationINV(int[][]  blocEntree, int yuv){
		int[][]  blocSortie = new int[8][8]; 
		double alpha=0;
		
		if (factQ==100){
			blocSortie = blocEntree;
		}else{
			for(int u=0;u<Main.BLOCK_SIZE;u++){
				for(int v=0;v<Main.BLOCK_SIZE;v++){
					if(factQ <= 49){
						alpha = 50/factQ;
					}
					if(factQ >= 50 && factQ <= 99){
						alpha = (200 - (2*factQ)) / 100;
					}
					if(yuv==0){
						blocSortie[u][v]= (int) Math.round(blocEntree[u][v]*(alpha*Qy[u][v]));
					}
					if(yuv==1 || yuv ==2){
						blocSortie[u][v]= (int) Math.round(blocEntree[u][v]*(alpha*Quv[u][v]));
					}
				}
			}
		}
		
		return blocSortie;
	}
		
}
