/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator.lexer;

import java.util.HashMap;

/**
 *
 * @author pkd
 */
public class Values {
    
    public static HashMap<String, Boolean> values;
    
    public static void init(){
        values = new HashMap<>();
    }
    
    public static void initList(String[] keys, boolean ... val){
        init();
        for(int i = 0; i < keys.length; i++){
            values.put(keys[i], val[i]);
        }
    }
}
