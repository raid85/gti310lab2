package gti310.tp3;
import gti310.tp3.parser.*;
import gti310.tp3.solver.ConcreteSolver;
import gti310.tp3.solver.Solver;

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
		Parser p = new ConcreteParser();
		Solver s = new ConcreteSolver();
		
		//mettre args au lieu de musee.txt
		gInputData data = p.parse("Musee.txt");
		
		s.solve(data);
	}
}
