package gti310.tp4;

import java.util.ArrayList;
/**
 * Cette classe calcule les valeurs DCT en utilisant la méthode de multiplication matricielle
 * elle précalcule les valeurs des cosinus et les met dans une matrice transposé pour pouvoir
 * effectuer la multiplication, fortement inspirée du site en référence
 * @see http://www.nyx.net/~smanley/dct/DCT.java
 * @author Stephen Manley -- smanley@eagle.uccb.ns.ca
 * @author Riad Chebli (révision et modifications)
 * @author J.S Bonnin  (révision et modifications) *
 */

public class DCT {

	//http://www.nyx.net/~smanley/dct/DCT.java

	private static int[][]  blocSortie = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE]; 
	public static double cT[][]= new double[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	public static double c[][] = new double[Main.BLOCK_SIZE][Main.BLOCK_SIZE];


	public static ArrayList<ArrayList<int[][]>> process(ArrayList<ArrayList<int[][]>> listeBloc8x8){

		for (int i=0;i<listeBloc8x8.size();i++){
			for (int j=0;j<listeBloc8x8.get(0).size();j++){
				int[][] bloc = listeBloc8x8.get(i).get(j);				
				listeBloc8x8.get(i).set(j,dct(bloc));
			}
		}

		return listeBloc8x8;
	}


	public static double C(double valeur){
		if (valeur == 0){
			valeur = (1 / (Math.sqrt(2))); 
		}else{
			valeur = 1;
		}
		return valeur;
	}

	public static int[][] dct(int input[][]){


		int output[][] = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
		double temp[][] = new double[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
		double temp1;
		int i;
		int j;
		int k;
		initMatrix();

		for (i = 0; i < Main.BLOCK_SIZE; i++){

			for (j = 0; j < Main.BLOCK_SIZE; j++){

				temp[i][j] = 0.0;
				for (k = 0; k < Main.BLOCK_SIZE; k++){

					temp[i][j] += (((int)(input[i][k])) * cT[k][j]);
				}
			}
		}

		for (i = 0; i < Main.BLOCK_SIZE; i++){

			for (j = 0; j < Main.BLOCK_SIZE; j++){

				temp1 = 0.0;

				for (k = 0; k < Main.BLOCK_SIZE; k++){

					temp1 += (c[i][k] * temp[k][j]);
				}

				output[i][j] = (int)Math.round(temp1);
			}
		}

		return output;
	}

	public static int[][] DCTinverse(int input[][]){

		int output[][] = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
		double temp[][] = new double[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
		double temp1;
		int i;
		int j;
		int k;
		initMatrix();

		for (i=0; i<Main.BLOCK_SIZE; i++){

			for (j=0; j<Main.BLOCK_SIZE; j++){

				temp[i][j] = 0.0;

				for (k=0; k<Main.BLOCK_SIZE; k++){

					temp[i][j] += input[i][k] * c[k][j];
				}
			}
		}

		for (i=0; i<Main.BLOCK_SIZE; i++){

			for (j=0; j<Main.BLOCK_SIZE; j++){

				temp1 = 0.0;

				for (k=0; k<Main.BLOCK_SIZE; k++){

					temp1 += cT[i][k] * temp[k][j];
				}

				//temp1 += 0.0;
				
				if (temp1 < 0)
				{
					output[i][j] = 0;
				}
				else if (temp1 > 255)
				{
					output[i][j] = 255;
				}
				else
				{
					output[i][j] = (int)Math.round(temp1);
				}





			}
		}

		return output;
	}


	private static void initMatrix(){

		int i;
		int j;	

		for (j = 0; j < Main.BLOCK_SIZE; j++){

			double nn = (double)(Main.BLOCK_SIZE);
			c[0][j]  = 1.0 / Math.sqrt(nn);
			cT[j][0] = c[0][j];
		}

		for (i = 1; i < 8; i++){

			for (j = 0; j < 8; j++)
			{
				double jj = (double)j;
				double ii = (double)i;
				c[i][j]  = Math.sqrt(2.0/8.0) * Math.cos(((2.0 * jj + 1.0) * ii * Math.PI) / (2.0 * 8.0));
				cT[j][i] = c[i][j];
			}
		}
	}





}
