package eu.unipv.epsilon.eqsolve.equation;

public interface Equation {

    // String getFriendlyName();

    String getCurrentFormula();

    int getParamListSize();

    void setParam(int idx, double value);

    double getParam(int idx);

}
