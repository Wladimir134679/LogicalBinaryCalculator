/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator;

import javax.swing.JFrame;

/**
 *
 * @author pkd
 */
public class MainFrame extends JFrame{
    
    public MainPanel panel;
    
    public MainFrame(String title){
        super();
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new MainPanel();
        this.add(panel);
        this.pack();
    }
    
}
