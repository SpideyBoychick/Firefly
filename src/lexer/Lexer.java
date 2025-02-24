package lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer {

    private String input;
    private int pos = 0;
    private int line = 1;
    private char currentChar;

    private static final Map<String, TTypeValuePair> operatorsTTPair = new HashMap<>();
    private final String operators = "+-*/^=()[]{};|&!<>";

    static {
        operatorsTTPair.put("+", new TTypeValuePair(TokenType.PLUS, "+"));
        operatorsTTPair.put("-", new TTypeValuePair(TokenType.MINUS, "-"));
        operatorsTTPair.put("*", new TTypeValuePair(TokenType.MUL, "*"));
        operatorsTTPair.put("/", new TTypeValuePair(TokenType.DIV, "/"));
        operatorsTTPair.put("^", new TTypeValuePair(TokenType.POW, "^"));
        operatorsTTPair.put("=", new TTypeValuePair(TokenType.ASIGN, "="));
        operatorsTTPair.put("==", new TTypeValuePair(TokenType.EQEQ, "=="));
        operatorsTTPair.put("!=", new TTypeValuePair(TokenType.NOTEQ, "!="));
        operatorsTTPair.put("<", new TTypeValuePair(TokenType.LOWER, "<"));
        operatorsTTPair.put(">", new TTypeValuePair(TokenType.HIGHER, ">"));
        operatorsTTPair.put("<=", new TTypeValuePair(TokenType.LOWEREQ, "<="));
        operatorsTTPair.put(">=", new TTypeValuePair(TokenType.HIGHEREQ, ">="));
        operatorsTTPair.put("++", new TTypeValuePair(TokenType.PLUSPLUS, "++"));
        operatorsTTPair.put("--", new TTypeValuePair(TokenType.MINUSMINUS, "--"));
        operatorsTTPair.put("+=", new TTypeValuePair(TokenType.PLUSEQ, "+="));
        operatorsTTPair.put("-=", new TTypeValuePair(TokenType.MINUSEQ, "-="));
        operatorsTTPair.put("*=", new TTypeValuePair(TokenType.MULEQ, "*="));
        operatorsTTPair.put("/=", new TTypeValuePair(TokenType.DIVEQ, "/="));
        operatorsTTPair.put("^=", new TTypeValuePair(TokenType.POWEQ, "^="));
        operatorsTTPair.put("!", new TTypeValuePair(TokenType.NOT, "!"));
        operatorsTTPair.put("||", new TTypeValuePair(TokenType.OROR, "||"));
        operatorsTTPair.put("&&", new TTypeValuePair(TokenType.ANDAND, "&&"));
        operatorsTTPair.put("^^", new TTypeValuePair(TokenType.XORXOR, "^^"));
        operatorsTTPair.put("|>", new TTypeValuePair(TokenType.PIPE, "|>"));
        operatorsTTPair.put("->", new TTypeValuePair(TokenType.RIGHTSLIMARROW, "->"));
        operatorsTTPair.put(";", new TTypeValuePair(TokenType.SEMICOLON, ";"));
        operatorsTTPair.put("(", new TTypeValuePair(TokenType.LPAREN, "("));
        operatorsTTPair.put(")", new TTypeValuePair(TokenType.RPAREN, ")"));
        operatorsTTPair.put("[", new TTypeValuePair(TokenType.LSQUAREPAREN, "["));
        operatorsTTPair.put("]", new TTypeValuePair(TokenType.RSQUAREPAREN, "]"));
        operatorsTTPair.put("{", new TTypeValuePair(TokenType.LFIGURE, "{"));
        operatorsTTPair.put("}", new TTypeValuePair(TokenType.RFIGURE, "}"));
    }

    public List<Token> tokenize(String input){
        List<Token> res = new ArrayList<>();
        this.input = input;

        currentChar = get(0);

        while(currentChar != '\0'){
            switch (currentChar) {
                case '\n':
                    line++;
                    advance();
                    break;
                case '"':
                    res.add(tokenizeString());
                    break;
                default:
                    if(Character.isWhitespace(currentChar)){
                        skipWhitespaces();
                    }
                    else if (operators.indexOf(currentChar) != -1){
                        String s = "";
                        s += currentChar;
                        if(operators.indexOf(get(1)) != -1){
                            advance();
                            s += currentChar;
                        }
                        res.add(new Token(operatorsTTPair.get(s), line));
                        advance();
                    }
                    else if(Character.isDigit(currentChar)){
                        res.add(tokenizeNumber());
                    }
                    else if(Character.isAlphabetic(currentChar)){
                        res.add(tokenizeKeyWord());
                    }
                    else {
                        throw new RuntimeException("Unknown token " + currentChar);
                    }
                    break;
            }
        }
        res.add(new Token(TokenType.EOF, "\0", line));

        return res;
    }

    private char get(int offset){
        int position = pos + offset;
        if (position >= input.length()){
            return '\0';
        }
        return input.charAt(position);
    }

    private void advance(){
        pos++;
        currentChar = get(0);
    }

    private Token tokenizeString(){
        StringBuilder sb = new StringBuilder();
        advance();
        while(currentChar != '"'){
            sb.append(currentChar);
            advance();
        }
        advance();
        return new Token(TokenType.STRING_LITERAL, sb.toString(), line);
    }

    private void skipWhitespaces(){
        if(currentChar == '\n'){
            line++;
        }
        while(Character.isWhitespace(currentChar)){
            advance();
            if(currentChar == '\n'){
                line++;
                advance();
            }
        }
    }

    private Token tokenizeNumber(){
        StringBuilder sb = new StringBuilder();

        while(Character.isDigit(currentChar)){
            sb.append(currentChar);
            advance();
        }

        return new Token(TokenType.NUMBER_LITERAL, sb.toString(), line);
    }

    private Token tokenizeKeyWord(){
        StringBuilder sb = new StringBuilder();

        while(Character.isAlphabetic(currentChar)){
            sb.append(currentChar);
            advance();
        }

        switch (sb.toString()){
            case "print": return new Token(TokenType.PRINT, "print", line);
            case "void": return new Token(TokenType.VOID, "void", line);
            case "byte": return new Token(TokenType.BYTE, "byte", line);
            case "short": return new Token(TokenType.SHORT, "short", line);
            case "int": return new Token(TokenType.INT, "int", line);
            case "long": return new Token(TokenType.LONG, "long", line);
            case "float": return new Token(TokenType.FLOAT, "float", line);
            case "double": return new Token(TokenType.DOUBLE, "double", line);
            case "string": return new Token(TokenType.STRING, "string", line);
            case "bool": return new Token(TokenType.BOOL, "bool", line);
            case "char": return new Token(TokenType.CHAR, "char", line);
            case "if": return new Token(TokenType.IF, "if", line);
            case "else": return new Token(TokenType.ELSE, "else", line);
            case "for": return new Token(TokenType.FOR, "for", line);
            case "while": return new Token(TokenType.WHILE, "while", line);
            case "repeat": return new Token(TokenType.REPEAT, "repeat", line);
            case "break": return new Token(TokenType.BREAK, "break", line);
            case "continue": return new Token(TokenType.CONTINUE, "continue", line);
            case "switch": return new Token(TokenType.SWITCH, "switch", line);
            case "const": return new Token(TokenType.CONST, "const", line);
            case "fn": return new Token(TokenType.FN, "fn", line);
            case "return": return new Token(TokenType.RETURN, "return", line);
            case "class": return new Token(TokenType.CLASS, "class", line);
            case "get": return new Token(TokenType.GET, "get", line);
            case "set": return new Token(TokenType.SET, "set", line);
            case "public": return new Token(TokenType.PUBLIC, "public", line);
            case "private": return new Token(TokenType.PRIVATE, "private", line);
            case "static": return new Token(TokenType.STATIC, "static", line);
            default: return new Token(TokenType.NAME, sb.toString(), line);
        }
    }
}
