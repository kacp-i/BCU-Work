package bcu.funnypackage.expressiontree;

import java.util.*;

public class Variable extends Expression {
    private String varName;

    //To create a Variable object you need varName
    public Variable(String variableName) {
        this.varName = variableName;
    }

    @Override
    public final double evaluate(Map<String, Double> variables){
        if (variables.get(this.varName) == null) {
            throw new IllegalArgumentException("Variable name not in map");
        }
        else {
            return variables.get(this.varName);
        }
    };

    @Override
    public final Set<String> freeVariables() {
        Set<String> variables = new HashSet<>();

        variables.add(this.varName);

        return Collections.unmodifiableSet(variables);
    }
}
