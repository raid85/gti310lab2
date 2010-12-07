package gti310.tp4;

import java.util.ArrayList;

public class ZigZag {
	
	public static int zigZag[][] = new int[64][2];


	public static ArrayList<ArrayList<int[]>> process(ArrayList<ArrayList<int[][]>> listeBloc8x8){
		
		ArrayList<ArrayList<int[]>> listeTab64 = new ArrayList<ArrayList<int[]>>();
		listeTab64.add(new ArrayList<int[]>() );
		listeTab64.add(new ArrayList<int[]>() );
		listeTab64.add(new ArrayList<int[]>() );
		
		try {
			for (int i=0;i<listeBloc8x8.size();i++){
				for (int j=0;j<listeBloc8x8.get(0).size();j++){
					int[][] bloc = listeBloc8x8.get(i).get(j);
						int[] tab64 = zigzag(bloc);
						listeTab64.get(i).add(tab64);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listeTab64;
	}
	
	//Référence:
	//http://rosettacode.org/wiki/Zig-zag_matrix
	public static int[] zigzag(int[][] blocEntree){
		
		int[] tab64 = new int[64];
		int i= 1;
		int j= 1;
		
		try {
			for(int element= 0;element < 64;element++){
				tab64[element] = blocEntree[i-1][j-1];
				if((i + j) % 2 == 0){
					// Even stripes
					if(j < Main.BLOCK_SIZE){
						j++;
					}else{
						i+= 2;
					}
					if(i > 1){
						i--;
					}
				}else{
					// Odd stripes
					if(i < Main.BLOCK_SIZE){
						i++;
					}else{
						j+= 2;
					}
					if(j > 1){
						j--;
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tab64;
	}
	
	

	public static int[][] zigzagINV(int[] tab64){
		int[][] bloc8x8 = new int[8][8];
		
		

		
		return bloc8x8;
	}
	
	public static ArrayList<ArrayList<int[][]>> processINV(ArrayList<ArrayList<int[]>> listeTab64){
		ArrayList<ArrayList<int[][]>> listeBloc8x8 = new ArrayList<ArrayList<int[][]>>();
		
		
		
		return listeBloc8x8;
	}
	
}
