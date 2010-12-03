package gti310.tp4;
import java.util.ArrayList;
import junit.framework.TestCase;

public class TestDCT extends TestCase {

	
	private  int[][]  blocTest = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	private  int[][]  blocTestRecu = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	private  int[][]  blocTestAttendu = new int[Main.BLOCK_SIZE][Main.BLOCK_SIZE];
	private ArrayList<ArrayList<int[][]>> listeTestBloc8 = new ArrayList<ArrayList<int[][]>>();
	private ArrayList<int[][]> listeYUV = new ArrayList<int[][]>();

	public void testProcess(){

		//Donnees des notes de cours 
		blocTest[0][0]= 200;
		blocTest[0][1]= 200;
		blocTest[0][2]= 203;
		blocTest[0][3]= 200;
		blocTest[0][4]= 200;
		blocTest[0][5]= 200;
		blocTest[0][6]= 205;
		blocTest[0][7]= 210;

		blocTest[1][0]= 202;
		blocTest[1][1]= 203;
		blocTest[1][2]= 200;
		blocTest[1][3]= 200;
		blocTest[1][4]= 205;
		blocTest[1][5]= 200;
		blocTest[1][6]= 200;
		blocTest[1][7]= 200;

		blocTest[2][0]= 189;
		blocTest[2][1]= 198;
		blocTest[2][2]= 200;
		blocTest[2][3]= 200;
		blocTest[2][4]= 200;
		blocTest[2][5]= 200;
		blocTest[2][6]= 199;
		blocTest[2][7]= 200;

		blocTest[3][0]= 188;
		blocTest[3][1]= 188;
		blocTest[3][2]= 195;
		blocTest[3][3]= 200;
		blocTest[3][4]= 200;
		blocTest[3][5]= 200;
		blocTest[3][6]= 200;
		blocTest[3][7]= 200;

		blocTest[4][0]= 189;
		blocTest[4][1]= 189;
		blocTest[4][2]= 200;
		blocTest[4][3]= 197;
		blocTest[4][4]= 195;
		blocTest[4][5]= 200;
		blocTest[4][6]= 191;
		blocTest[4][7]= 188;

		blocTest[5][0]= 175;
		blocTest[5][1]= 182;
		blocTest[5][2]= 187;
		blocTest[5][3]= 187;
		blocTest[5][4]= 188;
		blocTest[5][5]= 190;
		blocTest[5][6]= 187;
		blocTest[5][7]= 185;

		blocTest[6][0]= 175;
		blocTest[6][1]= 178;
		blocTest[6][2]= 185;
		blocTest[6][3]= 187;
		blocTest[6][4]= 187;
		blocTest[6][5]= 187;
		blocTest[6][6]= 187;
		blocTest[6][7]= 187;

		blocTest[7][0]= 175;
		blocTest[7][1]= 175;
		blocTest[7][2]= 175;
		blocTest[7][3]= 187;
		blocTest[7][4]= 175;
		blocTest[7][5]= 175;
		blocTest[7][6]= 175;
		blocTest[7][7]= 186;

		blocTestAttendu[0][0]= 1539;
		blocTestAttendu[0][1]= -16;
		blocTestAttendu[0][2]= -12;
		blocTestAttendu[0][3]= -8;
		blocTestAttendu[0][4]= 0;
		blocTestAttendu[0][5]= 0;
		blocTestAttendu[0][6]= 3;
		blocTestAttendu[0][7]= -2;

		blocTestAttendu[1][0]= 65;
		blocTestAttendu[1][1]= 3;
		blocTestAttendu[1][2]= 6;
		blocTestAttendu[1][3]= 3;
		blocTestAttendu[1][4]= -2;
		blocTestAttendu[1][5]= -3;
		blocTestAttendu[1][6]= -2;
		blocTestAttendu[1][7]= 5;

		blocTestAttendu[2][0]= -12;
		blocTestAttendu[2][1]= 2;
		blocTestAttendu[2][2]= 11;
		blocTestAttendu[2][3]= -4;
		blocTestAttendu[2][4]= 7;
		blocTestAttendu[2][5]= -1;
		blocTestAttendu[2][6]= -3;
		blocTestAttendu[2][7]= -2;

		blocTestAttendu[3][0]= 4;
		blocTestAttendu[3][1]= 0;
		blocTestAttendu[3][2]= -1;
		blocTestAttendu[3][3]= 2;
		blocTestAttendu[3][4]= -5;
		blocTestAttendu[3][5]= 0;
		blocTestAttendu[3][6]= 3;
		blocTestAttendu[3][7]= 4;

		blocTestAttendu[4][0]= 1;
		blocTestAttendu[4][1]= 0;
		blocTestAttendu[4][2]= 3;
		blocTestAttendu[4][3]= -2;
		blocTestAttendu[4][4]= 4;
		blocTestAttendu[4][5]= 4;
		blocTestAttendu[4][6]= 3;
		blocTestAttendu[4][7]= -2;

		blocTestAttendu[5][0]= 2;
		blocTestAttendu[5][1]= -11;
		blocTestAttendu[5][2]= 0;
		blocTestAttendu[5][3]= -3;
		blocTestAttendu[5][4]= 0;
		blocTestAttendu[5][5]= 1;
		blocTestAttendu[5][6]= -1;
		blocTestAttendu[5][7]= 2;

		blocTestAttendu[6][0]= -8;
		blocTestAttendu[6][1]= -2;
		blocTestAttendu[6][2]= 1;
		blocTestAttendu[6][3]= -5;
		blocTestAttendu[6][4]= -1;
		blocTestAttendu[6][5]= -1;
		blocTestAttendu[6][6]= -1;
		blocTestAttendu[6][7]= -3;

		blocTestAttendu[7][0]= 5;
		blocTestAttendu[7][1]= 3;
		blocTestAttendu[7][2]= -2;
		blocTestAttendu[7][3]= -2;
		blocTestAttendu[7][4]= -4;
		blocTestAttendu[7][5]= 0;
		blocTestAttendu[7][6]= 3;
		blocTestAttendu[7][7]= 0;		

		listeYUV.add(blocTest);
		listeTestBloc8.add(listeYUV);	


		blocTestRecu = DCT.process(listeTestBloc8).get(0).get(0);

		boolean valid = true ;
		for (int i=0; i<Main.BLOCK_SIZE ;i++){			
			for(int j=0 ; j<Main.BLOCK_SIZE;j++){
				if(blocTestRecu[i][j]!=blocTestAttendu[i][j])
					valid = false;				
			}
		}
		assertTrue(valid);
	}

	public void testC(){

		assertTrue(DCT.C(32)==1 && DCT.C(0)==1 / Math.sqrt(2));
	}

	public void testDCT(){
		//Donnees des notes de cours 

		blocTest[0][0]= 200;
		blocTest[0][1]= 200;
		blocTest[0][2]= 203;
		blocTest[0][3]= 200;
		blocTest[0][4]= 200;
		blocTest[0][5]= 200;
		blocTest[0][6]= 205;
		blocTest[0][7]= 210;

		blocTest[1][0]= 202;
		blocTest[1][1]= 203;
		blocTest[1][2]= 200;
		blocTest[1][3]= 200;
		blocTest[1][4]= 205;
		blocTest[1][5]= 200;
		blocTest[1][6]= 200;
		blocTest[1][7]= 200;

		blocTest[2][0]= 189;
		blocTest[2][1]= 198;
		blocTest[2][2]= 200;
		blocTest[2][3]= 200;
		blocTest[2][4]= 200;
		blocTest[2][5]= 200;
		blocTest[2][6]= 199;
		blocTest[2][7]= 200;

		blocTest[3][0]= 188;
		blocTest[3][1]= 188;
		blocTest[3][2]= 195;
		blocTest[3][3]= 200;
		blocTest[3][4]= 200;
		blocTest[3][5]= 200;
		blocTest[3][6]= 200;
		blocTest[3][7]= 200;

		blocTest[4][0]= 189;
		blocTest[4][1]= 189;
		blocTest[4][2]= 200;
		blocTest[4][3]= 197;
		blocTest[4][4]= 195;
		blocTest[4][5]= 200;
		blocTest[4][6]= 191;
		blocTest[4][7]= 188;

		blocTest[5][0]= 175;
		blocTest[5][1]= 182;
		blocTest[5][2]= 187;
		blocTest[5][3]= 187;
		blocTest[5][4]= 188;
		blocTest[5][5]= 190;
		blocTest[5][6]= 187;
		blocTest[5][7]= 185;

		blocTest[6][0]= 175;
		blocTest[6][1]= 178;
		blocTest[6][2]= 185;
		blocTest[6][3]= 187;
		blocTest[6][4]= 187;
		blocTest[6][5]= 187;
		blocTest[6][6]= 187;
		blocTest[6][7]= 187;

		blocTest[7][0]= 175;
		blocTest[7][1]= 175;
		blocTest[7][2]= 175;
		blocTest[7][3]= 187;
		blocTest[7][4]= 175;
		blocTest[7][5]= 175;
		blocTest[7][6]= 175;
		blocTest[7][7]= 186;

		blocTestAttendu[0][0]= 1539;
		blocTestAttendu[0][1]= -16;
		blocTestAttendu[0][2]= -12;
		blocTestAttendu[0][3]= -8;
		blocTestAttendu[0][4]= 0;
		blocTestAttendu[0][5]= 0;
		blocTestAttendu[0][6]= 3;
		blocTestAttendu[0][7]= -2;

		blocTestAttendu[1][0]= 65;
		blocTestAttendu[1][1]= 3;
		blocTestAttendu[1][2]= 6;
		blocTestAttendu[1][3]= 3;
		blocTestAttendu[1][4]= -2;
		blocTestAttendu[1][5]= -3;
		blocTestAttendu[1][6]= -2;
		blocTestAttendu[1][7]= 5;

		blocTestAttendu[2][0]= -12;
		blocTestAttendu[2][1]= 2;
		blocTestAttendu[2][2]= 11;
		blocTestAttendu[2][3]= -4;
		blocTestAttendu[2][4]= 7;
		blocTestAttendu[2][5]= -1;
		blocTestAttendu[2][6]= -3;
		blocTestAttendu[2][7]= -2;

		blocTestAttendu[3][0]= 4;
		blocTestAttendu[3][1]= 0;
		blocTestAttendu[3][2]= -1;
		blocTestAttendu[3][3]= 2;
		blocTestAttendu[3][4]= -5;
		blocTestAttendu[3][5]= 0;
		blocTestAttendu[3][6]= 3;
		blocTestAttendu[3][7]= 4;

		blocTestAttendu[4][0]= 1;
		blocTestAttendu[4][1]= 0;
		blocTestAttendu[4][2]= 3;
		blocTestAttendu[4][3]= -2;
		blocTestAttendu[4][4]= 4;
		blocTestAttendu[4][5]= 4;
		blocTestAttendu[4][6]= 3;
		blocTestAttendu[4][7]= -2;

		blocTestAttendu[5][0]= 2;
		blocTestAttendu[5][1]= -11;
		blocTestAttendu[5][2]= 0;
		blocTestAttendu[5][3]= -3;
		blocTestAttendu[5][4]= 0;
		blocTestAttendu[5][5]= 1;
		blocTestAttendu[5][6]= -1;
		blocTestAttendu[5][7]= 2;

		blocTestAttendu[6][0]= -8;
		blocTestAttendu[6][1]= -2;
		blocTestAttendu[6][2]= 1;
		blocTestAttendu[6][3]= -5;
		blocTestAttendu[6][4]= -1;
		blocTestAttendu[6][5]= -1;
		blocTestAttendu[6][6]= -1;
		blocTestAttendu[6][7]= -3;

		blocTestAttendu[7][0]= 5;
		blocTestAttendu[7][1]= 3;
		blocTestAttendu[7][2]= -2;
		blocTestAttendu[7][3]= -2;
		blocTestAttendu[7][4]= -4;
		blocTestAttendu[7][5]= 0;
		blocTestAttendu[7][6]= 3;
		blocTestAttendu[7][7]= 0;

		blocTestRecu =DCT.dct(blocTest);


		boolean valid = true ;
		for (int i=0; i<Main.BLOCK_SIZE ;i++){			
			for(int j=0 ; j<Main.BLOCK_SIZE;j++){
				if(blocTestRecu[i][j]!=blocTestAttendu[i][j])
					valid = false;				
			}
		}
		assertTrue(valid);

	}


}
