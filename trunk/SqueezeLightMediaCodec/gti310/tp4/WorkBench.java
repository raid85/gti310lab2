package gti310.tp4;
import java.util.*;


public class WorkBench  {
	
	public static void main1(String[] args) {
		
		ConvertRGB2YUV test = new ConvertRGB2YUV();
		int [][][]RGB=null;
		RGB=PPMReaderWriter.readPPMFile("monalisa.ppm");		
		PPMReaderWriter.writePPMFile("mona3.ppm", test.convert(RGB));
		
		/**for(int i =0;i<3;i++){
			System.out.println("RGBLENGHT"+RGB.length);
			for(int j =0 ;j<256;j++){
				for(int k=0;k<256;k++){
					System.out.println("RGB["+i+"]["+j+"]["+k+"]");
					System.out.println(" "+RGB[i][j][k]);
					
				}
				
			}
		}*/
		
		
		
		
	}

}
