/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.parse;

/**
 *
 * @author pkd
 */
public class Token {
    
    public static enum TokenType{
        TEXT, VALUE, OR("|"), AND("&"), NOT("!"), LBRACET("("), RBRACET(")"), END, IMPLICATION("->"), EQUIVALENCE("<->");
        String opreator = "";

        private TokenType() {
        }

        private TokenType(String opreator) {
            this.opreator = opreator;
        }
    }
    
    public String text = "";
    public TokenType type;

    public Token() {
    }

    public Token(TokenType type) {
        this.type = type;
    }
    

    @Override
    public String toString() {
        return type + " " + text;
    }
}
