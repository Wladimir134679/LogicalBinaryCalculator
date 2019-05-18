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
public abstract class Expression {
    
    public abstract boolean run();
    
    public abstract Visitor visitor(Visitor v);
    
    
    public static ArrayList<String> getValues(String v){
        ArrayList<String> values = new ArrayList<>();
        for(int i = 0; i < v.length(); i++){
            if(Character.isLetter(v.charAt(i))){
                values.add(String.valueOf(v.charAt(i)));
            }
        }
        for(int i = 0; i < values.size(); i++){
            String str = values.get(i);
            for(int j = i + 1; j < values.size(); j++){
                String str1 = values.get(j);
                if(str.equals(str1)){
                    values.remove(j);
                    j--;
                }
            }
        }
        return values;
    }
}
