package gti310.tp4;

public class DCT {

	int[][]  blocSortie = new int[8][8]; 
	
	public int[][] process(int[][]  blocEntree){
		
		int u,v,i,j;
		double somme = 0;
		
		for (u=0; u<8; u++){
			for (v=0; v<8; v++){
				for (i=0; i<8; i++){
					for (j=0; j<8; j++){
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
