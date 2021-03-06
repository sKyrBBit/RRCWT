package ast;

import middle_end.IRBuilder;

public class UnaryOperator extends Operator {
    private final Expression operand;
    public UnaryOperator(final String operator, final Expression operand) {
        super(operator);
        this.operand = operand;
    }
    @Override
    public Expression generate() {
        return new UnaryOperator(operator, operand.reduce());
    }
    @Override
    public String toAssembly() {
        switch (operator) {
            case "-":
                return "  pop  rax\n  neg  rax\n  push rax\n";
            default:
                System.out.println("expected operator, but found `" + operator + "`");
                System.exit(1);
                return null;
        }
    }
    @Override
    public String toS(int tab) {
        tab += 2 + operator.length();
        return "(" + operator + " " + operand.toS(tab) + ")";
    }
    @Override
    public String toString() {
        return operator + " " + operand.toString();
    }

    @Override
    public ir.Operand toIR() {
        ir.Operand o = operand.toIR();
        ir.Register tmp = new ir.Register("$" + IRBuilder.tmp());
        IRBuilder.add(new ir.ThreeAddress(tmp, new ir.Immediate(0), operator, o));
        return tmp;
    }
}
