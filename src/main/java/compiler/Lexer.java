package compiler;

import java.util.*;

public class Lexer {
    private String input;
    private int pos;
    private char currentChar;

    public enum TokenType { NUMBER, PLUS, MINUS, MUL, DIV, LPAREN, RPAREN, EOF }
    public static class Token {
        public final TokenType type;
        public final String value;
        public Token(TokenType type, String value) {
            this.type = type;
            this.value = value;
        }
        public String toString() { return type + "('" + value + "')"; }
    }

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
        this.currentChar = input.length() > 0 ? input.charAt(0) : '\0';
    }

    private void advance() {
        pos++;
        currentChar = pos < input.length() ? input.charAt(pos) : '\0';
    }

    private void skipWhitespace() {
        while (Character.isWhitespace(currentChar)) advance();
    }

    private String number() {
        StringBuilder sb = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            sb.append(currentChar);
            advance();
        }
        return sb.toString();
    }

    public Token getNextToken() {
        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue;
            }
            if (Character.isDigit(currentChar)) return new Token(TokenType.NUMBER, number());
            if (currentChar == '+') { advance(); return new Token(TokenType.PLUS, "+"); }
            if (currentChar == '-') { advance(); return new Token(TokenType.MINUS, "-"); }
            if (currentChar == '*') { advance(); return new Token(TokenType.MUL, "*"); }
            if (currentChar == '/') { advance(); return new Token(TokenType.DIV, "/"); }
            if (currentChar == '(') { advance(); return new Token(TokenType.LPAREN, "("); }
            if (currentChar == ')') { advance(); return new Token(TokenType.RPAREN, ")"); }

            throw new RuntimeException("Unexpected char: " + currentChar);
        }
        return new Token(TokenType.EOF, "");
    }
}
