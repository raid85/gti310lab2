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
	public static boolean decode = false;
	public static boolean encode = false;
	
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
			if (args.length >= 3) {
				
				if (args.length == 4){
					fichierEntree = args[0];
					fichierSortie = args[1];
					facteurQuantification = Integer.parseInt(args[2]);
					
					if(args[3].equals("encode")){
						encode = true;
					}
					if(args[3].equals("decode")){
						decode = true;
					}
				}
				if (args.length == 3){
					fichierEntree = args[0];
					fichierSortie = args[1];
					
					if(args[2].equals("encode")){
						encode = true;
					}
					if(args[2].equals("decode")){
						decode = true;
					}
				}
				
				//Si l'utilisateur demande un encodage
				if (encode){
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
					System.out.println();
					System.out.println();
					System.out.println("Après ZigZag :");
					afficherTab(listeTab64.get(0).get(0));
				
					//On applique le DPCM
					DPCM.process(listeTab64);
					
					//On applique le RLC
					RLC.process(listeTab64);
					
					//On écrit le fichier de sortie
					SZLReaderWriter.writeSZLFile(fichierSortie, hauteur, largeur, facteurQuantification);
				}
				
				//Si l'utilisateur demande un decodage
				if (decode){
					
					//On lit le fichier SZL
					int[] header = SZLReaderWriter.readSZLFile(fichierEntree);
					hauteur = header[0]; 
					largeur = header[1]; 
					facteurQuantification = header[3];
					
					ArrayList<ArrayList<int[]>> listeTab64 = new ArrayList<ArrayList<int[]>>();
					
					//On effectue le RLC inverse
					//RLC.processINV();
					
					
					
				}
				
			} else {
				System.out.println();
				System.out.println("il manque des arguments !");
				System.out.println();
				System.out.println("Pour un encodage :");
				System.out.println("<fichierEntree> <fichierCompr> <facteurQuantification> <encode>");
				System.out.println();
				System.out.println("Pour un encodage :");
				System.out.println("<fichierCompr> <fichierSortie> <decode>");
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
	public static void afficherBloc(int[][] bloc8x8){
		for (int i=0;i<8;i++){
			if (i>0){
				System.out.println();
			}
			for (int j=0;j<8;j++){
				System.out.print(bloc8x8[i][j] + " ");
			}
		}
	}
	public static void afficherTab(int[] tab){
		for (int i=0;i<tab.length;i++){
				System.out.print(tab[i] + " ");
			}
	}
}
