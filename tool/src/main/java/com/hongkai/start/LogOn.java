package com.hongkai.start;

import javax.swing.*;
import java.awt.*;

public class LogOn {
    private JPanel jpanellogon;
    private JButton Button1;
    private JButton Button2;
    private JTextField textField1;
    private JPasswordField passwordField1;

    public JFrame frameLogOn;
    public LogOn(){
        jpanellogon.setOpaque(false);

        frameLogOn=new JFrame("新增");
        ImageIcon imageIcon1=new ImageIcon(ClassLoader.getSystemResource("png").getPath()+"/add.png");

        frameLogOn.setIconImage(imageIcon1.getImage());
        frameLogOn.setContentPane(jpanellogon);
        frameLogOn.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogOn.pack();
        frameLogOn.setResizable(false);
        frameLogOn.setVisible(true);
        frameLogOn.setSize(400,300);
        frameLogOn.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new LogOn();
    }

}
