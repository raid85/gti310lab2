package gti310.tp3;
import java.util.ArrayList;
import java.util.List;

import gti310.tp3.parser.*;
import gti310.tp3.solver.ConcreteSolver;
import gti310.tp3.solver.Solver;
import gti310.tp3.writer.ConcreteWriter;
import gti310.tp3.writer.Writer;

/**
 * The Application class defines a template method to call the elements to
 * solve the problem Unreal-Networks is façing.
 * 
 * @author François Caron <francois.caron.7@ens.etsmtl.ca>
 */
public class Application {

	/**
	 * The Application's entry point.
	 * 
	 * The main method makes a series of calls to find a solution to the
	 * problem. The program awaits two arguments, the complete path to the
	 * input file, and the complete path to the output file.
	 * 
	 * @param args The array containing the arguments to the files.
	 */
	public static void main(String args[]) {
		System.out.println("Unreal Networks Solver !");
		Parser<gInputData> p = new ConcreteParser();
		Solver<gInputData, ?> s = new ConcreteSolver();
		Writer<ArrayList<List<Integer>>> w = new ConcreteWriter();
		
		//On récupère les données du fichier source
		gInputData data = p.parse(args[0]);
		
		//On résous le problème à partir des données
		ArrayList<List<Integer>> solutions = (ArrayList<List<Integer>>) s.solve(data);
		
		//On écrit les solutions trouvé dans le fichier de sortie
		w.write(args[1], solutions);
		
	}
}
