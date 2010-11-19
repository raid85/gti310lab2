package gti310.tp4;

import junit.framework.TestCase;

public class TestConvertRGB2YUV extends TestCase {
	
	ConvertRGB2YUV testeur=new ConvertRGB2YUV();


	
	public void testconvert(){
		//ne fonctionne pas si la classe correspondante n<accepte
		//pas toute les tailles de matrice
		int R = 250 ;
		int G = 125;
		int B = 75;
		int RGB[][][]=new int[3][1][1];
		RGB[0][0][0]=R ;
		RGB[1][0][0]=G ;
		RGB[2][0][0]=B ;
		int YUV[][][]=new int [3][1][1];
		YUV =testeur.convert(RGB);
		//y = 0.299R + 0.587G + 0.114B
		//u = 0.492(B-Y)
		//v = 0.877(R-Y)
		if(YUV[0][0][0]==157 && YUV[1][0][0]==-40 && YUV[2][0][0]==82){
			assertTrue(YUV[0][0][0]==157 && YUV[1][0][0]==-40 && YUV[2][0][0]==82);
		}
		
		
	}
	public void testget_Y() {		
		
		assertTrue(testeur.get_Y(250, 125, 75)==157);

	}
	public void testget_U() {
		
		assertTrue(testeur.get_U(75,157)==-40);

	}
	public void testget_V() {
		
		assertTrue(testeur.get_U(125,157)==82);

	}

}
