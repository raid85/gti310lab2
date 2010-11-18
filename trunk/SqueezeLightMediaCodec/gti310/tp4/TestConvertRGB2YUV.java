package gti310.tp4;

import junit.framework.TestCase;

public class TestConvertRGB2YUV extends TestCase {

	ConvertRGB2YUV testeur=new ConvertRGB2YUV();

	/**
	 * Test la création de la matrice nulle par le constructeur
	 * par défaut
	 */
	public void testConvertRGB2YUV(){
		assertEquals(testeur.getMatriceYUV(),null);
	}
	public void testconvert_RGB_to_YUV(){
		int R = 250 ;
		int G = 125;
		int B = 75;
		
	}
	public void testget_Y() {

	}
	public void testget_U() {

	}
	public void testget_V() {

	}

}
