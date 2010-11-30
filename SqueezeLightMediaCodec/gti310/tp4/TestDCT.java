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
	
	public void testDCT(){}
	
	
}
