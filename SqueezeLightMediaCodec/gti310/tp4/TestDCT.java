package gti310.tp4;
import java.util.ArrayList;

import junit.framework.TestCase;

public class TestDCT extends TestCase {
	
	DCT test = new DCT();
	private static int[][]  blocTest = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	private static ArrayList<ArrayList<int[][]>> listeTestBloc8 = new ArrayList<ArrayList<int[][]>>();

	public void testProcess(){}
	
	public void testC(){
		
		assertTrue(DCT.C(32)==1 && DCT.C(0)==1 / Math.sqrt(2));
	}
	
	public void testDCT(){
		
		blocTest[0][0]= 1;
		blocTest[0][1]= 1;
		blocTest[0][2]= 1;
		blocTest[0][3]= 1;
		blocTest[0][4]= 1;
		blocTest[0][5]= 1;
		blocTest[0][6]= 1;
		blocTest[0][7]= 1;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 2;
		blocTest[1][2]= 2;
		blocTest[1][3]= 2;
		blocTest[1][4]= 2;
		blocTest[1][5]= 2;
		blocTest[1][6]= 2;
		blocTest[1][7]= 2;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 2;
		blocTest[1][2]= 2;
		blocTest[1][3]= 2;
		blocTest[1][4]= 2;
		blocTest[1][5]= 2;
		blocTest[1][6]= 2;
		blocTest[1][7]= 2;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 2;
		blocTest[1][2]= 2;
		blocTest[1][3]= 2;
		blocTest[1][4]= 2;
		blocTest[1][5]= 2;
		blocTest[1][6]= 2;
		blocTest[1][7]= 2;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 2;
		blocTest[1][2]= 2;
		blocTest[1][3]= 2;
		blocTest[1][4]= 2;
		blocTest[1][5]= 2;
		blocTest[1][6]= 2;
		blocTest[1][7]= 2;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 2;
		blocTest[1][2]= 2;
		blocTest[1][3]= 2;
		blocTest[1][4]= 2;
		blocTest[1][5]= 2;
		blocTest[1][6]= 2;
		blocTest[1][7]= 2;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 2;
		blocTest[1][2]= 2;
		blocTest[1][3]= 2;
		blocTest[1][4]= 2;
		blocTest[1][5]= 2;
		blocTest[1][6]= 2;
		blocTest[1][7]= 2;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 2;
		blocTest[1][2]= 2;
		blocTest[1][3]= 2;
		blocTest[1][4]= 2;
		blocTest[1][5]= 2;
		blocTest[1][6]= 2;
		blocTest[1][7]= 2;
		
		
		
	}
	
	
}
