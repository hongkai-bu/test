package com.hongkai.start;

import com.hongkai.common.CommonConfig;
import com.hongkai.common.DateChooser;
import com.hongkai.common.SwingCommon;
import com.hongkai.enums.ExcelName;
import com.hongkai.model.ShowTableModel;
import com.hongkai.utils.ExcelUtils;
import com.hongkai.utils.FileUtils;
import com.hongkai.utils.SqliteUtils;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.FilenameFilter;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;


public class Start {
    private JPanel jpanel;
    private JTable table1;
    private JButton button5;
    private JTabbedPane tabbedPane1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JComboBox comboBox1;
    private JTextField textField11;
    private JTextField textField12;
    private JTextField textField13;
    private JTextField textField15;
    private JTextField textField16;
    private JTextField textField17;
    private JComboBox comboBox2;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JPanel pictureJPanel;
    private JScrollPane pictureJScollPane;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button7;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JLabel label9;
    private JLabel label10;
    private JLabel label11;
    private JLabel label12;
    private JLabel label13;
    private JLabel label14;
    private JLabel label15;
    private JLabel label16;
    private JLabel label17;
    private JLabel label18;
    private JLabel label19;
    private JLabel label20;
    private JLabel label21;
    private JLabel label23;
    private JLabel label24;
    private JLabel label1;
    private JButton button8;
    private JButton button9;
    private JButton button10;

    private JComboBox comboBox3;
    private JLabel label;
    private JFileChooser picturesChooser;
    private ShowTableModel showTableModel;
    public static JFrame frameWindow;
    private DateChooser dateChooser1;
    private DateChooser dateChooser2;

    private String allQuerySql="select * from record";
    private String  defaultDisk = "d:\\";
    private String pictureDir="D:\\DataBaseForNiHeWan\\picture";
    private final  int WIDTH=500;
    private final  int HEIGHT=400;
    private File[] insertFiles;
    private String oneRecordPicturePaths;
    private String[] pics;

    private static Logger logger = Logger.getLogger(ExcelUtils.class);

    public Start(){
        frameWindow=new JFrame("泥河湾国家级自然保护区标本管理系统");
        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("png").getPath()+"/picture.png");
        frameWindow.setIconImage(imageIcon.getImage());

        dateChooser1=DateChooser.getInstance("yyyy-MM-dd");
        dateChooser1.register(textField15);

        dateChooser2=DateChooser.getInstance("yyyy-MM-dd");
        dateChooser2.register(textField17);

        frameWindow.setContentPane(jpanel);
        frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWindow.pack();
        frameWindow.setVisible(true);
        frameWindow.setSize(1200,1000);
        frameWindow.setLocationRelativeTo(null);

        style();
        getTableInfo(allQuerySql);
        event();
    }

    private void style() {
        pictureJPanel.setLayout(null);
        pictureJPanel.setOpaque(false);//将面板设置为透明

        SwingCommon.changeIconSize(button1,"Add.png","新增");
        SwingCommon.changeIconSize(button2,"FolderUp.png","文件导出");
        SwingCommon.changeIconSize(button3,"Search.png","查询");
        SwingCommon.changeIconSize(button4,"FolderAdd.png","数据导入");
        SwingCommon.changeIconSize(button5,"Remove.png","删除");
        SwingCommon.changeIconSize(button7,"ImageAdd.png","选择图片");
        SwingCommon.changeIconSize(button8,"Cd.png","保存");
        SwingCommon.changeIconSize(button9,"NoteEdit.png","修改");
        SwingCommon.changeIconSize(button10,"ImageNext.png","下一张");

        SwingCommon.changeBorder(textArea1);
        SwingCommon.changeBorder(textArea2);

        SwingCommon.changeFont(button1,16);
        SwingCommon.changeFont(button2,16);
        SwingCommon.changeFont(button3,16);
        SwingCommon.changeFont(button4,16);
        SwingCommon.changeFont(button5,16);
        SwingCommon.changeFont(button7,14);
        SwingCommon.changeFont(button8,16);
        SwingCommon.changeFont(button9,16);

        SwingCommon.changeFont(label2,14);
        SwingCommon.changeFont(label3,14);
        SwingCommon.changeFont(label4,14);
        SwingCommon.changeFont(label5,14);
        SwingCommon.changeFont(label6,14);
        SwingCommon.changeFont(label7,14);
        SwingCommon.changeFont(label8,14);
        SwingCommon.changeFont(label9,14);
        SwingCommon.changeFont(label10,14);
        SwingCommon.changeFont(label11,14);
        SwingCommon.changeFont(label12,14);
        SwingCommon.changeFont(label13,14);
        SwingCommon.changeFont(label14,14);
        SwingCommon.changeFont(label15,14);
        SwingCommon.changeFont(label16,14);
        SwingCommon.changeFont(label17,14);
        SwingCommon.changeFont(label18,14);
        SwingCommon.changeFont(label19,14);
        SwingCommon.changeFont(label20,14);
        SwingCommon.changeFont(label21,14);
        SwingCommon.changeFont(label23,12);
        SwingCommon.changeFont(label24,12);

        SwingCommon.changeFont(tabbedPane1,14);

        SwingCommon.changeFont(textField2,12);
        SwingCommon.changeFont(textField3,12);
        SwingCommon.changeFont(textField4,12);
        SwingCommon.changeFont(textField5,12);
        SwingCommon.changeFont(textField6,12);
        SwingCommon.changeFont(textField7,12);
        SwingCommon.changeFont(textField8,12);
        SwingCommon.changeFont(textField9,12);
        SwingCommon.changeFont(textField10,12);
        SwingCommon.changeFont(textField11,12);
        SwingCommon.changeFont(textField12,12);
        SwingCommon.changeFont(textField13,12);
        SwingCommon.changeFont(textField15,12);
        SwingCommon.changeFont(textField16,12);
        SwingCommon.changeFont(textField17,12);

        SwingCommon.changeFont(comboBox1,12);
        SwingCommon.changeFont(comboBox2,12);
        SwingCommon.changeFont(comboBox3,12);

        comboBox1.addItem("自采");
        comboBox1.addItem("转让");
        comboBox1.addItem("交换");
        comboBox1.addItem("移交");
        comboBox1.addItem("赠与");
        comboBox1.addItem("其它");

        comboBox2.addItem("科研");
        comboBox2.addItem("抢救");
        comboBox2.addItem("科普");
        comboBox2.addItem("移交");
        comboBox2.addItem("其它");

        comboBox3.addItem("馆藏");
        comboBox3.addItem("借出研究");
        comboBox3.addItem("出境");
        comboBox3.addItem("国内展出");
        comboBox3.addItem("遗失");
        comboBox3.addItem("注销");
    }

    private void getTableInfo(String sql){
        try{
            SqliteUtils sqliteUtils =new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils.openConnection();
            List<Map<String,Object>> list= sqliteUtils.queryMap(sql);
            showTableModel=new ShowTableModel(list);
            table1.setModel(showTableModel);
            table1.setRowHeight(30);

            SwingCommon.FitTableColumns(table1);

            Dimension size = table1.getTableHeader().getPreferredSize();
            size.height = 32;//设置新的表头高度32
            table1.getTableHeader().setPreferredSize(size);
            table1.getTableHeader().setBackground(new Color(155, 221, 255));
            table1.getTableHeader().setFont(new Font("微软雅黑", Font.BOLD, 12));
            table1.getTableHeader().setReorderingAllowed(false);
            table1.getTableHeader().setResizingAllowed(false);

            label1.setText(String.format("当前共计:%s条项目信息",list.size()));
            sqliteUtils.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void event() {
        button1.addActionListener(e->{
           boolean result=check();
           boolean result2=check2();
           if(result){
               if(result2){
                   insertOneData(insertFiles);
               }
           }
            getTableInfo(allQuerySql);
        });

        button4.addActionListener(e->{
            frameWindow.setEnabled(false);
            new Add2(frameWindow,"Excel批量上传");
            getTableInfo(allQuerySql);
            frameWindow.setEnabled(true);
        });

        button5.addActionListener(e->{
            Object[] options = {"确定","取消"};
            int response=JOptionPane.showOptionDialog(jpanel, "确定删除？", "确定对话框",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(response==0) {
                try{
                    int[] selRow = table1.getSelectedRows();
                    int selColumn=0;
                    SqliteUtils sqliteUtils =new SqliteUtils(CommonConfig.dbPath);
                    sqliteUtils.openConnection();
                    for (int i:selRow) {
                        int id=Integer.valueOf(table1.getValueAt(i, selColumn).toString());
                        String querySql=String.format("select * from record where id=%s",id);
                        List<Map<String,Object>> result=sqliteUtils.queryMap(querySql);
                        String[] picturePaths=result.get(0).get("picturePaths").toString().split(";");
                        for (String picturePath : picturePaths) {
                            File pictureFile=new File(picturePath);
                            pictureFile.delete();
                        }
                        String sql=String.format("delete from record where id=%s",id);
                        sqliteUtils.updateOrInsert(sql);
                    }
                    getTableInfo(allQuerySql);
                }catch (Exception e1){
                    e1.printStackTrace();
                }
            }
        });

        button7.addActionListener(e->{
            picturesChooser=new JFileChooser();
            picturesChooser.setMultiSelectionEnabled(true);
            picturesChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            picturesChooser.setCurrentDirectory(new File(defaultDisk));
            picturesChooser.showOpenDialog(null);
            picturesChooser.addChoosableFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    String name = f.getName();
                    return name.toLowerCase().endsWith(".png") || name.toLowerCase().endsWith(".jpg");  // 仅显示目录和png、jpg文件
                }

                @Override
                public String getDescription() {
                    return "*.png;*.jpg";
                }
            });
            insertFiles=picturesChooser.getSelectedFiles();
            String lableText=String.format("已选择照片%s张",insertFiles.length);
            label23.setText(lableText);
        });

        button9.addActionListener(e->{
            try{
                int[] selRow = table1.getSelectedRows();
                int selColumn=0;
                SqliteUtils sqliteUtils =new SqliteUtils(CommonConfig.dbPath);
                sqliteUtils.openConnection();
                int id = Integer.valueOf(table1.getValueAt(selRow[0], selColumn).toString());
                String querySql = String.format("select * from record where id=%s", id);
                List<Map<String, Object>> result = sqliteUtils.queryMap(querySql);
                Map<String, Object> amap = result.get(0);
                getDetails(amap);
                sqliteUtils.close();
            }catch (Exception e1){
                e1.printStackTrace();
            }
        });

        button10.addActionListener(e->{
            try{
                String laberText=label24.getText();
                int totalNum=Integer.valueOf(laberText.substring(laberText.indexOf("共计")+2,laberText.indexOf("张照片")));
                int currentNum=Integer.valueOf(laberText.substring(laberText.indexOf("第")+1,laberText.lastIndexOf("张")));
                if(currentNum+1<=totalNum){
                    nextPicture(pics[currentNum]);
                    label24.setText(String.format("共计%s张照片，第%s张",pics.length,currentNum+1));
                }else{
                    JOptionPane.showMessageDialog(jpanel, "没有照片了！", "提示",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch (Exception e1){
                e1.printStackTrace();
            }
        });
    }



    private void getDetails(Map<String, Object> amap) {
        for(String key:amap.keySet()){
            if (key.equals(ExcelName.sampleId.getEngName())) {
                textField2.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.sampleLocation.getEngName())) {
                textField3.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.chinaName.getEngName())) {
                textField4.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.latiName.getEngName())) {
                textField5.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.proLocation.getEngName())) {
                textField6.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.sampleType.getEngName())) {
                textField7.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.category.getEngName())) {
                textField8.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.protectLevel.getEngName())) {
                textField9.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.origNumber.getEngName())) {
                textField10.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.sampleSource.getEngName())) {
                comboBox1.setSelectedItem((amap.get(key).toString()));
            }
            if (key.equals(ExcelName.sampleStatus.getEngName())) {
                textField11.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.sampleCount.getEngName())) {
                textField12.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.pictureNumber.getEngName())) {
                textField13.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.sampleGone.getEngName())) {
                comboBox3.setSelectedItem((amap.get(key).toString()));
            }
            if (key.equals(ExcelName.enterDate.getEngName())) {
                textField15.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.exploreCompany.getEngName())) {
                textField16.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.exploreDate.getEngName())) {
                textField17.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.exploreReason.getEngName())) {
                comboBox2.setSelectedItem((amap.get(key).toString()));
            }
            if (key.equals(ExcelName.sampleDesc.getEngName())) {
                textArea1.setText(amap.get(key).toString());
            }
            if (key.equals(ExcelName.remark.getEngName())) {
                textArea2.setText(amap.get(key).toString());
            }
            if(key.equals(ExcelName.picturePaths.getEngName())){
                oneRecordPicturePaths=amap.get(key).toString();
                pics=oneRecordPicturePaths.split(";");
                label24.setText(String.format("共计%s张照片，第1张",pics.length));
                nextPicture(pics[0]);
            }
        }
    }

    private void nextPicture(String picturePath){
        ImageIcon icon = new ImageIcon(picturePath);
        icon.setImage(icon.getImage().getScaledInstance(WIDTH,HEIGHT,Image.SCALE_DEFAULT));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(icon);

        label.setBounds(50, 10, icon.getIconWidth(), icon.getIconHeight());
        pictureJPanel.setPreferredSize(new Dimension(pictureJScollPane.getWidth()+200, pictureJScollPane.getHeight()));
        pictureJPanel.revalidate();
    }

    private boolean check2() {
        String checkSql="SELECT sampleId FROM record;";
        try {
            SqliteUtils sqliteUtils = new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils.openConnection();
            List<Map<String, Object>> result = sqliteUtils.queryMap(checkSql);
            for (Map<String, Object> stringObjectMap : result) {
                String sampleId=stringObjectMap.get("sampleId").toString();
                if(textField2.getText().equals(sampleId)){
                    JOptionPane.showMessageDialog(textField2, sampleId+":样本ID重复", "错误",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            sqliteUtils.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    private boolean check() {
        if(textField2.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField2, label2.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField3.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField3, label3.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField4.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField4, label4.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField6.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField6, label6.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField10.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField10, label10.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField11.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField11, label12.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField12.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField12, label13.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField13.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField13, label14.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField15.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField15, label16.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField16.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField16, label17.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(textField17.getText().isEmpty()){
            JOptionPane.showMessageDialog(textField17, label18.getText()+"内容缺失", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(label23.getText().isEmpty()){
            JOptionPane.showMessageDialog(label23, "未选择照片", "错误",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void insertOneData(File[] insertFiles) {

        try {
            StringBuffer picturePath = new StringBuffer();
            for (File file : insertFiles) {
                String picpath = FileUtils.copy(file, pictureDir);
                if (!picturePath.toString().isEmpty()) {
                    picturePath.append(";");
                }
                picturePath.append(picpath);
            }

            StringBuffer insertSql=new StringBuffer();
            insertSql.append("insert into record (sampleId,sampleLocation,chnName,latiName,proLocation,sampleType,category," +
                    "protectLevel,origNumber,sampleSource,sampleStatus,sampleCount,pictureNumber,sampleGone,enterDate,exploreCompany," +
                    "exploreDate,exploreReason,sampleDesc,remark,picturePaths) values (").append("'"+textField2.getText()+"',")
                    .append("'"+textField3.getText()+"',").append("'"+textField4.getText()+"',").append("'"+textField5.getText()+"',").append("'"+textField6.getText()+"',")
                    .append("'"+textField7.getText()+"',").append("'"+textField8.getText()+"',").append("'"+textField9.getText()+"',").append("'"+textField10.getText()+"',")
                    .append("'"+comboBox1.getSelectedItem().toString()+"',").append("'"+textField11.getText()+"',").append("'"+textField12.getText()+"',").append("'"+textField13.getText()+"',")
                    .append("'"+comboBox3.getSelectedItem().toString()+"',").append("'"+textField15.getText()+"',").append("'"+textField16.getText()+"',").append("'"+textField17.getText()+"',")
                    .append("'"+comboBox2.getSelectedItem().toString()+"',").append("'"+textArea1.getText()+"',").append("'"+textArea2.getText()+"',").append("'"+picturePath.toString()+"');");

            logger.info(insertSql.toString());
            SqliteUtils sqliteUtils1 = new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils1.openConnection();
            sqliteUtils1.updateOrInsert(insertSql.toString());
            JOptionPane.showMessageDialog(jpanel, "处理完成", "提示",JOptionPane.INFORMATION_MESSAGE);
            sqliteUtils1.close();

        }catch (Exception e){
            JOptionPane.showMessageDialog(jpanel, e, "错误",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Start();
    }
}
