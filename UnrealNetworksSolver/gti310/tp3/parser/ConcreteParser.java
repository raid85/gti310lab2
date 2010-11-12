package gti310.tp3.parser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
	private int[][] matrice = null;



	@Override
	public gInputData parse(String filename) {


		if(validate(filename)){

			File file = new File(filename);
		    FileInputStream fis = null;
		 
		    try {
		      fis = new FileInputStream(file);
		      BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
		      String line = null;
		      
		      //La premiere ligne sera le nombre de sommet
		      data.setNbSommet(Integer.parseInt(reader.readLine()));
		      
		      //La deuxieme ligne sera la valeur pour l'infinie
		      data.setValI(Integer.parseInt(reader.readLine()));
		      
		      //La troisieme ligne sera le sommet de depart
		      data.setSommetDepart(Integer.parseInt(reader.readLine()));
		      
		      //On se sert du nombre de sommets pour initialiser la matrice avec les dimensions
		      int nbSommets = data.getNbSommet();
		      matrice = new int[nbSommets][nbSommets];
		      
		      //Boucle pour initialiser la matrice avec la valeur pour l'infinie...)
		      int i,j = 0;
		      for (i=0;i<nbSommets;i++){
		    	  for (j=0;j<nbSommets;j++){
		    		  matrice[i][j] = data.getValI();
		    	  }
		      }
		      //On lit le reste du fichier pour construire la matrice
		      while ((line = reader.readLine()) != null) {

		    	  if (!(line.contains(end))){
		    		  try{
		    		  String[] strArray = line.split("\t");
		    		  int sommet1 = Integer.parseInt(strArray[0]);
		    		  int sommet2 = Integer.parseInt(strArray[1]);
		    		  int poids = Integer.parseInt(strArray[2]);
		    		
		    		  //On enlève 1 aux sommets car l'indice de la matrice commence à 0...
		    		  matrice[(sommet1 - 1)][(sommet2 - 1)] = poids;
		    		  
		    		  //System.out.println(line);
		    		  } catch (Exception e) {
		    		      e.printStackTrace();
		    		    }
			        }
		    	  else{
		    		  break;
		    	  }
		      }

		      data.setMatrice(matrice);
		      
		      //On ferme l'inputStream et le reader
		      fis.close();
		      reader.close();

		    } catch (FileNotFoundException e) {
		      e.printStackTrace();
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    
//			try {
//				Scanner s = new Scanner(new FileReader(filename));			
//				//boucle qui s'occupe d'envoyer les donnees a la classe gInputData
//
//				while(s.hasNextInt()){				
//
//					data.add(s.nextInt());	
//				}
//
//			} 
//
//			catch (FileNotFoundException e) {
//				System.out.println("Fichier introuvable!");
//				e.printStackTrace();
//			}
		}else
			System.out.println("Format de fichier non valide !!!");
		
		return data;
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
