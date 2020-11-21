package com.example.propertyrentalapp.parserTokenizer;

public class VarExp extends Exp {

    private String value;

    public VarExp(String value) {
        this.value = value;
    }

    @Override
    public String show() {
        return value;
    }

    @Override
    public String eqOperator(){
        return "";
    }

    @Override
    public int getInt() {
        return 1;
    }

    @Override
    public String getVar() {
        return "";
    }

}
