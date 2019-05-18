/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.lexer;

/**
 *
 * @author pkd
 */
public class ExpressionValue extends Expression
{
    
    boolean value;

    public ExpressionValue() {
    }

    public ExpressionValue(boolean value) {
        this.value = value;
    }

    @Override
    public boolean run() {
        return value;
    }

    @Override
    public Visitor visitor(Visitor v) {
        v.visit(new Visitor.ViewVisitor(String.valueOf(value), run()));
        return v;
    }
    
}
