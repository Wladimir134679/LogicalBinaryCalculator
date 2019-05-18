/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.lexer;

import com.wdeath.logical.binary.calculator.SetData;

/**
 *
 * @author pkd
 */
public class ExpressionTerm extends Expression{

    public Expression exp;
    public SetData data;
    
    @Override
    public boolean run() {
        Values.initList(data.key, data.val);
        return exp.run();
    }

    @Override
    public Visitor visitor(Visitor v) {
        v.visit(new Visitor.ViewVisitor(exp.visitor(v).toString(), run()));
        return v;
    }
    
}
