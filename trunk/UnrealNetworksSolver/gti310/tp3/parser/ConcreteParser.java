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
	final String end = "$";




	@Override
	public gInputData parse(String filename) {


		if(validate(filename)){

			try {
				Scanner s = new Scanner(new FileReader(filename));			
				//boucle qui s'occupe d'envoyer les donnees a la classe gInputData

				while(s.hasNextInt()){				

					data.add(s.nextInt());	
				}

			} 

			catch (FileNotFoundException e) {
				System.out.println("Fichier introuvable!");
				e.printStackTrace();
			}
		}else
			System.out.println("Format de fichier non valide !!!");
		


		// TODO Auto-generated method stub
		return null;
	}

	private boolean validate(String filename){

		int i = 0 ;
		boolean isValid = true ;

		try{
			Scanner s = new Scanner (new FileReader(filename));
			while(s.hasNextInt()){	

				Pattern p = Pattern.compile("[\\D]+");
				String[] result = p.split(s.nextLine());

				if(i==0 && result.length!=1 || i==1 && result.length!=1 || i==2 && result.length!=1)
					isValid = false;

				if(i>2){
					if(result.length!=3)
						isValid=false;
				}

				i++;

			}
			if(!s.next().equals(end))
				isValid=false;

		}
		catch (FileNotFoundException e) {
			System.out.println("Fichier introuvable!");
			e.printStackTrace();
		}






		//System.out.println("valide ?" +isValid);
		return isValid;

	}

}
