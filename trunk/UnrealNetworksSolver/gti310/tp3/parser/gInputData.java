package gti310.tp3.parser;
import java.util.*;

public class gInputData {

	private int nbSommet ;
	private int valI ;
	private int SommetDepart ;	
	final String end = "$";
	private ArrayList<Integer> m = new ArrayList<Integer> ();


	public void add(String n){
		
		
		if(!n.equals(end)){
			
			int i = Integer.parseInt(n);			
			m.add(i);			
		}
		System.out.println(""+m.toString());
	}
	/**
	 * @param nbSommet the nbSommet to  set
	 */
	public void setNbSommet(int nbSommet) {
		this.nbSommet = nbSommet;
	}
	/**
	 * @return the nbSommet
	 */
	public int getNbSommet() {
		return nbSommet;
	}
	/**
	 * @param valI the valI to set
	 */
	public void setValI(int valI) {
		this.valI = valI;
	}
	/**
	 * @return the valI
	 */
	public int getValI() {
		return valI;
	}
	/**
	 * @param sommetDepart the sommetDepart to set
	 */
	public void setSommetDepart(int sommetDepart) {
		SommetDepart = sommetDepart;
	}
	/**
	 * @return the sommetDepart
	 */
	public int getSommetDepart() {
		return SommetDepart;
	}


}
