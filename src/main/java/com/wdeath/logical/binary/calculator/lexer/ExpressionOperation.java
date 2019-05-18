/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.lexer;

import com.wdeath.logical.binary.calculator.parse.Token.TokenType;

/**
 *
 * @author pkd
 */
public class ExpressionOperation extends Expression{
    
    public Expression exp1;
    public TokenType operation;
    public Expression exp2 = null;

    @Override
    public boolean run() {
        switch(operation){
            case AND: return exp1.run() & exp2.run();
            case OR: return exp1.run() | exp2.run();
            case NOT: return !exp1.run();
            case IMPLICATION: return implication();
            case EQUIVALENCE: return equavalence();
        }
        return true;
    }
    
    public boolean implication(){
        boolean a = exp1.run();
        boolean b = exp2.run();
        if(a == true && b == false)
            return false;
        return true;
    }
    
    public boolean equavalence(){
        boolean a = exp1.run();
        boolean b = exp2.run();
        if(a == b)
            return true;
        return false;
    }

    @Override
    public Visitor visitor(Visitor v) {
        if(operation == TokenType.NOT){
            v.visit(new Visitor.ViewVisitor(String.valueOf(exp1.visitor(v)).toString() + "" + operation.toString(), run()));
        }else{
            v.visit(new Visitor.ViewVisitor(String.valueOf(exp1.visitor(v).toString() + " " + operation + " " + exp2.visitor(v).toString()), run()));
        }
        return v;
    }
    
}
