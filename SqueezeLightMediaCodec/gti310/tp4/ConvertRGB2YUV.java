package gti310.tp4;

public class ConvertRGB2YUV {

	public static int[][][] matriceYUV = null;

	public static int[][][] convert_RGB_to_YUV(int[][][] matriceRGB){

		//y = 0.299R + 0.587G + 0.114B
		//u = 0.492(B-Y)
		//v = 0.877(R-Y)
		
		int n = matriceRGB.length;

		int i,j,k = 0;
		
		for (i=0; i<3; i++){
			for (j=0; j<n; j++){
				for (k=0; k<n; k++){
					if ( i==0){
						matriceYUV[i][j][k] = get_Y(matriceRGB[0][j][k], matriceRGB[1][j][k], matriceRGB[2][j][k]);
					}
					if ( i==1){
						matriceYUV[i][j][k] = get_U(matriceRGB[2][j][k], matriceYUV[0][j][k] );
					}
					if ( i==2){
						matriceYUV[i][j][k] = get_V(matriceRGB[0][j][k], matriceYUV[0][j][k] );
					}
				}
			}
		}

		return matriceYUV;

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
}
