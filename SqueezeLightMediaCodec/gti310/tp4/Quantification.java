package gti310.tp4;

import java.util.ArrayList;

public class Quantification {
	
	int factQ;
	int[][] Qy =  {
		      { 46, 41, 40, 46, 54, 60, 61, 71 },
		      { 42, 42, 44, 49, 56, 58, 70, 75 },
		      { 44, 43, 46, 54, 50, 57, 69, 56 },
		      { 44, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 },
		      { 48, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 },
		      { 44, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 },
		      { 49, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 },
		      { 72, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 }
		    };
	
	int[][] Quv =  {
		      { 0*0, 1*0, 2*0, 3*0, 0*0, 1*0, 2*0, 3*0 },
		      { 0*1, 1*1, 2*1, 3*1, 0*0, 1*0, 2*0, 3*0 },
		      { 0*2, 1*2, 2*2, 3*2, 0*0, 1*0, 2*0, 3*0 },
		      { 0*3, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 },
		      { 0*3, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 },
		      { 0*3, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 },
		      { 0*3, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 },
		      { 0*3, 1*3, 2*3, 3*3, 0*0, 1*0, 2*0, 3*0 }
		    };

	public ArrayList<ArrayList<int[][]>> process(ArrayList<ArrayList<int[][]>> listeBloc8x8, int factQ){
		this.factQ = factQ;
		
		for (int i=0;i<listeBloc8x8.size();i++){
			for (int j=0;j<listeBloc8x8.get(0).size();j++){
				int[][] bloc = listeBloc8x8.get(i).get(j);
					quantification(bloc, i);
				listeBloc8x8.get(i).set(j,bloc);
			}
		}
		
		return listeBloc8x8;
	}
	
	public int[][] quantification(int[][]  blocEntree, int yuv){
		int[][]  blocSortie = new int[8][8]; 
		double alpha=0;
		
		if (factQ==100){
			blocSortie = blocEntree;
		}else{
			for(int u=0;u<8;u++){
				for(int v=0;v<8;v++){
					if(factQ <= 49){
						alpha = 50/factQ;
					}
					if(factQ >= 50 && factQ <= 99){
						alpha = (200 - 2*factQ)/100;
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
}
