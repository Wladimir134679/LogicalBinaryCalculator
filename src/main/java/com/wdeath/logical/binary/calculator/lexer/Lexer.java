/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.lexer;

import com.wdeath.logical.binary.calculator.SetData;
import com.wdeath.logical.binary.calculator.parse.Token;

import java.util.List;

/**
 *
 * @author pkd
 */
public class Lexer {
    
    public static final Token END = new Token(Token.TokenType.END);
    
    public List<Token> tokens;
    public int pos;
    public int length;
    
    public Expression lexer(List<Token> tokens){
        pos = 0;
        this.tokens = tokens;
        length = tokens.size();
        Token current = tokens.get(0);
        
        ExpressionTerm term = new ExpressionTerm();
        while(current.type != END.type){
            term.exp = equival();
            current = get(0);
        }
        return term;
    }
    
    public Expression equival(){
        Expression exp1 = implication();
        while(true){
            if(mathc(Token.TokenType.EQUIVALENCE)){
                ExpressionOperation op1 = new ExpressionOperation();
                op1.operation = Token.TokenType.EQUIVALENCE;
                op1.exp1 = exp1;
                op1.exp2 = implication();
                exp1 = op1;
                continue;
            }
            break;
        }
        return exp1;
    }
    
    public Expression implication(){
        Expression exp1 = or();
        while(true){
            if(mathc(Token.TokenType.IMPLICATION)){
                ExpressionOperation op1 = new ExpressionOperation();
                op1.operation = Token.TokenType.IMPLICATION;
                op1.exp1 = exp1;
                op1.exp2 = or();
                exp1 = op1;
                continue;
            }
            break;
        }
        return exp1;
    }
    
    public Expression or(){
        Expression exp1 = and();
        while(true){
            if(mathc(Token.TokenType.OR)){
                ExpressionOperation op1 = new ExpressionOperation();
                op1.operation = Token.TokenType.OR;
                op1.exp1 = exp1;
                op1.exp2 = and();
                exp1 = op1;
                continue;
            }
            break;
        }
        return exp1;
    }
    
    public Expression and(){
        Expression exp1 = denial();
        while(true){
            if(mathc(Token.TokenType.AND)){
                ExpressionOperation op1 = new ExpressionOperation();
                op1.operation = Token.TokenType.AND;
                op1.exp1 = exp1;
                op1.exp2 = denial();
                exp1 = op1;
                continue;
            }
            break;
        }
        return exp1;
    }
    
    public Expression denial(){
        Expression exp1 = bracet();
        while(true){
            if(mathc(Token.TokenType.NOT)){
                ExpressionOperation op1 = new ExpressionOperation();
                op1.operation = Token.TokenType.NOT;
                op1.exp1 = denial();
                exp1 = op1;
                continue;
            }
            break;
        }
        return exp1;
    }
    
    public Expression bracet(){
        if(mathc(Token.TokenType.LBRACET)){
            Expression exp = equival();
            mathc(Token.TokenType.RBRACET);
            return exp;
        }
        return value();
    }
    
    public Expression value(){
        if(mathc(Token.TokenType.TEXT)){
            return new ExpressionText(tokens.get(pos - 1));
        }
        if(mathc(Token.TokenType.VALUE)){
            String str = tokens.get(pos - 1).text;
            return new ExpressionValue(SetData.convert((str.equals("0") ? 0 : 1)));
        }
        return null;
    }
    
    public boolean mathc(Token token){
        return mathc(token.type);
    }
    
    public boolean mathc(Token.TokenType token){
        Token tok = get(0);
        if(tok.type != token)
            return false;
        pos++;
        return true;
    }
    
    public Token get(int i){
        if(pos + i >= length) return END;
        else return tokens.get(pos + i);
    }
}
