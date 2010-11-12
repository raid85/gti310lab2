package gti310.tp3.solver;

import java.util.ArrayList;

public class SolutionData {

	 ArrayList<String> listeSolutions = new ArrayList<String>();
	 
	 public String getSolution(int i){
		 return listeSolutions.get(i);
	 }
	 
	 public int getNbSolution(){
		 return listeSolutions.size();
	 }
	 public void addSolution(String solution){
		 listeSolutions.add(solution);
	 }
	 
	public ArrayList<String> getListeSolutions() {
		return listeSolutions;
	}

	public void setListeSolutions(ArrayList<String> listeSolutions) {
		this.listeSolutions = listeSolutions;
	}
}
