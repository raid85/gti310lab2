package gti310.tp4;

import java.util.ArrayList;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author Fran�ois Caron
 */
public class Main {

	/*
	 * The entire application assumes that the blocks are 8x8 squares.
	 */
	public static final int BLOCK_SIZE = 8;
	
	/*
	 * The number of dimensions in the color spaces.
	 */
	public static final int COLOR_SPACE_SIZE = 3;
	
	/*
	 * The RGB color space.
	 */
	public static final int R = 0;
	public static final int G = 1;
	public static final int B = 2;
	
	/*
	 * The YUV color space.
	 */
	public static final int Y = 0;
	public static final int U = 1;
	public static final int V = 2;
	
	//Arguments fourni par l'usager
	public static String fichierEntree = "";
	public static String fichierSortie = "";
	public static int facteurQuantification = 0;
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main1(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
	
		try {
			if (args.length == 3) {
				fichierEntree = args[0];
				fichierSortie = args[1];
				facteurQuantification = Integer.parseInt(args[2]);

				//On lit le fichier d'entree
				//PPMReaderWriter ppmReaderWriter = new PPMReaderWriter();
				int[][][] matriceRGB = PPMReaderWriter.readPPMFile(fichierEntree);

				//On converti la matriceRGB en matriceYUV
				ConvertRGB2YUV convertRGB2YUV = new ConvertRGB2YUV();
				int[][][] matriceYUV = convertRGB2YUV.convert(matriceRGB);

				//On decoupe la matrice en bloc 8x8
				Decoupage8x8 decoupage = new Decoupage8x8();
				ArrayList<ArrayList<int[][]>> listeBloc8x8 = decoupage.decoupe(matriceYUV);
				
				//On applique le DCT sur chaque bloc 8x8
				DCT dct = new DCT();
				listeBloc8x8 = dct.process(listeBloc8x8);

				//On applique la quantification sur chaque bloc 8x8
				Quantification quantification = new Quantification();
				listeBloc8x8 = quantification.process(listeBloc8x8, facteurQuantification);
				
				//Zigzag
				ZigZag zigZaz = new ZigZag();
				ArrayList<ArrayList<int[]>> listeTab64 = new ArrayList<ArrayList<int[]>>();
				listeTab64 = zigZaz.process(listeBloc8x8);
			
				System.out.println(listeTab64.get(0).size());
				System.out.println(listeTab64.get(1).size());
				System.out.println(listeTab64.get(2).size());
				
			} else {
				System.out.println("il manque des arguments !");
				System.out.println("<fichierEntree> <fichierSortie> <facteurQuantification>");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
//	public static void afficheMatrice(int [][][] matrice){
//		int i,j,k = 0;
//		int ni, nj, nk, n = 0;
//	     
//		n = matrice.length;
//		ni = matrice[0].length;
//		nj = matrice[1].length;
//		nk = matrice[2].length;
//		
//		System.out.println(" ni : " + ni + " nj : " + nj + " nk : " + nk);
//		
//		for (i=0; i<n; i++){
//			if ( i==0){
//				System.out.println("");
//				System.out.println("");
//				System.out.println("Composante R / Y : ");
//			}
//			if ( i==1){
//				System.out.println("");
//				System.out.println("");
//				System.out.println("Composante G / U : ");
//			}
//			if ( i==2){
//				System.out.println("");
//				System.out.println("");
//				System.out.println("Composante B / V : ");
//			}
//			for (j=0; j<nj; j++){
//				System.out.println("");
//				for (k=0; k<nk; k++){
//					System.out.print(matrice[i][j][k] + " ");
//				}
//			}
//		}	
//	}
	
}
