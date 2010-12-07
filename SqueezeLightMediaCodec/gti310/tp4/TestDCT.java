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
		blocTest[1][0]= 200;
		blocTest[2][0]= 203;
		blocTest[3][0]= 200;
		blocTest[4][0]= 200;
		blocTest[5][0]= 200;
		blocTest[6][0]= 205;
		blocTest[7][0]= 210;

		blocTest[0][1]= 202;
		blocTest[1][1]= 203;
		blocTest[2][1]= 200;
		blocTest[3][1]= 200;
		blocTest[4][1]= 205;
		blocTest[5][1]= 200;
		blocTest[6][1]= 200;
		blocTest[7][1]= 200;

		blocTest[0][2]= 189;
		blocTest[1][2]= 198;
		blocTest[2][2]= 200;
		blocTest[3][2]= 200;
		blocTest[4][2]= 200;
		blocTest[5][2]= 200;
		blocTest[6][2]= 199;
		blocTest[7][2]= 200;

		blocTest[0][3]= 188;
		blocTest[1][3]= 188;
		blocTest[2][3]= 195;
		blocTest[3][3]= 200;
		blocTest[4][3]= 200;
		blocTest[5][3]= 200;
		blocTest[6][3]= 200;
		blocTest[7][3]= 200;

		blocTest[0][4]= 189;
		blocTest[1][4]= 189;
		blocTest[2][4]= 200;
		blocTest[3][4]= 197;
		blocTest[4][4]= 195;
		blocTest[5][4]= 200;
		blocTest[6][4]= 191;
		blocTest[7][4]= 188;

		blocTest[0][5]= 175;
		blocTest[1][5]= 182;
		blocTest[2][5]= 187;
		blocTest[3][5]= 187;
		blocTest[4][5]= 188;
		blocTest[5][5]= 190;
		blocTest[6][5]= 187;
		blocTest[7][5]= 185;

		blocTest[0][6]= 175;
		blocTest[1][6]= 178;
		blocTest[2][6]= 185;
		blocTest[3][6]= 187;
		blocTest[4][6]= 187;
		blocTest[5][6]= 187;
		blocTest[6][6]= 187;
		blocTest[7][6]= 187;

		blocTest[0][7]= 175;
		blocTest[1][7]= 175;
		blocTest[2][7]= 175;
		blocTest[3][7]= 187;
		blocTest[4][7]= 175;
		blocTest[5][7]= 175;
		blocTest[6][7]= 175;
		blocTest[7][7]= 186;

		blocTestAttendu[0][0]= 1539;
		blocTestAttendu[1][0]= -16;
		blocTestAttendu[2][0]= -12;
		blocTestAttendu[3][0]= -8;
		blocTestAttendu[4][0]= 0;
		blocTestAttendu[5][0]= 0;
		blocTestAttendu[6][0]= 3;
		blocTestAttendu[7][0]= -2;

		blocTestAttendu[0][1]= 65;
		blocTestAttendu[1][1]= 3;
		blocTestAttendu[2][1]= 6;
		blocTestAttendu[3][1]= 3;
		blocTestAttendu[4][1]= -2;
		blocTestAttendu[5][1]= -3;
		blocTestAttendu[6][1]= -2;
		blocTestAttendu[7][1]= 5;

		blocTestAttendu[0][2]= -12;
		blocTestAttendu[1][2]= 2;
		blocTestAttendu[2][2]= 11;
		blocTestAttendu[3][2]= -4;
		blocTestAttendu[4][2]= 7;
		blocTestAttendu[5][2]= -1;
		blocTestAttendu[6][2]= -3;
		blocTestAttendu[7][2]= -2;

		blocTestAttendu[0][3]= 4;
		blocTestAttendu[1][3]= 0;
		blocTestAttendu[2][3]= -1;
		blocTestAttendu[3][3]= 2;
		blocTestAttendu[4][3]= -5;
		blocTestAttendu[5][3]= 0;
		blocTestAttendu[6][3]= 3;
		blocTestAttendu[7][3]= 4;

		blocTestAttendu[0][4]= 1;
		blocTestAttendu[1][4]= 0;
		blocTestAttendu[2][4]= 3;
		blocTestAttendu[3][4]= -2;
		blocTestAttendu[4][4]= 4;
		blocTestAttendu[5][4]= 4;
		blocTestAttendu[6][4]= 3;
		blocTestAttendu[7][4]= -2;

		blocTestAttendu[0][5]= 2;
		blocTestAttendu[1][5]= -11;
		blocTestAttendu[2][5]= 0;
		blocTestAttendu[3][5]= -3;
		blocTestAttendu[4][5]= 0;
		blocTestAttendu[5][5]= 1;
		blocTestAttendu[6][5]= -1;
		blocTestAttendu[7][5]= 2;

		blocTestAttendu[0][6]= -8;
		blocTestAttendu[1][6]= -2;
		blocTestAttendu[2][6]= 1;
		blocTestAttendu[3][6]= -5;
		blocTestAttendu[4][6]= -1;
		blocTestAttendu[5][6]= -1;
		blocTestAttendu[6][6]= -1;
		blocTestAttendu[7][6]= -3;

		blocTestAttendu[0][7]= 5;
		blocTestAttendu[1][7]= 3;
		blocTestAttendu[2][7]= -2;
		blocTestAttendu[3][7]= -2;
		blocTestAttendu[4][7]= -4;
		blocTestAttendu[5][7]= 0;
		blocTestAttendu[6][7]= 3;
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
		
		blocTest[0][0]= 200;
		blocTest[1][0]= 200;
		blocTest[2][0]= 203;
		blocTest[3][0]= 200;
		blocTest[4][0]= 200;
		blocTest[5][0]= 200;
		blocTest[6][0]= 205;
		blocTest[7][0]= 210;
	
		blocTest[0][1]= 202;
		blocTest[1][1]= 203;
		blocTest[2][1]= 200;
		blocTest[3][1]= 200;
		blocTest[4][1]= 205;
		blocTest[5][1]= 200;
		blocTest[6][1]= 200;
		blocTest[7][1]= 200;
	
		blocTest[0][2]= 189;
		blocTest[1][2]= 198;
		blocTest[2][2]= 200;
		blocTest[3][2]= 200;
		blocTest[4][2]= 200;
		blocTest[5][2]= 200;
		blocTest[6][2]= 199;
		blocTest[7][2]= 200;
	
		blocTest[0][3]= 188;
		blocTest[1][3]= 188;
		blocTest[2][3]= 195;
		blocTest[3][3]= 200;
		blocTest[4][3]= 200;
		blocTest[5][3]= 200;
		blocTest[6][3]= 200;
		blocTest[7][3]= 200;
	
		blocTest[0][4]= 189;
		blocTest[1][4]= 189;
		blocTest[2][4]= 200;
		blocTest[3][4]= 197;
		blocTest[4][4]= 195;
		blocTest[5][4]= 200;
		blocTest[6][4]= 191;
		blocTest[7][4]= 188;
	
		blocTest[0][5]= 175;
		blocTest[1][5]= 182;
		blocTest[2][5]= 187;
		blocTest[3][5]= 187;
		blocTest[4][5]= 188;
		blocTest[5][5]= 190;
		blocTest[6][5]= 187;
		blocTest[7][5]= 185;
	
		blocTest[0][6]= 175;
		blocTest[1][6]= 178;
		blocTest[2][6]= 185;
		blocTest[3][6]= 187;
		blocTest[4][6]= 187;
		blocTest[5][6]= 187;
		blocTest[6][6]= 187;
		blocTest[7][6]= 187;
	
		blocTest[0][7]= 175;
		blocTest[1][7]= 175;
		blocTest[2][7]= 175;
		blocTest[3][7]= 187;
		blocTest[4][7]= 175;
		blocTest[5][7]= 175;
		blocTest[6][7]= 175;
		blocTest[7][7]= 186;
	
		blocTestAttendu[0][0]= 1539;
		blocTestAttendu[1][0]= -16;
		blocTestAttendu[2][0]= -12;
		blocTestAttendu[3][0]= -8;
		blocTestAttendu[4][0]= 0;
		blocTestAttendu[5][0]= 0;
		blocTestAttendu[6][0]= 3;
		blocTestAttendu[7][0]= -2;
	
		blocTestAttendu[0][1]= 65;
		blocTestAttendu[1][1]= 3;
		blocTestAttendu[2][1]= 6;
		blocTestAttendu[3][1]= 3;
		blocTestAttendu[4][1]= -2;
		blocTestAttendu[5][1]= -3;
		blocTestAttendu[6][1]= -2;
		blocTestAttendu[7][1]= 5;
	
		blocTestAttendu[0][2]= -12;
		blocTestAttendu[1][2]= 2;
		blocTestAttendu[2][2]= 11;
		blocTestAttendu[3][2]= -4;
		blocTestAttendu[4][2]= 7;
		blocTestAttendu[5][2]= -1;
		blocTestAttendu[6][2]= -3;
		blocTestAttendu[7][2]= -2;
	
		blocTestAttendu[0][3]= 4;
		blocTestAttendu[1][3]= 0;
		blocTestAttendu[2][3]= -1;
		blocTestAttendu[3][3]= 2;
		blocTestAttendu[4][3]= -5;
		blocTestAttendu[5][3]= 0;
		blocTestAttendu[6][3]= 3;
		blocTestAttendu[7][3]= 4;
	
		blocTestAttendu[0][4]= 1;
		blocTestAttendu[1][4]= 0;
		blocTestAttendu[2][4]= 3;
		blocTestAttendu[3][4]= -2;
		blocTestAttendu[4][4]= 4;
		blocTestAttendu[5][4]= 4;
		blocTestAttendu[6][4]= 3;
		blocTestAttendu[7][4]= -2;
	
		blocTestAttendu[0][5]= 2;
		blocTestAttendu[1][5]= -11;
		blocTestAttendu[2][5]= 0;
		blocTestAttendu[3][5]= -3;
		blocTestAttendu[4][5]= 0;
		blocTestAttendu[5][5]= 1;
		blocTestAttendu[6][5]= -1;
		blocTestAttendu[7][5]= 2;
	
		blocTestAttendu[0][6]= -8;
		blocTestAttendu[1][6]= -2;
		blocTestAttendu[2][6]= 1;
		blocTestAttendu[3][6]= -5;
		blocTestAttendu[4][6]= -1;
		blocTestAttendu[5][6]= -1;
		blocTestAttendu[6][6]= -1;
		blocTestAttendu[7][6]= -3;
	
		blocTestAttendu[0][7]= 5;
		blocTestAttendu[1][7]= 3;
		blocTestAttendu[2][7]= -2;
		blocTestAttendu[3][7]= -2;
		blocTestAttendu[4][7]= -4;
		blocTestAttendu[5][7]= 0;
		blocTestAttendu[6][7]= 3;
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

	public void testDCTinverse(){
		
		int[][]c = {
				 {200, 202, 189, 188, 189 ,175, 175, 175},
				 {200, 203, 198, 188, 189, 182, 178, 175},
				 {203, 200, 200, 195 ,201, 187, 185, 175},
				 {200, 200, 200, 200, 196, 187, 187, 187},
				 {201, 205, 199, 200, 195, 188, 187, 174},
				 {200, 201, 200, 200, 200 ,190, 187, 175},
				 {205, 200, 199, 200, 191, 187, 187, 175},
				 {210, 200 ,200, 200, 188, 186, 187, 186}};

		blocTestAttendu[0][0]= 1539;
		blocTestAttendu[1][0]= -16;
		blocTestAttendu[2][0]= -12;
		blocTestAttendu[3][0]= -8;
		blocTestAttendu[4][0]= 0;
		blocTestAttendu[5][0]= 0;
		blocTestAttendu[6][0]= 3;
		blocTestAttendu[7][0]= -2;

		blocTestAttendu[0][1]= 65;
		blocTestAttendu[1][1]= 3;
		blocTestAttendu[2][1]= 6;
		blocTestAttendu[3][1]= 3;
		blocTestAttendu[4][1]= -2;
		blocTestAttendu[5][1]= -3;
		blocTestAttendu[6][1]= -2;
		blocTestAttendu[7][1]= 5;

		blocTestAttendu[0][2]= -12;
		blocTestAttendu[1][2]= 2;
		blocTestAttendu[2][2]= 11;
		blocTestAttendu[3][2]= -4;
		blocTestAttendu[4][2]= 7;
		blocTestAttendu[5][2]= -1;
		blocTestAttendu[6][2]= -3;
		blocTestAttendu[7][2]= -2;

		blocTestAttendu[0][3]= 4;
		blocTestAttendu[1][3]= 0;
		blocTestAttendu[2][3]= -1;
		blocTestAttendu[3][3]= 2;
		blocTestAttendu[4][3]= -5;
		blocTestAttendu[5][3]= 0;
		blocTestAttendu[6][3]= 3;
		blocTestAttendu[7][3]= 4;

		blocTestAttendu[0][4]= 1;
		blocTestAttendu[1][4]= 0;
		blocTestAttendu[2][4]= 3;
		blocTestAttendu[3][4]= -2;
		blocTestAttendu[4][4]= 4;
		blocTestAttendu[5][4]= 4;
		blocTestAttendu[6][4]= 3;
		blocTestAttendu[7][4]= -2;

		blocTestAttendu[0][5]= 2;
		blocTestAttendu[1][5]= -11;
		blocTestAttendu[2][5]= 0;
		blocTestAttendu[3][5]= -3;
		blocTestAttendu[4][5]= 0;
		blocTestAttendu[5][5]= 1;
		blocTestAttendu[6][5]= -1;
		blocTestAttendu[7][5]= 2;

		blocTestAttendu[0][6]= -8;
		blocTestAttendu[1][6]= -2;
		blocTestAttendu[2][6]= 1;
		blocTestAttendu[3][6]= -5;
		blocTestAttendu[4][6]= -1;
		blocTestAttendu[5][6]= -1;
		blocTestAttendu[6][6]= -1;
		blocTestAttendu[7][6]= -3;

		blocTestAttendu[0][7]= 5;
		blocTestAttendu[1][7]= 3;
		blocTestAttendu[2][7]= -2;
		blocTestAttendu[3][7]= -2;
		blocTestAttendu[4][7]= -4;
		blocTestAttendu[5][7]= 0;
		blocTestAttendu[6][7]= 3;
		blocTestAttendu[7][7]= 0;			
		

		blocTestRecu =DCT.DCTinverse(blocTestAttendu);
		
		for (int i=0; i<Main.BLOCK_SIZE ;i++){
			System.out.println("");
			for(int j=0 ; j<Main.BLOCK_SIZE;j++){
				System.out.print(" "+blocTestRecu[i][j]);			
			}
		}

		boolean valid = true ;
		for (int i=0; i<Main.BLOCK_SIZE ;i++){			
			for(int j=0 ; j<Main.BLOCK_SIZE;j++){
				if(blocTestRecu[i][j]!=c[i][j])
					valid = false;				
			}
		}
		assertTrue(valid);

	}


}
