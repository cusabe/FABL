package com.example.propertyrentalapp.parserTokenizer;

import com.example.propertyrentalapp.parserTokenizer.Token;
import com.example.propertyrentalapp.parserTokenizer.Tokenizer;

public class MyTokenizer extends Tokenizer {

    private String _buffer;		//save text
    private Token currentToken;	//save token extracted from next()

    public MyTokenizer(String text) {
        _buffer = text;		// save input text (string)
        next();		// extracts the first token.
    }


    public void next() {

        _buffer = _buffer.trim(); // remove whitespace

        if(_buffer.isEmpty()) {
            currentToken = null;	// if there's no string left, set currentToken null and return
            return;
        }

        char firstChar = _buffer.charAt(0);

        if(firstChar == '='){
            currentToken = new Token("=", Token.Type.EQ);
        } else if(firstChar == '>') {
            currentToken = new Token(">", Token.Type.GT);
        } else if(firstChar == '<'){
            currentToken = new Token("<", Token.Type.LT);
        } else if(firstChar == ';'){
            currentToken = new Token(";", Token.Type.SEMICOLON);
        }
        else if(Character.isLetter(firstChar)){
            int end = 1;
            while(end<_buffer.length() && Character.isLetter(_buffer.charAt(end))){
                end++;
            }
            String subString = _buffer.substring(0,end);
            currentToken = new Token(subString, Token.Type.VARIABLE);
        } else if(Character.isDigit(firstChar)){
            int end = 1;
            while(end<_buffer.length() && Character.isDigit(_buffer.charAt(end))){
                end++;
            }
            String subString = _buffer.substring(0,end);
            currentToken = new Token(subString, Token.Type.INT);

        }

        // Remove the extracted token from buffer
        int tokenLen = currentToken.token().length();
        _buffer = _buffer.substring(tokenLen);
    }

    public Token current() {
        return currentToken;
    }

    public boolean hasNext() {
        return currentToken != null;
    }
}