package com.company;

import java.io.IOException;

public class NumberParser {

    private Token currentToken = null;
    public NumberLexer lexer;

    public NumberParser(NumberLexer lexer) {
        this.lexer = lexer;
    }

    public void eat(Type type) {
        if (currentToken.type == type) {
            try {
                currentToken = lexer.getToken();
            } catch (Exception ex) {
                Error("unexpected token " + type);
            }
        } else {
            Error("unexpected token " + type);
        }
    }

    public void Error(String msg) {
        System.out.println(msg);
        System.exit(0);
    }

    public Token getNextToken() {
        if (currentToken == null) {
            try {
                currentToken = lexer.getToken();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return currentToken;
    }

    public void S() {
        Token token = getNextToken();
        switch (token.type) {
            case MINUS, NUM, ZERO -> {
                N();
                eat(Type.EOF);
            }
            default -> Error("unexpected token " + token.type);

        }
    }

    public void I() {
        Token token = getNextToken();

        switch (token.type) {
            case MINUS -> {
                eat(Type.MINUS);
                OI();
            }
            case ZERO -> ZD();
            case NUM -> {
                O();
                Ds();
            }
            default -> Error("unexpected token " + token.type);

        }
    }

    public void N() {
        Token token = getNextToken();

        switch (token.type) {
            case MINUS, NUM, ZERO -> {
                I();
                F();
            }
            default -> Error("unexpected token " + token.type);

        }
    }

    public void OI() {
        Token token = getNextToken();

        switch (token.type) {
            case ZERO -> ZD();
            case NUM -> {
                O();
                Ds();
            }
            default -> Error("unexpected token " + token.type);

        }
    }

    public void Ds() {
        Token token = getNextToken();

        switch (token.type) {
            case ZERO, NUM -> {
                D();
                OD();
            }

            default -> Error("unexpected token " + token.type);

        }
    }

    public void OD() {
        Token token = getNextToken();

        switch (token.type) {
            case EOF -> {
            }
            case ZERO, DOT, NUM -> Ds();
            default -> Error("unexpected token " + token.type);

        }
    }

    public void D() {
        Token token = getNextToken();

        switch (token.type) {
            case ZERO -> ZD();
            case NUM -> O();
            default -> Error("unexpected token " + token.type);

        }
    }

    public void ZD() {
        Token token = getNextToken();

        switch (token.type) {
            case ZERO -> eat(Type.ZERO);

            default -> Error("unexpected token " + token.type);

        }
    }

    public void O() {
        Token token = getNextToken();

        switch (token.type) {
            case NUM -> eat(Type.NUM);
            default -> Error("unexpected token " + token.type);

        }
    }

    public void F() {
        Token token = getNextToken();

        switch (token.type) {
            case EOF -> {
            }
            case DOT -> {
                eat(Type.DOT);
                Ds();
            }
            default -> Error("unexpected token " + token.type);

        }
    }
}
