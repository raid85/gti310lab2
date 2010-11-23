package gti310.tp4;

public class ConvertRGB2YUV {

	private int[][][] matriceYUV = new int[Main.COLOR_SPACE_SIZE][256][256];

	public int[][][] convert(int[][][] matriceRGB){

		//y = 0.299R + 0.587G + 0.114B
		//u = 0.492(B-Y)
		//v = 0.877(R-Y)

		int n = 0, ni = 0, nj = 0, nk = 0;
		int i = 0, j = 0, k = 0;
		
		n = matriceRGB.length;
		//ni = matriceRGB[0].length;
		nj = matriceRGB[1].length;
		nk = matriceRGB[2].length;
		
		for (i=0; i<n; i++){
			for (j=0; j<nj; j++){
				for (k=0; k<nk; k++){
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

	public int get_Y(double R, double G, double B){
		int Y = (int) Math.round(0.299*R + 0.587*G + 0.114*B);
		return Y;
	}
	public int get_U(double B, double Y){
		int U = (int) Math.round(0.492*(B-Y));
		return U;
	}
	public int get_V(double R, double Y){
		int V = (int) Math.round(0.877*(R-Y));
		return V;
	}

	public int[][][] getMatriceYUV() {
		return matriceYUV;
	}
}
