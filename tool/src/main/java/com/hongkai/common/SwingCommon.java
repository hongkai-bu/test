package com.hongkai.common;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwingCommon {
    public static JButton changeIconSize(JButton button, String pictureName, int width, int height, String tip){
        button.setBounds(0,0,width,height);
        ImageIcon buttonImg=new ImageIcon(ClassLoader.getSystemResource("png").getPath() + "/"+pictureName);
        //改变图片的大小
        Image temp=buttonImg.getImage().getScaledInstance(button.getWidth(), button.getHeight(), buttonImg.getImage().SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp));
        button.setIcon(buttonImg);
        button.setToolTipText(tip);    //提示
        return button;
    }

    public static JButton changeIconSize(JButton button, String pictureName, String tip){
        ImageIcon buttonImg=new ImageIcon(ClassLoader.getSystemResource("png").getPath() + "/"+pictureName);
        button.setIcon(buttonImg);
        button.setToolTipText(tip);    //提示
        return button;
    }

    public static JTextArea changeBorder(JTextArea jTextArea){
        Border border = BorderFactory.createLineBorder(new Color(124, 131, 135));
        jTextArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        return jTextArea;
    }

    public static JComponent changeFont(JComponent jComponent,Integer size){
        Font font = new Font("微软雅黑", Font.BOLD, size);
        jComponent.setFont(font);
        return jComponent;
    }
}
