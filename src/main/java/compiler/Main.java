package compiler;

import java.nio.file.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Uso: java compiler.Main <arquivo>");
            return;
        }
        String code = Files.readString(Paths.get(args[0]));
        Lexer lexer = new Lexer(code);
        Parser parser = new Parser(lexer);
        Parser.AST tree = parser.expr();
        CodeGenerator gen = new CodeGenerator();
        gen.visit(tree);
        List<String> bytecode = gen.getInstructions();
        System.out.println("Bytecode gerado: " + bytecode);
        VirtualMachine vm = new VirtualMachine();
        int result = vm.execute(bytecode);
        System.out.println("Resultado: " + result);
    }
}
