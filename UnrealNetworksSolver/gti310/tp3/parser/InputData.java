package gti310.tp3.parser;

/**
 *Cette classe est la classe décrite par "E" dans le rapport de laboratoire.
 *Sa fonction est de contenir les informations relatives à la matrice représentant le graphe
 *ainsi que de fournir les accesseurs nécéssaire à la résolution du problème
 */

public class InputData {

	private int nbSommet ;
	private int valI ;
	private int SommetDepart ;	
	private int[][] matrice ;
	
	/**
	 *Ensembles des accesseurs et des mutateurs pour la classe 
	 */
	public int[][] getMatrice() {
		return matrice;
	}
	public void setMatrice(int[][] matrice) {
		this.matrice = matrice;
	}
	public void setNbSommet(int nbSommet) {
		this.nbSommet = nbSommet;
	}
	
	public int getNbSommet() {
		return nbSommet;
	}

	public void setValI(int valI) {
		this.valI = valI;
	}
	
	public int getValI() {
		return valI;
	}
	public void setSommetDepart(int sommetDepart) {
		SommetDepart = sommetDepart;
	}
	public int getSommetDepart() {
		return SommetDepart;
	}


}
