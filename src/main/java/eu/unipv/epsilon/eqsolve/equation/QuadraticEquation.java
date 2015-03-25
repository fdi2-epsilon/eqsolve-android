package eu.unipv.epsilon.eqsolve.equation;

public class QuadraticEquation extends EquationBase {

    /*@Override
    public String getFriendlyName() {
        return "Quadratic Equation";
    }*/

    @Override
    public String getCurrentFormula() {
        return String.format("%.1f x² + %.1f x + %.1f = 0", getParam(0), getParam(1), getParam(2));
    }

    @Override
    public int getParamListSize() {
        return 3;
    }

}
