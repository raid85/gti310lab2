package gti310.tp3.solver;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class mySolver implements Solver {

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
	public static int n = matrice.length;
	//Les indices commences à 0 donc 0 correspond à 1 pour les sommets etc.
	public static int depart = 0;
	public static int counter = 0;
	public static ArrayList<List<Integer>> solutions;
	public static ArrayList<Integer> solution;
	public static ArrayList<Integer> listeVoisins;

	public static void hamiltonianPath() {
		hamiltonianPath(new ArrayList<Integer>());
	}
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
			if (cycle == true){
				printSolution(chemin);
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

	public static void printSolution(List<Integer> chemin) {
		Iterator<Integer> it = chemin.iterator();
		while (it.hasNext()) {
			System.err.print((it.next()+ 1) +" ");
		}
		//On ferme le cyle avec le sommet de depart
		solution = (ArrayList<Integer>) chemin;
		solution.add(depart);
		//On ajoute la solution trouvé à la liste de solutions
		solutions.add(solution);
		System.err.print((depart+1) +" ");
		System.err.println();

	}

	public static ArrayList<Integer> getVoisins(int noeud) {
		
		listeVoisins = new ArrayList<Integer>();
		 for(int j=0; j < n; j++){
	 	   if (matrice[noeud][j] > 0) {
	 		   listeVoisins.add(j);
	 	   }   
	    }
		 return listeVoisins;
	}

	@Override
	public Object solve(Object input) {
		
		hamiltonianPath();
		
		//On retourne la liste de solutions
		return solutions;
	}

}
