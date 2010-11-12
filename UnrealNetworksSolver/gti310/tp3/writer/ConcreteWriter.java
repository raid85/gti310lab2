package gti310.tp3.writer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class ConcreteWriter implements Writer<ArrayList<List<Integer>>> {

	
	public void write(String filename, ArrayList<List<Integer>> output) {
		
		 try{
			    //On crée le fichier
			    FileWriter fstream = new FileWriter(filename);
			    BufferedWriter writer = new BufferedWriter(fstream);
			    
			    int i,j=0;
			    //Boucle qui passe le nombre de solutions
			    for (i=0;i<output.size();i++){
			    	List<Integer> solution = output.get(i);
			    	//On écrit chaque solution
			    	for (j=0;j<solution.size();j++){
			    		if (j == (solution.size()-1)){
			    			writer.write(solution.get(j).toString());
			    		}else{
			    			writer.write(solution.get(j).toString());
				    		writer.write(" -> ");
			    		}	
			    	}
			    }
			   
			    writer.close(); 
			    
			    }catch (Exception e){//Catch exception if any
			      System.err.println("Error: " + e.getMessage());
			    }
		
	}

}
 