/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator;

/**
 *
 * @author pkd
 */
public class SetData {
    
    public String[] key;
    public boolean[] val;

    public SetData() {
    }

    public SetData(String[] key, boolean[] val) {
        this.key = key;
        this.val = val;
    }
    public SetData(String[] key, int[] val) {
        this.key = key;
        set(val);
    }
    
    public void set(String[] keys){
        this.key = keys;
    }
    
    public void set(boolean[] val){
        this.val = val;
    }
    
    public void set(int[] vals){
        val = new boolean[vals.length];
        for(int i = 0; i < val.length; i++){
            if(vals[i] == 0)
                val[i] = false;
            if(vals[i] == 1)
                val[i] = true;
        }
    }
    
    public int[] toInt(){
        int[] in = new int[val.length];
        for(int i = 0; i < val.length; i++){
            if(val[i])
                in[i] = 1;
            else
                in[i] = 0;
        }
        return in;
    }
    
    public static boolean convert(int i){
        if(i == 1)
            return true;
        else
            return false;
    }
    
    public static int convert(boolean v){
        if(v)
            return 1;
        else
            return 0;
    }
}
