package eu.unipv.epsilon.eqsolve.solver;

import eu.unipv.epsilon.eqsolve.equation.Equation;
import eu.unipv.epsilon.eqsolve.equation.QuadraticEquation;

public class QuadraticSolver implements Solver {

    @Override
    public boolean canSolve(Equation eq) {
        return eq instanceof QuadraticEquation;
    }

    @Override
    public double[] solve(Equation eq) {
        if (!canSolve(eq)) throw new RuntimeException("I told ya dat I can't solve that darn thing!");

        double a = eq.getParam(0);
        double b = eq.getParam(1);
        double c = eq.getParam(2);

        double delta = b * b - 4 * a * c;
        if (delta < 0) throw new RuntimeException("Δ < 0");

        return new double[]{ (- b + delta) / (2 * a), (- b - delta) / (2 * a) };
    }

}
