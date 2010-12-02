package gti310.tp4;
import java.util.ArrayList;

import junit.framework.TestCase;

public class TestDecoupage8x8 extends TestCase {
	
	private ArrayList<ArrayList<int[][]>> listeBloc8x8 = new ArrayList<ArrayList<int[][]>>();
	
	public void testdecoupe(){
		
		int test[][][]=new int[3][1024][1024];
		listeBloc8x8 =Decoupage8x8.decoupe(test);
		assertTrue(listeBloc8x8.get(0).get(0).length==8);
		
		
		
	}

}
