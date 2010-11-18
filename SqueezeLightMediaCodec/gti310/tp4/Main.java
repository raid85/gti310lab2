package gti310.tp4;

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
	public static void main(String[] args) {
		System.out.println("Squeeze Light Media Codec !");
		
		if (args[0] != null && args[1] != null && args[2] != null){
			fichierEntree = args[0];
			fichierSortie = args[1];
			facteurQuantification = Integer.parseInt(args[2]);
			
			//On lit le fichier d'entr�e
			PPMReaderWriter ppmReaderWriter = new PPMReaderWriter();
			int[][][] matriceRGB = ppmReaderWriter.readPPMFile(fichierEntree);
			
			//On converti la matriceRGB en matriceYUV
			ConvertRGB2YUV convertRGB2YUV = new ConvertRGB2YUV();
			int[][][] matriceYUV = convertRGB2YUV.convert_RGB_to_YUV(matriceRGB);
			
			
		}else{
			System.out.println("il manque des arguments !");
			System.out.println("<fichierEntr�e> <fichierSortie> <facteurQuantification>");
		}
		
	}
}