package bcu.funnypackage.expressiontree;

public class Division extends BinaryOperation{
    private final Expression left;
    private final Expression right;

    public Division(Expression left, Expression right) {
        super(left, right);

        this.left = left;
        this.right = right;
    }

    @Override
	public final double evaluate(double a, double b) {
		return a / b;
	}
}
