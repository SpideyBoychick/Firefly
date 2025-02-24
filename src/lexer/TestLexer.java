package lexer;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestLexer {

    public static void main(String[] args) {
        String file;
        try {
            file = Files.readString(Paths.get("test.frfly"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        List<Token> tokens = new Lexer().tokenize(file);
        for (Token t : tokens){
            System.out.println(t.toString());
        }
    }
}
