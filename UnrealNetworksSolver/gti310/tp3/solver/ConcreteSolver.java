package gti310.tp3.solver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import gti310.tp3.parser.gInputData;

public class ConcreteSolver implements Solver<gInputData, ArrayList<List<Integer>>>{

	
	public static int[][] matrice = {
		 { 0, 0, 0, 0, 2, 0, 0, 3, 0, 0, 4 },
	     { 0, 0, 0, 0, 0, 3, 5, 0, 0, 0, 0 },
	     { 0, 0, 0, 0, 0, 3, 0, 4, 0, 2, 0 },
	     { 0, 1, 0, 0, 0, 0, 0, 0, 5, 0, 0 },
	     { 2, 0, 0, 0, 0, 0, 0, 0, 2, 0, 3 },
	     { 0, 0, 3, 0, 0, 0, 0, 0, 2, 4, 0 },
	     { 0, 5, 0, 0, 0, 0, 0, 0, 0, 3, 0 },
	     { 3, 0, 4, 0, 0, 0, 0, 0, 0, 0, 6 },
	     { 0, 0, 0, 5, 2, 0, 0, 0, 0, 0, 0 },
	     { 0, 0, 2, 0, 0, 4, 3, 0, 0, 0, 0 },
	     { 0, 0, 0, 0, 3, 0, 0, 6, 0, 0, 0 },
	};
	public static int n;
	public static int depart = 0;
	public static int counter = 0;
	public static ArrayList<List<Integer>> solutions = new ArrayList<List<Integer>>();
	public static ArrayList<Integer> solution = new ArrayList<Integer>();
	public static ArrayList<Integer> listeVoisins;
	
	/**
	 * Methode qui resous le probleme a partir des infos de gInputData (matrice, sommetDepart, etc)
	 * et qui retourne une liste des solutions trouv�
	 * 
	 * @param input (gInputData) Ce qui est requis pour resoudre le probleme
	 * @return solutions ( ArrayList<List<Integer>> ) ou null
	 */
	public ArrayList<List<Integer>> solve(gInputData input) {
		
		try {
			//int[][] matrice = gInputData.get
			
			n = matrice.length;
			
			hamiltonianPath();
			
			return solutions;
			
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
	 * Methode r�cursive qui trouve un cycle hamiltonien a partir d'une matrice d'adjacence
	 * Sert de point de depart
	 * 
	 * R�f�rence : http://moodle.cornellcollege.edu/0809/mod/resource/view.php?id=8993
	 * 
	 * @return void
	 */
	public static void hamiltonianPath(List<Integer> chemin){
		
		//Ajoute le sommet de d�part et un counter pour ajouter le sommet de depart
		//au chemin seulement une fois
		if (counter == 0){
			chemin.add(depart);
			counter++;
		}
		
		//On v�rifie si le chemin passe par tous les sommets et s'il termine par un sommet qui � le sommet de depart en tant que voisin
		//pour pouvoir terminer le cycle au point de d�part
		if (chemin.size() == n) {
			boolean cycle = false;
			//On va chercher les voisins du dernier noeud du chemin
			ArrayList<Integer> voisins = getVoisins(chemin.get(n-1));
			//On regarde si un des voisins est le noeud de d�part (pour pouvoir boucler)
			for (int i = 0; i < voisins.size();i++){
				if (depart == voisins.get(i)){
					cycle = true;
				}
			}
			//Si on a un cycle on ajoute la solution
			if (cycle == true){
				ajouteSolution(chemin);
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
				if (!chemin.contains(i) && matrice[noeudCourant][i] != 0) {
					chemin.add(i);
					hamiltonianPath(chemin);
					chemin.remove(chemin.size()-1);
		}
	}
	}

	}

	/**
	 * Methode qui ajoute une solution dans la liste de solutions et
	 * imprime a l'�cran la solution
	 *
	 * @param List<Integer> chemin
	 * @return void
	 */
	public static void ajouteSolution(List<Integer> chemin) {
		Iterator<Integer> it = chemin.iterator();
		while (it.hasNext()) {
			System.err.print((it.next()+ 1) +" ");
		}
		//On ferme le cyle avec le sommet de depart
		solution = (ArrayList<Integer>) chemin;
		solution.add(depart);
		//On ajoute la solution trouv� � la liste de solutions
		solutions.add(solution);
		System.err.print((depart+1) +" ");
		System.err.println();
	}

	/**
	 * Methode qui trouve les voisins d'un sommet � partir de la matrice
	 *
	 * @param int noeud
	 * @return ArrayList<Integer> Liste des voisins
	 */
	public static ArrayList<Integer> getVoisins(int noeud) {
		
		listeVoisins = new ArrayList<Integer>();
		 for(int j=0; j < n; j++){
	 	   if (matrice[noeud][j] > 0) {
	 		   listeVoisins.add(j);
	 	   }   
	    }
		 return listeVoisins;
	}

}