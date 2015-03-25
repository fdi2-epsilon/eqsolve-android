package eu.unipv.epsilon.eqsolve.solver;

import eu.unipv.epsilon.eqsolve.equation.Equation;

public interface Solver {

    boolean canSolve(Equation eq);

    double[] solve(Equation eq);

}
