package com.hongkai.start;


import com.hongkai.common.CommonConfig;
import com.hongkai.enums.ExcelName;
import com.hongkai.utils.ExcelUtils;
import com.hongkai.utils.FileUtils;
import com.hongkai.utils.SqliteUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;
import java.util.*;


public class Export extends JDialog{

    private JButton buttonAdd;
    private JPanel jpaneladd;
    private JButton buttonExcel;
    private JButton buttonCancel;
    private JLabel laberExcel;
    private JFileChooser fileChooserExcel;

    private Font font = new Font("微软雅黑", Font.BOLD, 14);
    private String  defaultDisk = "d:\\";
    private Map<String, Object> map;

    public Export(Frame owner, String title,Map<String, Object> amap){
        super(owner,title,true);
        setSize(470, 180);
        setResizable(false);
        setLocationRelativeTo(owner);

        map=amap;

        jpaneladd = new JPanel();
        jpaneladd.setLayout(null);

        buttonExcel = new JButton("导出文件夹");
        buttonExcel.setFont(font);
        buttonExcel.setBounds(30, 30, 130, 32);
        buttonExcel.setFocusPainted(false);
        jpaneladd.add(buttonExcel);

        laberExcel = new JLabel();
        laberExcel.setFont(new Font("微软雅黑", Font.PLAIN, 8));
        laberExcel.setBorder(BorderFactory.createLineBorder(new Color(124, 131, 135)));
        laberExcel.setBounds(180, 30, 250, 32);
        jpaneladd.add(laberExcel);


        buttonAdd= new JButton("确定");
        buttonAdd.setFont(font);
        buttonAdd.setBounds(30, 80, 130, 32);
        buttonAdd.setFocusPainted(false);
        jpaneladd.add(buttonAdd);

        buttonCancel= new JButton("取消");
        buttonCancel.setFont(font);
        buttonCancel.setBounds(300, 80, 130, 32);
        buttonCancel.setFocusPainted(false);
        jpaneladd.add(buttonCancel);

        // 设置对话框的内容面板
        setContentPane(jpaneladd);
        // 显示对话框
        event();
        setVisible(true);
    }

    File excelfile=null;

    private void event() {
        buttonExcel.addActionListener(e->{
            fileChooserExcel=new JFileChooser();
            fileChooserExcel.setDialogTitle("选择导出文件夹");
            fileChooserExcel.setCurrentDirectory(new File(defaultDisk));
            fileChooserExcel.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooserExcel.setMultiSelectionEnabled(false);
            fileChooserExcel.showOpenDialog(null);
            excelfile=fileChooserExcel.getSelectedFile();
            laberExcel.setText(excelfile.getPath());
        });


        buttonAdd.addActionListener((ActionEvent e)->{
            String filePath=excelfile.getPath();
            buttonAdd.setEnabled(false);
            try {
                String resultPath = ExcelUtils.exportBom(map,filePath);
                JOptionPane.showMessageDialog(jpaneladd, "导出路径:"+resultPath, "成功", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(jpaneladd, e1, "错误", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
            buttonAdd.setEnabled(true);
        });

        buttonCancel.addActionListener((ActionEvent e)->{
            this.dispose();
        });

    }
}

