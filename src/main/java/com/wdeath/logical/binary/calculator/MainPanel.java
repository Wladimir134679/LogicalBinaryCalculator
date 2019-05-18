/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wdeath.logical.binary.calculator;

import com.wdeath.logical.binary.calculator.lexer.Expression;
import com.wdeath.logical.binary.calculator.lexer.ExpressionTerm;
import com.wdeath.logical.binary.calculator.lexer.Lexer;
import com.wdeath.logical.binary.calculator.parse.Parser;
import com.wdeath.logical.binary.calculator.parse.Token;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author pkd
 */
public class MainPanel extends JPanel{
    
    public static final int WIDTH = 600, HEIGHT = 400;
    
    public JTextField textExpression;
    public JLabel infoValues;
    public HashMap<String, Integer> values;
    
    public JComboBox<String> listValues;
    public JTextField fieldValue;
    
    public JLabel resultInfo;
    
    public MainPanel(){
        super();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        values = new HashMap<>();
        
        textExpression = new JTextField();
        textExpression.setPreferredSize(new Dimension(WIDTH - 20, 30));
        textExpression.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String str = "";
                ArrayList<String> keys = Expression.getValues(textExpression.getText());
                listValues.removeAllItems();
                for(String v : keys){
                    if(values.get(v) == null)
                        values.put(v, 0);
                    str += v + " ";
                    listValues.addItem(v);
                }
                infoValues.setText(str);
            }
        });
        this.add(textExpression);
        
        JPanel panelViewValues = new JPanel();
        panelViewValues.setPreferredSize(new Dimension(WIDTH - 20, 40));
        panelViewValues.setBorder(new LineBorder(new Color(200, 200, 200, 200)));
        panelViewValues.setLayout(new FlowLayout(FlowLayout.LEFT));
        {
            JLabel labelInfo = new JLabel("Все переменные выражения: ");
            labelInfo.setPreferredSize(new Dimension(200, 30));
            panelViewValues.add(labelInfo);
            
            infoValues = new JLabel();
            infoValues.setPreferredSize(new Dimension(100, 30));
            panelViewValues.add(infoValues);
        }
        this.add(panelViewValues);
        
        JPanel panelView = new JPanel();
        panelView.setPreferredSize(new Dimension(WIDTH - 20, 40));
        panelView.setBorder(new LineBorder(new Color(200, 200, 200, 200)));
        panelView.setLayout(new FlowLayout(FlowLayout.LEFT));
        {
            JLabel infoPanel = new JLabel("Значения переменных выражения: ");
            panelView.add(infoPanel);
            
            listValues = new JComboBox<>();
            listValues.setPreferredSize(new Dimension(100, 30));
            listValues.addItemListener((e) -> {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    int i = values.get(e.getItem());
                    fieldValue.setText(String.valueOf(i));
                }
            });
            panelView.add(listValues);
            
            fieldValue = new JTextField();
            fieldValue.setPreferredSize(new Dimension(100, 30));
            fieldValue.addKeyListener(new KeyListener() {
                @Override
                public void keyTyped(KeyEvent e) {
                    char c = e.getKeyChar();
                    if(c != '0' && c != '1')
                        e.consume();
                    else{
                        fieldValue.setText(String.valueOf(c));
                        values.put((String)listValues.getSelectedItem(), Integer.parseInt(fieldValue.getText()));
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {}

                @Override
                public void keyReleased(KeyEvent e) {
                    
                }
            });
            panelView.add(fieldValue);
        }
        this.add(panelView);
        
        JPanel panelResult = new JPanel();
        panelResult.setPreferredSize(new Dimension(WIDTH - 20, 40));
        panelResult.setBorder(new LineBorder(new Color(200, 200, 200, 200)));
        panelResult.setLayout(new FlowLayout(FlowLayout.LEFT));
        {
            JButton br = new JButton("Посчитать");
            br.setPreferredSize(new Dimension(150, 30));
            br.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    resultInfo.setText("Считаем...");
                    String[] ks = new String[values.size()];
                    int[] vs = new int[values.size()];
                    int i = 0;
                    for(Entry<String, Integer> kv : values.entrySet()){
                        ks[i] = kv.getKey();
                        vs[i] = kv.getValue();
                        i++;
                    }
                    Thread t = new Thread(){
                        @Override
                        public void run() {
                            ArrayList<Token> tokens = Parser.parse(textExpression.getText()).parse();
                            System.out.println("==== Tokens ====");
                            for(Token t : tokens){
                                System.out.println(t);
                            }
                            System.out.println("================");
                            ExpressionTerm term = (ExpressionTerm)new Lexer().lexer(tokens);
                            term.data = new SetData(ks, vs);
                            boolean res = term.run();
                            resultInfo.setText(String.valueOf(SetData.convert(res)));
                        }
                    };
                    t.start();
                }
            });
            panelResult.add(br);
            
            JLabel info = new JLabel("Результат: ");
            info.setPreferredSize(new Dimension(75, 30));
            panelResult.add(info);
            
            resultInfo = new JLabel();
            resultInfo.setPreferredSize(new Dimension(75, 30));
            panelResult.add(resultInfo);
            
        }
        this.add(panelResult);
        
        JPanel helpPanel = new JPanel();
        helpPanel.setPreferredSize(new Dimension(WIDTH - 20, 200));
        helpPanel.setBorder(new LineBorder(new Color(200, 200, 200, 200)));
        helpPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        {
            String str = "";
            str = "<html><pre>Помощь по операторам <br>"
                    + " &   - конъюнкция(логическое умножение) <br>"
                    + " |   - дизъюнкция(логическое сложение) <br>"
                    + " !   - отрицание <br>"
                    + " ->  - имплекация(&#8594;) <br>"
                    + " &#60;-> - эквивалентность(&#8596;)<br>"
                    + " Так же поддерживания скобок - ()<br>"
                    + "</pre></html>";
            
            JLabel label = new JLabel(str);
            helpPanel.add(label);
            
        }
        this.add(helpPanel);
    }
}
