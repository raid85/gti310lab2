package gti310.tp3.solver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gti310.tp3.parser.InputData;

public class ConcreteSolver implements Solver<InputData, SolutionData>{

	
	public static int[][] matrice = null;
	public static int n;
	public static int depart = 0;
	public static int valInf = 999999;
	public static int counter = 0;
	private static SolutionData solutionData = new SolutionData();
	public static ArrayList<Integer> listeVoisins;
	
	/**
	 * Methode qui resous le probleme a partir des infos de gInputData (matrice, sommetDepart, etc)
	 * et qui retourne une liste des solutions trouvé
	 * 
	 * Analyse de complexité : 
	 * 
	 * meilleur cas : 0(nbSommets) 
	 * 
	 * pire cas : Puisqu'il s'agit d'un algorithme de récursivité que non devons prévenir de 
	 * récursivité infini dans le cas ou il n'y a pas de solution possible, le pire cas serait quand 
	 * notre compteur atteint la limite que l'on lui impose soit 0(limite max du compteur) 
	 * 
	 * @param input (gInputData) Ce qui est requis pour resoudre le probleme
	 * @return solutions ( ArrayList<List<Integer>> ) ou null
	 */
	public SolutionData solve(InputData data) {
		
		try {
			matrice = data.getMatrice();
			depart = (data.getSommetDepart() - 1); //depart = depart - 1 car l'indice de la matrice commence a 0...
			valInf = data.getValI();
			n = matrice.length;
			
			hamiltonianPath();
			
			return solutionData;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Methode qui appel la methode recursive hamiltonianPath(ArrayList<Integer>)
	 * Sert de point de depart
	 * 
	 * @return void
	 */
	public static void hamiltonianPath() {
		hamiltonianPath(new ArrayList<Integer>());
	}
	
	/**
	 * Methode récursive qui trouve un cycle hamiltonien a partir d'une matrice d'adjacence
	 * Sert de point de depart
	 * 
	 * Référence : http://moodle.cornellcollege.edu/0809/mod/resource/view.php?id=8993
	 * 
	 * @return void
	 */
	public static void hamiltonianPath(List<Integer> chemin){
		
		//Ajoute le sommet de départ et un counter pour ajouter le sommet de depart
		//au chemin seulement une fois
		if (counter == 0){
			chemin.add(depart);
			counter++;
		}
		
		//On vérifie si le chemin passe par tous les sommets et s'il termine par un sommet qui à le sommet de depart en tant que voisin
		//pour pouvoir terminer le cycle au point de départ
		if (chemin.size() == n) {
			boolean cycle = false;
			
			//On va chercher les voisins du dernier noeud du chemin
			ArrayList<Integer> voisins = getVoisins(chemin.get(n-1));
			
			//On regarde si un des voisins est le noeud de départ (pour pouvoir boucler)
			for (int i = 0; i < voisins.size();i++){
				if (depart == voisins.get(i)){
					cycle = true;
				}
			}
			//Si on a un cycle on ajoute la solution
			if (cycle == true){
				ajouteSolution(chemin);
				cycle = false;
				return;
			}
			
		} else if (chemin.size() == 0) {
			for (int i=0;i <n; i++ ) {
				chemin.add(i);
				hamiltonianPath(chemin);
				chemin.remove(chemin.size()-1);
		}
		} else {
			int noeudCourant = chemin.get(chemin.size()-1);
			for (int i=0;i <n; i++ ) {
				if (!chemin.contains(i) && matrice[noeudCourant][i] < valInf) {
					chemin.add(i);
					hamiltonianPath(chemin);
					chemin.remove(chemin.size()-1);
		}
	}
	}	
		//Empêche la récursion infini?
		if (counter > 999999999){
			return;
		}
	}

	/**
	 * Methode qui ajoute une solution dans la liste de solutions et
	 * imprime a l'écran la solution
	 *
	 * @param List<Integer> chemin
	 * @return void
	 */
	public static void ajouteSolution(List<Integer> chemin) {
		String solution = "";
		System.out.print("Solution : ");
		
		//On parcours le chemin et on ajoute l'écrit dans la String solution
		Iterator<Integer> it = chemin.iterator();
		while (it.hasNext()) {
			int nb = it.next();
			solution = solution + Integer.toString((nb + 1)) + " -> ";
			System.out.print((nb + 1) +" ");
		}
		System.out.print((depart+1) +" ");
		System.out.println();
		
		//On ferme le cyle avec le sommet de depart
		solution = solution + (Integer.toString(depart) + 1);
		
		//On ajoute la solution trouvé à la liste de solutions
		solutionData.addSolution(solution);
	}

	/**
	 * Methode qui trouve les voisins d'un sommet à partir de la matrice
	 *
	 * @param int noeud
	 * @return ArrayList<Integer> Liste des voisins
	 */
	public static ArrayList<Integer> getVoisins(int noeud) {
		
		listeVoisins = new ArrayList<Integer>();
		 for(int j=0; j < n; j++){
	 	   if (matrice[noeud][j] < valInf) {
	 		   listeVoisins.add(j);
	 	   }   
	    }
		 return listeVoisins;
	}

}
