/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.parse;

import com.wdeath.logical.binary.calculator.parse.Token.TokenType;
import java.util.ArrayList;

/**
 *
 * @author pkd
 */
public class Parser {
 
    
    public String str;
    public ArrayList<Token> tokens;
    public int length;
    public int pos;
    
    public Parser(String str){
        pos = 0;
        length = str.length();
        this.str = str;
        tokens = new ArrayList<>();
    }
    
    public static Parser parse(String str){
        Parser parse = new Parser(str);
        parse.parse();
        return parse;
    }
    
    public ArrayList<Token> parse(){
        char current = ' ';
        while(current != '\0'){
            current = get(0);
            if(isText(current)){
                parseText();
            }else if(isOperation(current)){
                parseOperation();
            }else if(isNumber(current)){
                parseNumber();
            }else{
                math();
            }
        }
        return tokens;
    }
    
    public void parseNumber(){
        char c = get(0);
        Token token = new Token(TokenType.VALUE);
        token.text = String.valueOf(c);
        math();
        tokens.add(token);
    }
    
    public void parseOperation(){
        char c = get(0);
        Token token = new Token();
        if(Token.TokenType.AND.opreator.equals(String.valueOf(c))){
            token.type = Token.TokenType.AND;
        }
        if(Token.TokenType.OR.opreator.equals(String.valueOf(c))){
            token.type = Token.TokenType.OR;
        }
        if(Token.TokenType.LBRACET.opreator.equals(String.valueOf(c))){
            token.type = Token.TokenType.LBRACET;
        }
        if(Token.TokenType.RBRACET.opreator.equals(String.valueOf(c))){
            token.type = Token.TokenType.RBRACET;
        }
        if(Token.TokenType.NOT.opreator.equals(String.valueOf(c))){
            token.type = Token.TokenType.NOT;
        }
        if(c == '-'){
            String str = String.valueOf(c +""+ get(1));
            if(Token.TokenType.IMPLICATION.opreator.equals(String.valueOf(str))){
                token.type= Token.TokenType.IMPLICATION;
                math();
            }
        }
        if(c == '<'){
            String str = String.valueOf(c +""+ get(1) +""+ get(2));
            if(Token.TokenType.EQUIVALENCE.opreator.equals(String.valueOf(str))){
                token.type= Token.TokenType.EQUIVALENCE;
                math();
                math();
            }
        }
        math();
        tokens.add(token);
    }
    
    public void parseText(){
        char c = getCharPos();
        StringBuilder builder = new StringBuilder();
        while(isText(c)){
            builder.append(c);
            c = math();
        }
        Token tok = new Token();
        tok.text = builder.toString();
        tok.type = Token.TokenType.TEXT;
        tokens.add(tok);
    }
    
    public boolean isText(char c){
        return Character.isLetter(c);
    }
    
    public boolean isOperation(char c){
        return "&|()!-><".indexOf(c) != -1;
    }
    
    public boolean isNumber(char c){
        if(c == '1' || c == '0')
            return true;
        return false;
    }
    
    public char math(){
        pos++;
        return getCharPos();
    }
    
    public char getCharPos(){
        return get(0);
    }
    
    public char get(int i){
        if(pos + i >= length){
            return '\0';
        }else{
            return str.charAt(pos + i);
        }
    }
}
