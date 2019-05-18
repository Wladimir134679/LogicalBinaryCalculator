/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.lexer;

import com.wdeath.logical.binary.calculator.parse.Token;

/**
 *
 * @author pkd
 */
public class ExpressionText extends Expression{

    public Token token;

    public ExpressionText() {
    }

    public ExpressionText(Token token) {
        this.token = token;
    }
    
    public boolean run() {
        return Values.values.get(token.text);
    }

    @Override
    public Visitor visitor(Visitor v) {
        v.visit(new Visitor.ViewVisitor(String.valueOf(token.text), run()));
        return v;
    }
}
