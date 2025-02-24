package lexer;

public class TTypeValuePair {
    private final TokenType type;
    private final String value;

    public TTypeValuePair(TokenType type, String value){
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
