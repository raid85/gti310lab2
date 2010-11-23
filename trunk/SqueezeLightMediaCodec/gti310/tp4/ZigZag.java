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
	
	public int[] zigzag(int[][] blocEntree){
		int[] tab64 = new int[64];
		
		//....
		
		return tab64;
	}
}
