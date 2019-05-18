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
public class Main {
    
    public static void main(String[] args) {
//        String str = "a&b|!c&!(b&d)";
//        System.out.println(str);
//        
//        System.out.println("=====");
//        ArrayList<Token> tokens = Parser.parse(str).tokens;
//        for(Token t : tokens){
//            System.out.println(t);
//        }
//        
//        System.out.println("=====");
//        ExpressionTerm exp = (ExpressionTerm) new Lexer().lexer(tokens);
//        exp.data = new SetData(new String[]{"a", "b", "c", "d"}, 
//                               new int[]{0, 0, 0, 0});
//        
//        System.out.println("=====");
//        exp.data.set(new int[]{0, 0, 0, 0});
//        for(int i : exp.data.toInt())
//            System.out.print(i + " ");
//        System.out.println();
//        System.out.println(exp.run());
//        
//        System.out.println("=====");
//        exp.visitor(vis);
//        System.out.println(vis.list.size());
//        for(Visitor.ViewVisitor view : vis.list){
//            System.out.print(view.text + " = " + view.result + " ||| ");
//        }
        System.out.println();
        
        MainFrame frame = new MainFrame("Калькулятор логических операций");
        frame.setVisible(true);
//        num = 0;
//        mess(0, exp.data.key.length, exp.data, () -> {
//            for(int b : exp.data.toInt()){
//                System.out.print(b + " | ");
//            }
//            System.out.println("");
//            System.out.println(exp.run());
//            System.out.println("-------");
//            num++;
//        });
//        System.out.println("===== " + num);
    }
    
//    public static int num = 0;
//    public static void mess(int b, int end, SetData data, Runnable run){
//        if(end <= b)
//            return;
//        mess(b, end - 1, data, run);
//        for(int i = b; i < end; i++){
//            run.run();
//            data.val[i] = !data.val[i];
//        }
//        mess(b+1, end, data, run);
//    }
}
