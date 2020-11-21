package com.example.propertyrentalapp.parserTokenizer;

public class LessExp extends Exp {

    private Exp term;
    private Exp exp;

    public LessExp(Exp term, Exp exp) {
        this.term = term;
        this.exp = exp;
    }

    @Override
    public String show() {
        return term.show() + " < " + exp.show();
    }

    @Override
    public String eqOperator(){
        return "<";
    }

    @Override
    public String getVar(){
        return term.show();
    }

    @Override
    public int getInt(){
        return Integer.parseInt(exp.show());
    }

}