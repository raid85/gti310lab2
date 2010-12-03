package gti310.tp4;

import java.util.ArrayList;

import junit.framework.TestCase;

public class TestQuantification extends TestCase {
	
	private int[][] blocTestAttendu= new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	private int[][] blocTest= new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	private ArrayList<ArrayList<int[][]>> listeTest = new ArrayList<ArrayList<int[][]>>();
	private ArrayList<int[][]> listeTesti = new ArrayList<int[][]>();
	
	
	public void testprocess(){
		
		blocTestAttendu[0][0]= 33;
		blocTestAttendu[0][1]= 0;
		blocTestAttendu[0][2]= 0;
		blocTestAttendu[0][3]= 0;
		blocTestAttendu[0][4]= 0;
		blocTestAttendu[0][5]= 0;
		blocTestAttendu[0][6]= 0;		
		blocTestAttendu[0][7]= 0;
		
		blocTestAttendu[1][0]= 2;
		blocTestAttendu[1][1]= 0;
		blocTestAttendu[1][2]= 0;
		blocTestAttendu[1][3]= 0;
		blocTestAttendu[1][4]= 0;
		blocTestAttendu[1][5]= 0;
		blocTestAttendu[1][6]= 0;
		blocTestAttendu[1][7]= 0;
		
		blocTestAttendu[2][0]= 0;
		blocTestAttendu[2][1]= 0;
		blocTestAttendu[2][2]= 0;
		blocTestAttendu[2][3]= 0;
		blocTestAttendu[2][4]= 0;
		blocTestAttendu[2][5]= 0;
		blocTestAttendu[2][6]= 0;
		blocTestAttendu[2][7]= 0;
		
		blocTestAttendu[3][0]= 0;
		blocTestAttendu[3][1]= 0;
		blocTestAttendu[3][2]= 0;
		blocTestAttendu[3][3]= 0;
		blocTestAttendu[3][4]= 0;
		blocTestAttendu[3][5]= 0;
		blocTestAttendu[3][6]= 0;
		blocTestAttendu[3][7]= 0;
		
		blocTestAttendu[4][0]= 0;
		blocTestAttendu[4][1]= 0;
		blocTestAttendu[4][2]= 0;
		blocTestAttendu[4][3]= 0;
		blocTestAttendu[4][4]= 0;
		blocTestAttendu[4][5]= 0;
		blocTestAttendu[4][6]= 0;
		blocTestAttendu[4][7]= 0;
		
		blocTestAttendu[5][0]= 0;
		blocTestAttendu[5][1]= 0;
		blocTestAttendu[5][2]= 0;
		blocTestAttendu[5][3]= 0;
		blocTestAttendu[5][4]= 0;
		blocTestAttendu[5][5]= 0;
		blocTestAttendu[5][6]= 0;
		blocTestAttendu[5][7]= 0;
		
		blocTestAttendu[6][0]= 0;
		blocTestAttendu[6][1]= 0;
		blocTestAttendu[6][2]= 0;
		blocTestAttendu[6][3]= 0;
		blocTestAttendu[6][4]= 0;
		blocTestAttendu[6][5]= 0;
		blocTestAttendu[6][6]= 0;
		blocTestAttendu[6][7]= 0;
		
		blocTestAttendu[7][0]= 0;
		blocTestAttendu[7][1]= 0;
		blocTestAttendu[7][2]= 0;
		blocTestAttendu[7][3]= 0;
		blocTestAttendu[7][4]= 0;
		blocTestAttendu[7][5]= 0;
		blocTestAttendu[7][6]= 0;
		blocTestAttendu[7][7]= 0;
		
		blocTest[0][0]= 1539;
		blocTest[0][1]= -16;
		blocTest[0][2]= -12;
		blocTest[0][3]= -8;
		blocTest[0][4]= 0;
		blocTest[0][5]= 0;
		blocTest[0][6]= 3;
		blocTest[0][7]= -2;
		
		blocTest[1][0]= 65;
		blocTest[1][1]= 3;
		blocTest[1][2]= 6;
		blocTest[1][3]= 3;
		blocTest[1][4]= -2;
		blocTest[1][5]= -3;
		blocTest[1][6]= -2;
		blocTest[1][7]= 5;
		
		blocTest[2][0]= -12;
		blocTest[2][1]= 2;
		blocTest[2][2]= 11;
		blocTest[2][3]= -4;
		blocTest[2][4]= 7;
		blocTest[2][5]= -1;
		blocTest[2][6]= -3;
		blocTest[2][7]= -2;
		
		blocTest[3][0]= 4;
		blocTest[3][1]= 0;
		blocTest[3][2]= -1;
		blocTest[3][3]= 2;
		blocTest[3][4]= -5;
		blocTest[3][5]= 0;
		blocTest[3][6]= 3;
		blocTest[3][7]= 4;
		
		blocTest[4][0]= 1;
		blocTest[4][1]= 0;
		blocTest[4][2]= 3;
		blocTest[4][3]= -2;
		blocTest[4][4]= 4;
		blocTest[4][5]= 4;
		blocTest[4][6]= 3;
		blocTest[4][7]= -2;
		
		blocTest[5][0]= 2;
		blocTest[5][1]= -11;
		blocTest[5][2]= 0;
		blocTest[5][3]= -3;
		blocTest[5][4]= 0;
		blocTest[5][5]= 1;
		blocTest[5][6]= -1;
		blocTest[5][7]= 2;
		
		blocTest[6][0]= -8;
		blocTest[6][1]= -2;
		blocTest[6][2]= 1;
		blocTest[6][3]= -5;
		blocTest[6][4]= -1;
		blocTest[6][5]= -1;
		blocTest[6][6]= -1;
		blocTest[6][7]= -3;
		
		blocTest[7][0]= 5;
		blocTest[7][1]= 3;
		blocTest[7][2]= -2;
		blocTest[7][3]= -2;
		blocTest[7][4]= -4;
		blocTest[7][5]= 0;
		blocTest[7][6]= 3;
		blocTest[7][7]= 0;
		
		listeTesti.add(0, blocTest);
		listeTest.add(0, listeTesti);		
		
		int[][] blocr = Quantification.process(listeTest,50).get(0).get(0);
		
		boolean valid = true ;
		for (int i=0; i<Main.BLOCK_SIZE ;i++){			
			for(int j=0 ; j<Main.BLOCK_SIZE;j++){
				if(blocr[i][j]!=blocTestAttendu[i][j])
					valid = false;				
			}
		}
		assertTrue(valid);
		//System.out.println("Bloc "+blocr[0][0]);
		//System.out.println(listeTest.get(0).get(0)[0][0]);
		//assertTrue(Quantification.process(listeTest, 50).get(0).get(0)[0][0]== listeTestAttendu.get(0).get(0)[0][0]);
	}
	
	
	public void testquantificaion(){
		
		//avec les tables de l'enoncé et facteur de qualité 50 de sorte que
		//alpha =1
		blocTestAttendu[0][0]= 33;
		blocTestAttendu[0][1]= 0;
		blocTestAttendu[0][2]= 0;
		blocTestAttendu[0][3]= 0;
		blocTestAttendu[0][4]= 0;
		blocTestAttendu[0][5]= 0;
		blocTestAttendu[0][6]= 0;		
		blocTestAttendu[0][7]= 0;
		
		blocTestAttendu[1][0]= 2;
		blocTestAttendu[1][1]= 0;
		blocTestAttendu[1][2]= 0;
		blocTestAttendu[1][3]= 0;
		blocTestAttendu[1][4]= 0;
		blocTestAttendu[1][5]= 0;
		blocTestAttendu[1][6]= 0;
		blocTestAttendu[1][7]= 0;
		
		blocTestAttendu[2][0]= 0;
		blocTestAttendu[2][1]= 0;
		blocTestAttendu[2][2]= 0;
		blocTestAttendu[2][3]= 0;
		blocTestAttendu[2][4]= 0;
		blocTestAttendu[2][5]= 0;
		blocTestAttendu[2][6]= 0;
		blocTestAttendu[2][7]= 0;
		
		blocTestAttendu[3][0]= 0;
		blocTestAttendu[3][1]= 0;
		blocTestAttendu[3][2]= 0;
		blocTestAttendu[3][3]= 0;
		blocTestAttendu[3][4]= 0;
		blocTestAttendu[3][5]= 0;
		blocTestAttendu[3][6]= 0;
		blocTestAttendu[3][7]= 0;
		
		blocTestAttendu[4][0]= 0;
		blocTestAttendu[4][1]= 0;
		blocTestAttendu[4][2]= 0;
		blocTestAttendu[4][3]= 0;
		blocTestAttendu[4][4]= 0;
		blocTestAttendu[4][5]= 0;
		blocTestAttendu[4][6]= 0;
		blocTestAttendu[4][7]= 0;
		
		blocTestAttendu[5][0]= 0;
		blocTestAttendu[5][1]= 0;
		blocTestAttendu[5][2]= 0;
		blocTestAttendu[5][3]= 0;
		blocTestAttendu[5][4]= 0;
		blocTestAttendu[5][5]= 0;
		blocTestAttendu[5][6]= 0;
		blocTestAttendu[5][7]= 0;
		
		blocTestAttendu[6][0]= 0;
		blocTestAttendu[6][1]= 0;
		blocTestAttendu[6][2]= 0;
		blocTestAttendu[6][3]= 0;
		blocTestAttendu[6][4]= 0;
		blocTestAttendu[6][5]= 0;
		blocTestAttendu[6][6]= 0;
		blocTestAttendu[6][7]= 0;
		
		blocTestAttendu[7][0]= 0;
		blocTestAttendu[7][1]= 0;
		blocTestAttendu[7][2]= 0;
		blocTestAttendu[7][3]= 0;
		blocTestAttendu[7][4]= 0;
		blocTestAttendu[7][5]= 0;
		blocTestAttendu[7][6]= 0;
		blocTestAttendu[7][7]= 0;
		
		blocTest[0][0]= 1539;
		blocTest[0][1]= -16;
		blocTest[0][2]= -12;
		blocTest[0][3]= -8;
		blocTest[0][4]= 0;
		blocTest[0][5]= 0;
		blocTest[0][6]= 3;
		blocTest[0][7]= -2;
		
		blocTest[1][0]= 65;
		blocTest[1][1]= 3;
		blocTest[1][2]= 6;
		blocTest[1][3]= 3;
		blocTest[1][4]= -2;
		blocTest[1][5]= -3;
		blocTest[1][6]= -2;
		blocTest[1][7]= 5;
		
		blocTest[2][0]= -12;
		blocTest[2][1]= 2;
		blocTest[2][2]= 11;
		blocTest[2][3]= -4;
		blocTest[2][4]= 7;
		blocTest[2][5]= -1;
		blocTest[2][6]= -3;
		blocTest[2][7]= -2;
		
		blocTest[3][0]= 4;
		blocTest[3][1]= 0;
		blocTest[3][2]= -1;
		blocTest[3][3]= 2;
		blocTest[3][4]= -5;
		blocTest[3][5]= 0;
		blocTest[3][6]= 3;
		blocTest[3][7]= 4;
		
		blocTest[4][0]= 1;
		blocTest[4][1]= 0;
		blocTest[4][2]= 3;
		blocTest[4][3]= -2;
		blocTest[4][4]= 4;
		blocTest[4][5]= 4;
		blocTest[4][6]= 3;
		blocTest[4][7]= -2;
		
		blocTest[5][0]= 2;
		blocTest[5][1]= -11;
		blocTest[5][2]= 0;
		blocTest[5][3]= -3;
		blocTest[5][4]= 0;
		blocTest[5][5]= 1;
		blocTest[5][6]= -1;
		blocTest[5][7]= 2;
		
		blocTest[6][0]= -8;
		blocTest[6][1]= -2;
		blocTest[6][2]= 1;
		blocTest[6][3]= -5;
		blocTest[6][4]= -1;
		blocTest[6][5]= -1;
		blocTest[6][6]= -1;
		blocTest[6][7]= -3;
		
		blocTest[7][0]= 5;
		blocTest[7][1]= 3;
		blocTest[7][2]= -2;
		blocTest[7][3]= -2;
		blocTest[7][4]= -4;
		blocTest[7][5]= 0;
		blocTest[7][6]= 3;
		blocTest[7][7]= 0;
		
		Quantification.factQ=50;
		int [][] tabRetour = Quantification.quantification(blocTest, 0);
		
		boolean valid = true ;
		for (int i=0; i<Main.BLOCK_SIZE ;i++){			
			for(int j=0 ; j<Main.BLOCK_SIZE;j++){
				if(tabRetour[i][j]!=blocTestAttendu[i][j])
					valid = false;				
			}
		}
		assertTrue(valid);
		
	}

}
