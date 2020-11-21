package com.example.propertyrentalapp.parserTokenizer;

public abstract class Tokenizer {

    public abstract boolean hasNext();

    public abstract Token current();

    public abstract void next();
}
