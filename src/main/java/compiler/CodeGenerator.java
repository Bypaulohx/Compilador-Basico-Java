package compiler;

import java.util.*;

public class CodeGenerator {
    private List<String> instructions = new ArrayList<>();

    public void visit(Parser.AST node) {
        if (node instanceof Parser.Num) {
            instructions.add("PUSH " + ((Parser.Num) node).value);
        } else if (node instanceof Parser.BinOp) {
            Parser.BinOp binOp = (Parser.BinOp) node;
            visit(binOp.left);
            visit(binOp.right);
            switch (binOp.op.type) {
                case PLUS: instructions.add("ADD"); break;
                case MINUS: instructions.add("SUB"); break;
                case MUL: instructions.add("MUL"); break;
                case DIV: instructions.add("DIV"); break;
                default: throw new RuntimeException("Unknown operator");
            }
        }
    }

    public List<String> getInstructions() {
        return instructions;
    }
}
