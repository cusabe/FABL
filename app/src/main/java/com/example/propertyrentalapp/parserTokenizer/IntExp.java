package com.example.propertyrentalapp.parserTokenizer;

public class IntExp extends Exp {

    private Integer value;

    public IntExp(Integer value) {
        this.value = value;
    }

    @Override
    public String show() {
        return value.toString();
    }

    @Override
    public String eqOperator(){
        return "";
    }

    @Override
    public int getInt() {
        return 0;
    }

    @Override
    public String getVar() {
        return "";
    }
}
