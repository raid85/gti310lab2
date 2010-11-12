package gti310.tp3.parser;

public class InputData {

	private int nbSommet ;
	private int valI ;
	private int SommetDepart ;	
	private int[][] matrice ;
	
	public int[][] getMatrice() {
		return matrice;
	}
	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}
//	private ArrayList<Integer> m = new ArrayList<Integer> ();
//
//
//	public void add(int n){		
//						
//			m.add(n);			
//		
//		System.out.println(""+m.toString());
//	}
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
