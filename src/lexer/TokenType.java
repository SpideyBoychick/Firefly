package lexer;

public enum TokenType {

    //Operators
    PLUS,// +
    MINUS,// -
    MUL,// *
    DIV,// /
    POW,// ^

    ASIGN,// =

    EQEQ,// ==
    NOTEQ,// !=
    LOWER,// <
    HIGHER,// >
    LOWEREQ,// <=
    HIGHEREQ,// >=

    PLUSPLUS,// ++
    MINUSMINUS,// --

    PLUSEQ,// +=
    MINUSEQ,// -=
    MULEQ,// *=
    DIVEQ,// /=
    POWEQ,// ^=

    NOT,// !
    OROR,// ||
    ANDAND,// &&
    XORXOR,// ^^

    PIPE,// |>
    RIGHTSLIMARROW,// ->

    SEMICOLON,// ;

    LPAREN,// (
    RPAREN,// )
    LSQUAREPAREN,// [
    RSQUAREPAREN,// ]
    LFIGURE,// {
    RFIGURE,// }

    //Literals
    NUMBER_LITERAL,
    STRING_LITERAL,
    CHAR_LITERAL,

    //Data types
    VOID,
    BYTE,
    SHORT,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    STRING,
    BOOL,
    CHAR,

    //Keywords
    PRINT,
    IF,
    ELSE,
    FOR,
    WHILE,
    REPEAT,
    BREAK,
    CONTINUE,
    SWITCH,
    CONST,
    FN,
    RETURN,
    CLASS,
    GET,
    SET,
    PUBLIC,
    PRIVATE,
    STATIC,

    NAME,
    EOF
}
