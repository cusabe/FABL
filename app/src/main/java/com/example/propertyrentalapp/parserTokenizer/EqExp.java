package com.example.propertyrentalapp.parserTokenizer;

public class EqExp extends Exp {

    private Exp term;
    private Exp exp;

    public EqExp(Exp term, Exp exp) {
        this.term = term;
        this.exp = exp;
    }

    @Override
    public String show() {
        return term.show() + " = " + exp.show();
    }

    @Override
    public String eqOperator(){
        return "=";
    }

    public String getVar(){
        return term.show();
    }

    public int getInt(){
        return Integer.parseInt(exp.show());
    }

}