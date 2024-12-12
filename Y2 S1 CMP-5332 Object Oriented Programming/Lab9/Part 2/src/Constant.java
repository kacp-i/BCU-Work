package bcu.funnypackage.expressiontree;

import java.util.*;

public class Constant extends Expression {
    private double constantValue;

    //To create a Constant object you need constantValue
    public Constant(double cValue) {
        this.constantValue = cValue;
    }

    @Override
    public final double evaluate(Map<String, Double> variables) {
        return constantValue;
    }

    @Override
    public final Set<String> freeVariables() {
        return Collections.emptySet();
    }
}
