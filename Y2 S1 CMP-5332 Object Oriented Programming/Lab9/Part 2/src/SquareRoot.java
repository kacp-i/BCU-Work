package bcu.funnypackage.expressiontree;

import java.lang.Math;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SquareRoot extends Expression{
    private Expression expression;

    public SquareRoot(Expression expression) {
        this.expression = expression;
    }

    @Override
    public final double evaluate(Map<String, Double> variables) {
        double toSquared = this.expression.evaluate(variables);
        return Math.sqrt(toSquared);
    }

    @Override
    public final Set<String> freeVariables() {
		Set<String> variables = new HashSet<>();
		
		variables.addAll(this.expression.freeVariables());
		
		return Collections.unmodifiableSet(variables);
	}
}
