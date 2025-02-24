package lexer;

public class Token {
    private final TTypeValuePair typeValuePair;
    private final int line;

    public Token(TokenType type, String value, int line){
        this.typeValuePair = new TTypeValuePair(type, value);
        this.line = line;
    }

    public Token(TTypeValuePair typeValuePair, int line){
        this.typeValuePair = typeValuePair;
        this.line = line;
    }

    public TokenType getType() {
        return typeValuePair.getType();
    }

    public String getValue() {
        return typeValuePair.getValue();
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", typeValuePair.getType(), typeValuePair.getValue(), line);
    }
}
