package com.example.propertyrentalapp.parserTokenizer;

public class Parser {

    MyTokenizer _tokenizer;

    public Parser(MyTokenizer tokenizer) {
        _tokenizer = tokenizer;
    }

    // <condition> = <var> < <number> | <var> > <number> |  <var> = <number> | sort by <var>

    public Exp parseRange() {

        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.SEMICOLON){
            _tokenizer.next();
            if (!_tokenizer.hasNext()) return null;
        }

        Exp term = parseVar();

        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.GT){
            _tokenizer.next();
            Exp exp = parseInt();
            return new GreaterExp(term, exp);
        } else if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.LT){
            _tokenizer.next();
            Exp exp = parseInt();
            return new LessExp(term, exp);
        } else if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.EQ){
            _tokenizer.next();
            Exp exp = parseInt();
            return new EqExp(term, exp);
        } else {
            return term;
        }

    }

    // <var> = price | quality | distance | people | nights

    public Exp parseVar() {

        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.VARIABLE){
            Exp varExp = new VarExp(_tokenizer.current().token());
            _tokenizer.next();
            return varExp;
        } else {
            _tokenizer.next();
            Exp exp = parseInt();
            _tokenizer.next();
            return exp;
        }
    }


    public Exp parseInt() {

        if(_tokenizer.hasNext() && _tokenizer.current().type() == Token.Type.INT){
            Exp intExp = new IntExp(Integer.parseInt(_tokenizer.current().token()));
            _tokenizer.next();
            return intExp;
        } else {
            _tokenizer.next();
            Exp exp = parseRange();
            _tokenizer.next();
            return exp;
        }
    }

}
