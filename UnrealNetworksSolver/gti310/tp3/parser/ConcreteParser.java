package gti310.tp3.parser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * http://www.javapractices.com/topic/TopicAction.do?Id=42
 * @author ah45290
 *
 */
public class ConcreteParser implements Parser<gInputData> {
	
	private gInputData data = new gInputData();
	

	
	
	@Override
	public gInputData parse(String filename) {
		
		Scanner s;		
		try {
			s = new Scanner(new FileReader(filename));			
			while(s.hasNext()){
				
				data.add(s.next());
			}
		} 
		catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable!");
			e.printStackTrace();
		}
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
