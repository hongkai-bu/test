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
import java.text.DecimalFormat;
import java.util.*;
import java.util.List;



public class Add2 extends JDialog{

    private JButton buttonAdd;
    private JPanel jpaneladd;
    private JProgressBar progressBar1;
    private JButton buttonExcel;
    private JButton buttonPicture;
    private JLabel laberExcel;
    private JLabel laberPicture;
    private JFileChooser fileChooserExcel;
    private JFileChooser fileChooserPicture;

    private Font font = new Font("微软雅黑", Font.BOLD, 14);
    private String  defaultDisk = "d:\\";
    private String pictureDir="D:\\DataBaseForNiHeWan\\picture";

    public Add2(Frame owner, String title){
        super(owner,title,true);
        setSize(470, 230);
        setResizable(false);
        setLocationRelativeTo(owner);

        jpaneladd = new JPanel();
        jpaneladd.setLayout(null);

        buttonExcel = new JButton("Excel文件");
        buttonExcel.setFont(font);
        buttonExcel.setBounds(30, 30, 130, 32);
        buttonExcel.setFocusPainted(false);
        jpaneladd.add(buttonExcel);

        laberExcel = new JLabel();
        laberExcel.setFont(new Font("微软雅黑", Font.PLAIN, 8));
        laberExcel.setBorder(BorderFactory.createLineBorder(new Color(124, 131, 135)));
        laberExcel.setBounds(180, 30, 250, 32);
        jpaneladd.add(laberExcel);

        buttonPicture = new JButton("图片文件夹");
        buttonPicture.setFont(font);
        buttonPicture.setBounds(30, 80, 130, 32);
        buttonPicture.setFocusPainted(false);
        jpaneladd.add(buttonPicture);

        laberPicture = new JLabel();
        laberPicture.setFont(new Font("微软雅黑", Font.PLAIN, 8));
        laberPicture.setBorder(BorderFactory.createLineBorder(new Color(124, 131, 135)));
        laberPicture.setBounds(180, 80, 250, 32);
        jpaneladd.add(laberPicture);

        buttonAdd= new JButton("确定");
        buttonAdd.setFont(font);
        buttonAdd.setBounds(30, 130, 130, 32);
        buttonAdd.setFocusPainted(false);
        jpaneladd.add(buttonAdd);

        progressBar1=new JProgressBar();
        progressBar1.setBounds(180, 130, 250, 32);
        jpaneladd.add(progressBar1);

        progressBar1.setStringPainted(true);
        progressBar1.setVerifyInputWhenFocusTarget(true);
        progressBar1.setForeground(Color.GREEN);
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(100);

        // 设置对话框的内容面板
        setContentPane(jpaneladd);
        // 显示对话框
        event();
        setVisible(true);
    }

    File excelfile=null;
    File picturefile=null;
    private void event() {
        buttonExcel.addActionListener(e->{
            fileChooserExcel=new JFileChooser();
            fileChooserExcel.setDialogTitle("选择上传excel");
            fileChooserExcel.setCurrentDirectory(new File(defaultDisk));
            fileChooserExcel.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooserExcel.setMultiSelectionEnabled(false);
            fileChooserExcel.showOpenDialog(null);
            excelfile=fileChooserExcel.getSelectedFile();
            laberExcel.setText(excelfile.getPath());
        });

        buttonPicture.addActionListener(e->{
            fileChooserPicture=new JFileChooser();
            fileChooserPicture.setDialogTitle("选择照片文件夹");
            fileChooserPicture.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            fileChooserPicture.setCurrentDirectory(new File(defaultDisk));
            fileChooserPicture.setMultiSelectionEnabled(false);
            fileChooserPicture.showOpenDialog(null);
            picturefile=fileChooserPicture.getSelectedFile();
            laberPicture.setText(picturefile.getPath());
        });

        buttonAdd.addActionListener((ActionEvent e)->{
            progressBar1.setVisible(true);
            String filePath=excelfile.getPath();
            String fileName=excelfile.getName();
            if(fileName.endsWith(ExcelUtils.XLS) || fileName.endsWith(ExcelUtils.XLSX)){
                java.util.List<java.util.List<Map<String, Object>>> excelResult= ExcelUtils.readExcel(filePath,0,0,1);
                importResultToDB(excelResult.get(0),picturefile);
                JOptionPane.showMessageDialog(jpaneladd, "处理完成", "提示",JOptionPane.INFORMATION_MESSAGE);
                buttonAdd.setEnabled(true);
            }else{
                JOptionPane.showMessageDialog(jpaneladd, "文件类型错误！", "错误",JOptionPane.ERROR_MESSAGE);
            }
            buttonAdd.setEnabled(false);
        });
    }


    private void importResultToDB(java.util.List<Map<String, Object>> excelResult, File picfile) {
        try{
            SqliteUtils sqliteUtils =new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils.openConnection();

            Integer third=excelResult.size() / 3;
            for (int i = 0; i < excelResult.size(); i++) {
                Map<String, Object> aMap=excelResult.get(i);
                StringBuffer sql=new StringBuffer();
                Map<String, Object> aNewEngMap=mapChnToEng(aMap);

                java.util.List pictures= Arrays.asList(aNewEngMap.get("pictureNumber").toString().split(","));
                File[] dataFiles= picfile.listFiles((dir, name) -> {
                    if (name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".png")) {
                        return true;
                    }
                    return false;
                });

                StringBuffer picturePath=new StringBuffer();
                for(File file:dataFiles){
                    String fileName=file.getName();
                    String tempFileName=fileName.substring(0,fileName.lastIndexOf("."));
                    if(pictures.contains(tempFileName)){
                        String picpath= FileUtils.copy(file,pictureDir);
                        if(!picturePath.toString().isEmpty()){
                            picturePath.append(";");
                        }
                        picturePath.append(picpath);
                    }
                }

                aNewEngMap.put("picturePaths",picturePath);
                sql.append("insert into record (");
                StringBuffer sqlkeys=new StringBuffer();
                StringBuffer sqlvalues=new StringBuffer();
                for(String key:aNewEngMap.keySet()){
                    if(!sqlkeys.toString().isEmpty()){
                        sqlkeys.append(",");
                        sqlvalues.append(",");
                    }
                    sqlkeys.append(key);
                    sqlvalues.append("'").append(aNewEngMap.get(key).toString()).append("'");
                }
                sql.append(sqlkeys.toString()).append(") values (").append(sqlvalues.toString()).append(");");
                sqliteUtils.updateOrInsert(sql.toString());

               if(i==third){
                   Task task = new Task(30);
                   task.start();
               }
               if(i==third*2){
                   Task task = new Task(60);
                   task.start();
               }
               if(i==excelResult.size()-1){
                   Task task = new Task(100);
                   task.start();
               }
            }

            sqliteUtils.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class Task extends Thread {

        int value;
        public Task(int tempValue) {
            value=tempValue;
        }

        public void run() {
                final int progress = value;
                SwingUtilities.invokeLater(() -> {
                    progressBar1.setValue(progress);
                });
        }
    }

    private java.util.List<Map<String, Object>> changeChnToEng(java.util.List<Map<String, Object>> excelResult) {
        List<Map<String, Object>> result=new ArrayList<>();
        for (Map<String, Object> stringObjectMap : excelResult) {
            Map<String, Object> oneMap=mapChnToEng(stringObjectMap);
            result.add(oneMap);
        }
        return result;
    }

    private Map<String, Object> mapChnToEng(Map<String, Object> stringObjectMap) {
        Map<String, Object> newMap=new HashMap<>();
        for(String key:stringObjectMap.keySet()){
            String engKey= ExcelName.getEngName(key);
            if(engKey==null || engKey.equals("id")){
                continue;
            }
            newMap.put(engKey,stringObjectMap.get(key));
        }
        return newMap;
    }

    public static void main(String[] args) {
        new Add2(new JFrame(),"hahah");
    }
}

