package gti310.tp4;

public class ConvertRGB2YUV {

	private static int[][][] matriceYUV = null;

	public static int[][][] convert(int[][][] matriceRGB){
		
		int n = 0;

		//longueur d'une des 3 matrices... (les 3 sont egale)
		n = matriceRGB[0].length;
		matriceYUV = new int[Main.COLOR_SPACE_SIZE][n][n];
		
		
		int i = 0, j = 0, k = 0;
		
		
		for (i=0; i<3; i++){
			for (j=0; j<n; j++){
				for (k=0; k<n; k++){
					if ( i==0){
						//on rempli la matrice des Y						
						matriceYUV[i][j][k] = get_Y(matriceRGB[0][j][k], matriceRGB[1][j][k], matriceRGB[2][j][k]);
					}
					if ( i==1){
						//on rempli la matrice des U
						matriceYUV[i][j][k] = get_U(matriceRGB[2][j][k], matriceYUV[0][j][k] );
						
					}
					if ( i==2){
						//on rempli la matrice des V
						matriceYUV[i][j][k] = get_V(matriceRGB[0][j][k], matriceYUV[0][j][k] );
											}
				}
			}
		}

		return matriceYUV;

	}
	
public static int[][][] convertINV(int[][][] matriceYUV){
		
		int n = 0;

		//longueur d'une des 3 matrices... (les 3 sont egale)
		n = matriceYUV[0].length;
		int[][][] matriceRGB = new int[Main.COLOR_SPACE_SIZE][n][n];
		
		
		int i = 0, j = 0, k = 0;
		
		for (i=0; i<3; i++){
			for (j=0; j<n; j++){
				for (k=0; k<n; k++){
					if ( i==0){
						//on rempli la matrice des R						
						matriceRGB[i][j][k] = get_R(matriceYUV[0][j][k], matriceYUV[2][j][k]);
					}
					if ( i==1){
						//on rempli la matrice des G
						matriceRGB[i][j][k] = get_G(matriceYUV[0][j][k], matriceYUV[2][j][k], matriceYUV[1][j][k] );
						
					}
					if ( i==2){
						//on rempli la matrice des B
						matriceRGB[i][j][k] = get_B(matriceYUV[0][j][k], matriceYUV[1][j][k] );
					}
				}
			}
		}

		return matriceRGB;

	}

public static int get_R(double Y, double V){
	int R = (int) Math.round(1.164*(Y-16)+ 1.596*(V - 128));
	return R;
}
public static int get_G(double Y, double V, double U){
	int G = (int) Math.round(1.164*(Y - 16) - 0.813*(V - 128) - 0.391*(U - 128));
	return G;
}
public static int get_B(double Y, double U){
	int B = (int) Math.round(1.164*(Y - 16) + 2.018*(U - 128));
	return B;
}

	public static int get_Y(double R, double G, double B){
		int Y = (int) Math.round(0.299*R + 0.587*G + 0.114*B);
		return Y;
	}
	public static int get_U(double B, double Y){
		int U = (int) Math.round(0.492*(B-Y));
		return U;
	}
	public static int get_V(double R, double Y){
		int V = (int) Math.round(0.877*(R-Y));
		return V;
	}

	public static int[][][] getMatriceYUV() {
		return matriceYUV;
	}
}
