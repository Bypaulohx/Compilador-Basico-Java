package compiler;

import java.util.*;

public class VirtualMachine {
    private Stack<Integer> stack = new Stack<>();

    public int execute(List<String> instructions) {
        for (String instr : instructions) {
            if (instr.startsWith("PUSH")) {
                stack.push(Integer.parseInt(instr.split(" ")[1]));
            } else if (instr.equals("ADD")) {
                stack.push(stack.pop() + stack.pop());
            } else if (instr.equals("SUB")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a - b);
            } else if (instr.equals("MUL")) {
                stack.push(stack.pop() * stack.pop());
            } else if (instr.equals("DIV")) {
                int b = stack.pop();
                int a = stack.pop();
                stack.push(a / b);
            }
        }
        return stack.pop();
    }
}
