package eu.unipv.epsilon.eqsolve.equation;

abstract class EquationBase implements Equation {

    private double[] params;

    public EquationBase() {
        params = new double[getParamListSize()];
    }

    @Override
    public void setParam(int idx, double value) {
        params[idx] = value;
    }

    @Override
    public double getParam(int idx) {
        return params[idx];
    }

}
