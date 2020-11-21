package com.example.propertyrentalapp.parserTokenizer;

public class Token {

    public enum Type {UNKNOWN, VARIABLE, INT, GT, LT, EQ, SEMICOLON};
    private String _token = "";
    private Type _type = Type.UNKNOWN;

    public Token(String token, Type type) {
        _token = token;
        _type = type;
    }

    public String token() {
        return _token;
    }

    public Type type() {
        return _type;
    }
}
