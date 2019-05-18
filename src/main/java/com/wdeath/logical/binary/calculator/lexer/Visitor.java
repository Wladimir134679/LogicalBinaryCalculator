/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.lexer;

import java.util.ArrayList;

/**
 *
 * @author pkd
 */
public class Visitor {
    
    public ArrayList<ViewVisitor> list;

    public Visitor() {
        list = new ArrayList<>();
    }
    
    public void visit(ViewVisitor exp){
        list.add(exp);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        for(ViewVisitor v : list){
            b.append(v.text).append(" ");
        }
        return b.toString();
    }
    
    public static class ViewVisitor{
        
        public String text;
        public boolean result = true;

        public ViewVisitor() {
        }

        public ViewVisitor(String text, boolean v) {
            this.text = text;
            this.result = v;
        }
        
//        public ViewVisitor(Expression exp1, String op){
//            
//        }
//        
//        public ViewVisitor(Expression exp1, String op, Expression exp2){
//            
//        }
    }
}
