package gti310.tp2;

import gti310.tp2.io.FileSource;

import java.io.FileNotFoundException;

public class Application {

	
	private final int HEADER_SIZE = 44;
	
	/**
	 * Launch the application
	 * @param args This parameter is ignored
	 */
	public static void main(String args[]) {
		System.out.println("Audio Resample project!");
		
		try {
			FileSource fs = new FileSource("App1Test1Mono16bits.wav");
			
			byte[] header = fs.pop(44);
			
			
			int i = 0;
			
			for (i = 0; i < 44; i++){
				System.out.print(Integer.toHexString(header[i]));
				
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
