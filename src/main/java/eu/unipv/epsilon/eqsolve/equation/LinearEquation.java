package eu.unipv.epsilon.eqsolve.equation;

public class LinearEquation extends EquationBase {

    /*@Override
    public String getFriendlyName() {
        return "Linear Equation";
    }*/

    @Override
    public String getCurrentFormula() {
        return String.format("%.1f x + %.1f = 0", getParam(0), getParam(1));
    }

    @Override
    public int getParamListSize() {
        return 2;
    }

}
