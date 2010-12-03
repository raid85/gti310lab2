package gti310.tp4;
import java.util.ArrayList;

import junit.framework.*;

public class TestZigZag extends TestCase {
	
	private ArrayList<ArrayList<int[][]>> listeTest = new ArrayList<ArrayList<int[][]>>();
	private ArrayList<int[][]> listeTesti = new ArrayList<int[][]>();
	private ArrayList<ArrayList<int[]>> listeTestRecu = new ArrayList<ArrayList<int[]>>();
	private int[][]blocTest = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	private int[]blocRecu = new int[Main.BLOCK_SIZE*Main.BLOCK_SIZE];
	
	public void testProcess(){
		
		blocTest[0][0]= 33;
		blocTest[0][1]= 0;
		blocTest[0][2]= 0;
		blocTest[0][3]= 0;
		blocTest[0][4]= 0;
		blocTest[0][5]= 0;
		blocTest[0][6]= 0;		
		blocTest[0][7]= 0;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 0;
		blocTest[1][2]= 0;
		blocTest[1][3]= 0;
		blocTest[1][4]= 0;
		blocTest[1][5]= 0;
		blocTest[1][6]= 0;
		blocTest[1][7]= 0;
		
		blocTest[2][0]= 0;
		blocTest[2][1]= 0;
		blocTest[2][2]= 0;
		blocTest[2][3]= 0;
		blocTest[2][4]= 0;
		blocTest[2][5]= 0;
		blocTest[2][6]= 0;
		blocTest[2][7]= 0;
		
		blocTest[3][0]= 0;
		blocTest[3][1]= 0;
		blocTest[3][2]= 0;
		blocTest[3][3]= 0;
		blocTest[3][4]= 0;
		blocTest[3][5]= 0;
		blocTest[3][6]= 0;
		blocTest[3][7]= 0;
		
		blocTest[4][0]= 0;
		blocTest[4][1]= 0;
		blocTest[4][2]= 0;
		blocTest[4][3]= 0;
		blocTest[4][4]= 0;
		blocTest[4][5]= 0;
		blocTest[4][6]= 0;
		blocTest[4][7]= 0;
		
		blocTest[5][0]= 0;
		blocTest[5][1]= 0;
		blocTest[5][2]= 0;
		blocTest[5][3]= 0;
		blocTest[5][4]= 0;
		blocTest[5][5]= 0;
		blocTest[5][6]= 0;
		blocTest[5][7]= 0;
		
		blocTest[6][0]= 0;
		blocTest[6][1]= 0;
		blocTest[6][2]= 0;
		blocTest[6][3]= 0;
		blocTest[6][4]= 0;
		blocTest[6][5]= 0;
		blocTest[6][6]= 0;
		blocTest[6][7]= 0;
		
		blocTest[7][0]= 0;
		blocTest[7][1]= 0;
		blocTest[7][2]= 0;
		blocTest[7][3]= 0;
		blocTest[7][4]= 0;
		blocTest[7][5]= 0;
		blocTest[7][6]= 0;
		blocTest[7][7]= 0;
		
		listeTesti.add(blocTest);
		listeTest.add(listeTesti);
		listeTestRecu =ZigZag.process(listeTest);
		
	    int i = listeTestRecu.get(0).get(0)[0];
	    int j = listeTestRecu.get(0).get(0)[1];
	    
	    assertTrue(i==blocTest[0][0] && j==blocTest[1][0]);
	}
	
	public void testZigzag(){
		
		blocTest[0][0]= 33;
		blocTest[0][1]= 0;
		blocTest[0][2]= 0;
		blocTest[0][3]= 0;
		blocTest[0][4]= 0;
		blocTest[0][5]= 0;
		blocTest[0][6]= 0;		
		blocTest[0][7]= 0;
		
		blocTest[1][0]= 2;
		blocTest[1][1]= 0;
		blocTest[1][2]= 0;
		blocTest[1][3]= 0;
		blocTest[1][4]= 0;
		blocTest[1][5]= 0;
		blocTest[1][6]= 0;
		blocTest[1][7]= 0;
		
		blocTest[2][0]= 0;
		blocTest[2][1]= 0;
		blocTest[2][2]= 0;
		blocTest[2][3]= 0;
		blocTest[2][4]= 0;
		blocTest[2][5]= 0;
		blocTest[2][6]= 0;
		blocTest[2][7]= 0;
		
		blocTest[3][0]= 0;
		blocTest[3][1]= 0;
		blocTest[3][2]= 0;
		blocTest[3][3]= 0;
		blocTest[3][4]= 0;
		blocTest[3][5]= 0;
		blocTest[3][6]= 0;
		blocTest[3][7]= 0;
		
		blocTest[4][0]= 0;
		blocTest[4][1]= 0;
		blocTest[4][2]= 0;
		blocTest[4][3]= 0;
		blocTest[4][4]= 0;
		blocTest[4][5]= 0;
		blocTest[4][6]= 0;
		blocTest[4][7]= 0;
		
		blocTest[5][0]= 0;
		blocTest[5][1]= 0;
		blocTest[5][2]= 0;
		blocTest[5][3]= 0;
		blocTest[5][4]= 0;
		blocTest[5][5]= 0;
		blocTest[5][6]= 0;
		blocTest[5][7]= 0;
		
		blocTest[6][0]= 0;
		blocTest[6][1]= 0;
		blocTest[6][2]= 0;
		blocTest[6][3]= 0;
		blocTest[6][4]= 0;
		blocTest[6][5]= 0;
		blocTest[6][6]= 0;
		blocTest[6][7]= 0;
		
		blocTest[7][0]= 0;
		blocTest[7][1]= 0;
		blocTest[7][2]= 0;
		blocTest[7][3]= 0;
		blocTest[7][4]= 0;
		blocTest[7][5]= 0;
		blocTest[7][6]= 0;
		blocTest[7][7]= 0;
		
		blocRecu = ZigZag.zigzag(blocTest);
		assertTrue(blocRecu[0]==blocTest[0][0] && blocRecu[1]==blocTest[1][0]);
	}

}
