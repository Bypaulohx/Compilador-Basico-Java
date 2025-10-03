package compiler;

import java.util.*;

public class Parser {
    private Lexer lexer;
    private Lexer.Token currentToken;

    public abstract static class AST {}

    public static class BinOp extends AST {
        public AST left;
        public Lexer.Token op;
        public AST right;
        public BinOp(AST left, Lexer.Token op, AST right) {
            this.left = left;
            this.op = op;
            this.right = right;
        }
    }

    public static class Num extends AST {
        public String value;
        public Num(Lexer.Token token) { this.value = token.value; }
    }

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currentToken = lexer.getNextToken();
    }

    private void eat(Lexer.TokenType type) {
        if (currentToken.type == type) {
            currentToken = lexer.getNextToken();
        } else {
            throw new RuntimeException("Unexpected token: " + currentToken);
        }
    }

    private AST factor() {
        Lexer.Token token = currentToken;
        if (token.type == Lexer.TokenType.NUMBER) {
            eat(Lexer.TokenType.NUMBER);
            return new Num(token);
        } else if (token.type == Lexer.TokenType.LPAREN) {
            eat(Lexer.TokenType.LPAREN);
            AST node = expr();
            eat(Lexer.TokenType.RPAREN);
            return node;
        }
        throw new RuntimeException("Unexpected factor: " + token);
    }

    private AST term() {
        AST node = factor();
        while (currentToken.type == Lexer.TokenType.MUL || currentToken.type == Lexer.TokenType.DIV) {
            Lexer.Token token = currentToken;
            if (token.type == Lexer.TokenType.MUL) eat(Lexer.TokenType.MUL);
            else eat(Lexer.TokenType.DIV);
            node = new BinOp(node, token, factor());
        }
        return node;
    }

    public AST expr() {
        AST node = term();
        while (currentToken.type == Lexer.TokenType.PLUS || currentToken.type == Lexer.TokenType.MINUS) {
            Lexer.Token token = currentToken;
            if (token.type == Lexer.TokenType.PLUS) eat(Lexer.TokenType.PLUS);
            else eat(Lexer.TokenType.MINUS);
            node = new BinOp(node, token, term());
        }
        return node;
    }
}
