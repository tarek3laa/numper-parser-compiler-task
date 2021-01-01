package com.company;

import java.io.StringReader;

public class Main {

    public static void main(String[] args) {
        String msg = "12+345";
        StringReader s = new StringReader(msg);

        NumberLexer lexer = new NumberLexer(s);
        NumberParser parser = new NumberParser(lexer);
        parser.S();
        System.out.println("Success");
    }
}
