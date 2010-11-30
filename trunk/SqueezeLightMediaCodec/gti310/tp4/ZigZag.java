package gti310.tp4;

import java.util.ArrayList;

public class ZigZag {

	public ArrayList<ArrayList<int[]>> process(ArrayList<ArrayList<int[][]>> listeBloc8x8){
		
		ArrayList<ArrayList<int[]>> listeTab64 = new ArrayList<ArrayList<int[]>>();
		listeTab64.add(new ArrayList<int[]>() );
		
		for (int i=0;i<listeBloc8x8.size();i++){
			for (int j=0;j<listeBloc8x8.get(0).size();j++){
				int[][] bloc = listeBloc8x8.get(i).get(j);
					int[] tab64 = zigzag(bloc);
					listeTab64.get(i).add(tab64);
			}
		}
		return listeTab64;
	}
	
	//http://www.coderanch.com/t/485470/java/java/zigzag-traverse-matrix
	//http://rosettacode.org/wiki/Zig-zag_matrix
	public int[] zigzag(int[][] blocEntree){
		
		int[] tab64 = new int[64];
		//int size = blocEntree.length;
		int size = 8;
		
		int i= 1;
		int j= 1;
		
		for(int element= 0;element < size * size;element++){
			tab64[element] = blocEntree[i-1][j-1];
			if((i + j) % 2 == 0){
				// Even stripes
				if(j < size){
					j++;
				}else{
					i+= 2;
				}
				if(i > 1){
					i--;
				}
			}else{
				// Odd stripes
				if(i < size){
					i++;
				}else{
					j+= 2;
				}
				if(j > 1){
					j--;
				}
			}
		}
		
		return tab64;
	}
}
