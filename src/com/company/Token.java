package com.company;

public class Token {
    String text;
    int begin, end;
    Type type;

    public Token(Type type, String text) {
        this.type = type;
        this.text = text;
        this.end = end;
        this.begin = begin;
    }

    public String toString() {
        return String.format("[%s , %s]", type, text);
    }
}
