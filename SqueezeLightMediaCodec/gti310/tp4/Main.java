package gti310.tp4;

import java.util.ArrayList;

/**
 * The Main class is where the different functions are called to either encode
 * a PPM file to the Squeeze-Light format or to decode a Squeeze-Ligth image
 * into PPM format. It is the implementation of the simplified JPEG block 
 * diagrams.
 * 
 * @author Franï¿½ois Caron
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
	
	//Hauteur et largeur de l'image
	public static int hauteur = 0;
	public static int largeur = 0;
	
	/**
	 * The application's entry point.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
	
		try {
			if (args.length == 3) {
				fichierEntree = args[0];
				fichierSortie = args[1];
				facteurQuantification = Integer.parseInt(args[2]);
				
				//On lit le fichier d'entree
				int[][][] matriceRGB = PPMReaderWriter.readPPMFile(fichierEntree);
				
				//On defini les dimensions de l'image
				hauteur = matriceRGB[0].length;
				largeur = matriceRGB[0].length;
				
				//On converti la matriceRGB en matriceYUV
				int[][][] matriceYUV = ConvertRGB2YUV.convert(matriceRGB);

				//On decoupe la matrice en bloc 8x8
				ArrayList<ArrayList<int[][]>> listeBloc8x8 = Decoupage8x8.decoupe(matriceYUV);
				System.out.println();
				System.out.println("Avant DCT :");
				afficherBloc(listeBloc8x8.get(0).get(0));
				
				//On applique le DCT sur chaque bloc 8x8
				listeBloc8x8 = DCT.process(listeBloc8x8);
				System.out.println();
				System.out.println();
				System.out.println("Après DCT :");
				afficherBloc(listeBloc8x8.get(0).get(0));

				//On applique la quantification sur chaque bloc 8x8
				listeBloc8x8 = Quantification.process(listeBloc8x8, facteurQuantification);
				System.out.println();
				System.out.println();
				System.out.println("Après Quantification :");
				afficherBloc(listeBloc8x8.get(0).get(0));
				
				//On applique la lecture en Zigzag
				ArrayList<ArrayList<int[]>> listeTab64 = new ArrayList<ArrayList<int[]>>();
				listeTab64 = ZigZag.process(listeBloc8x8);
			
				//On applique le DPCM
				DPCM.process(listeTab64);
				
				//On applique le RLC
				RLC.process(listeTab64);
				
				//On écrit le fichier de sortie
				SZLReaderWriter.writeSZLFile("imageCompr.szl", hauteur, largeur, facteurQuantification);
				
			} else {
				System.out.println("il manque des arguments !");
				System.out.println("<fichierEntree> <fichierCompr> <facteurQuantification>");
				System.out.println("ou");
				System.out.println("<fichierCompr> <fichierSortie> <facteurQuantification>");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public static void afficherBloc(int[][] bloc8x8){
		for (int i=0;i<8;i++){
			System.out.println();
			for (int j=0;j<8;j++){
				System.out.print(bloc8x8[i][j] + " ");
			}
		}
	}
}
