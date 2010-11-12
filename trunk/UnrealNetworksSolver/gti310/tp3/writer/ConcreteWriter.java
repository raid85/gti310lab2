package gti310.tp3.writer;

import gti310.tp3.solver.SolutionData;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ConcreteWriter implements Writer<SolutionData> {

	
	public void write(String filename, SolutionData solutionData) {
		
		 try{
			    //On crée le fichier
			    FileWriter fstream = new FileWriter(filename);
			    BufferedWriter writer = new BufferedWriter(fstream);
			    
			    int nbSol = solutionData.getNbSolution();
			    int i=0;
			    //Boucle qui passe chaque solution
			    for (i=0;i<nbSol;i++){
			    	String solution = solutionData.getSolution(i);
			    	writer.write(solution);
			    }
			   
			    writer.close(); 
			    
			    }catch (Exception e){
			      System.err.println("Error: " + e.getMessage());
			    }
		
	}

}
 