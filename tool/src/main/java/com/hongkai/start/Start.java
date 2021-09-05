package com.hongkai.start;

import com.hongkai.common.CommonConfig;
import com.hongkai.common.DateChooser;
import com.hongkai.common.SwingCommon;
import com.hongkai.enums.ExcelName;
import com.hongkai.model.ShowTableModel;
import com.hongkai.utils.ExcelUtils;
import com.hongkai.utils.FileUtils;
import com.hongkai.utils.SqliteUtils;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.io.File;
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
    private JButton button6;
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

    private String allQuerySql = "select * from record";
    private String defaultDisk = "d:\\";
    private String pictureDir = "D:\\DataBaseForNiHeWan\\picture";
    private final int WIDTH = 500;
    private final int HEIGHT = 400;
    private File[] insertFiles;
    private String oneRecordPicturePaths;
    private String[] pics;

    private static Logger logger = Logger.getLogger(ExcelUtils.class);

    public Start() {
        frameWindow = new JFrame("泥河湾国家级自然保护区标本管理系统");
        ImageIcon imageIcon = new ImageIcon("png/picture.png");
        frameWindow.setIconImage(imageIcon.getImage());

        dateChooser1 = DateChooser.getInstance("yyyy-MM-dd");
        dateChooser1.register(textField15);

        dateChooser2 = DateChooser.getInstance("yyyy-MM-dd");
        dateChooser2.register(textField17);

        frameWindow.setContentPane(jpanel);
        frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWindow.pack();
        frameWindow.setVisible(true);
        frameWindow.setSize(1200, 1000);
        frameWindow.setLocationRelativeTo(null);

        style();
        getTableInfo(allQuerySql);
        event();
    }

    private void style() {
        pictureJPanel.setLayout(null);
        pictureJPanel.setOpaque(false);//将面板设置为透明

        SwingCommon.changeIconSize(button1, "Add.png", "新增");
        SwingCommon.changeIconSize(button2, "FolderUp.png", "文件导出");
        SwingCommon.changeIconSize(button3, "Search.png", "查询");
        SwingCommon.changeIconSize(button4, "FolderAdd.png", "数据导入");
        SwingCommon.changeIconSize(button5, "Remove.png", "删除");
        SwingCommon.changeIconSize(button6, "NoteRemove.png", "清空");
        SwingCommon.changeIconSize(button7, "ImageAdd.png", "选择图片");
        SwingCommon.changeIconSize(button8, "Cd.png", "保存");
        SwingCommon.changeIconSize(button9, "NoteEdit.png", "详情");
        SwingCommon.changeIconSize(button10, "ImageNext.png", "下一张");

        SwingCommon.changeBorder(textArea1);
        SwingCommon.changeBorder(textArea2);

        SwingCommon.changeFont(button1, 16);
        SwingCommon.changeFont(button2, 16);
        SwingCommon.changeFont(button3, 16);
        SwingCommon.changeFont(button4, 16);
        SwingCommon.changeFont(button5, 16);
        SwingCommon.changeFont(button6, 16);
        SwingCommon.changeFont(button7, 14);
        SwingCommon.changeFont(button8, 16);
        SwingCommon.changeFont(button9, 16);

        SwingCommon.changeFont(label2, 14);
        SwingCommon.changeFont(label3, 14);
        SwingCommon.changeFont(label4, 14);
        SwingCommon.changeFont(label5, 14);
        SwingCommon.changeFont(label6, 14);
        SwingCommon.changeFont(label7, 14);
        SwingCommon.changeFont(label8, 14);
        SwingCommon.changeFont(label9, 14);
        SwingCommon.changeFont(label10, 14);
        SwingCommon.changeFont(label11, 14);
        SwingCommon.changeFont(label12, 14);
        SwingCommon.changeFont(label13, 14);
        SwingCommon.changeFont(label14, 14);
        SwingCommon.changeFont(label15, 14);
        SwingCommon.changeFont(label16, 14);
        SwingCommon.changeFont(label17, 14);
        SwingCommon.changeFont(label18, 14);
        SwingCommon.changeFont(label19, 14);
        SwingCommon.changeFont(label20, 14);
        SwingCommon.changeFont(label21, 14);
        SwingCommon.changeFont(label23, 12);
        SwingCommon.changeFont(label24, 12);

        SwingCommon.changeFont(tabbedPane1, 14);

        SwingCommon.changeFont(textField2, 12);
        SwingCommon.changeFont(textField3, 12);
        SwingCommon.changeFont(textField4, 12);
        SwingCommon.changeFont(textField5, 12);
        SwingCommon.changeFont(textField6, 12);
        SwingCommon.changeFont(textField7, 12);
        SwingCommon.changeFont(textField8, 12);
        SwingCommon.changeFont(textField9, 12);
        SwingCommon.changeFont(textField10, 12);
        SwingCommon.changeFont(textField11, 12);
        SwingCommon.changeFont(textField12, 12);
        SwingCommon.changeFont(textField13, 12);
        SwingCommon.changeFont(textField15, 12);
        SwingCommon.changeFont(textField16, 12);
        SwingCommon.changeFont(textField17, 12);

        SwingCommon.changeFont(comboBox1, 12);
        SwingCommon.changeFont(comboBox2, 12);
        SwingCommon.changeFont(comboBox3, 12);

        comboBox1.addItem("");
        comboBox1.addItem("自采");
        comboBox1.addItem("转让");
        comboBox1.addItem("交换");
        comboBox1.addItem("移交");
        comboBox1.addItem("赠与");
        comboBox1.addItem("其它");

        comboBox2.addItem("");
        comboBox2.addItem("科研");
        comboBox2.addItem("抢救");
        comboBox2.addItem("科普");
        comboBox2.addItem("移交");
        comboBox2.addItem("其它");

        comboBox3.addItem("");
        comboBox3.addItem("馆藏");
        comboBox3.addItem("借出研究");
        comboBox3.addItem("出境");
        comboBox3.addItem("国内展出");
        comboBox3.addItem("遗失");
        comboBox3.addItem("注销");
    }

    private void getTableInfo(String sql) {
        try {
            SqliteUtils sqliteUtils = new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils.openConnection();
            List<Map<String, Object>> list = sqliteUtils.queryMap(sql);
            showTableModel = new ShowTableModel(list);
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

            label1.setText(String.format("当前共计:%s条项目信息", list.size()));
            sqliteUtils.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(jpanel, e, "错误", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void event() {
        button1.addActionListener(e -> {
            boolean result = check();
            boolean result2 = check2();
            if (result) {
                if (result2) {
                    insertOneData(insertFiles);
                }
            }
            getTableInfo(allQuerySql);
        });

        button2.addActionListener(e -> {
            try {
                int[] selRow = table1.getSelectedRows();
                if (selRow.length >= 1) {
                    int selColumn = 0;
                    SqliteUtils sqliteUtils = new SqliteUtils(CommonConfig.dbPath);
                    sqliteUtils.openConnection();
                    int id = Integer.valueOf(table1.getValueAt(selRow[0], selColumn).toString());
                    String querySql = String.format("select * from record where id=%s", id);
                    List<Map<String, Object>> result = sqliteUtils.queryMap(querySql);
                    Map<String, Object> amap = result.get(0);
                    sqliteUtils.close();
                    new Export(frameWindow, "Excel导出", amap);
                } else {
                    JOptionPane.showMessageDialog(jpanel, "请选择任意一行导出！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(jpanel, e1, "错误", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });


        button3.addActionListener(e -> {
            String sql = constructMapToQuerySql();
            getTableInfo(sql);
        });

        button4.addActionListener(e -> {
            frameWindow.setEnabled(false);
            new Add2(frameWindow, "Excel批量上传");
            getTableInfo(allQuerySql);
            frameWindow.setEnabled(true);
        });

        button5.addActionListener(e -> {
            Object[] options = {"确定", "取消"};
            int response = JOptionPane.showOptionDialog(jpanel, "确定删除？", "确定对话框", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (response == 0) {
                try {
                    int[] selRow = table1.getSelectedRows();
                    int selColumn = 0;
                    SqliteUtils sqliteUtils = new SqliteUtils(CommonConfig.dbPath);
                    sqliteUtils.openConnection();
                    for (int i : selRow) {
                        int id = Integer.valueOf(table1.getValueAt(i, selColumn).toString());
                        String querySql = String.format("select * from record where id=%s", id);
                        List<Map<String, Object>> result = sqliteUtils.queryMap(querySql);
                        String[] picturePaths = result.get(0).get("picturePaths").toString().split(";");
                        for (String picturePath : picturePaths) {
                            File pictureFile = new File(picturePath);
                            pictureFile.delete();
                        }
                        String sql = String.format("delete from record where id=%s", id);
                        sqliteUtils.updateOrInsert(sql);
                    }
                    getTableInfo(allQuerySql);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(jpanel, e1, "错误", JOptionPane.ERROR_MESSAGE);
                    e1.printStackTrace();
                }
            }
        });

        button6.addActionListener(e -> {
            clean();
        });

        button7.addActionListener(e -> {
            picturesChooser = new JFileChooser();
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
            insertFiles = picturesChooser.getSelectedFiles();
            String lableText = String.format("已选择照片%s张", insertFiles.length);
            label23.setText(lableText);
        });

        button8.addActionListener(e -> {
            if (textField2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(textField2, "样本ID不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            }
            String sql = constructMapToSql();
            try {
                SqliteUtils sqliteUtils = new SqliteUtils(CommonConfig.dbPath);
                sqliteUtils.openConnection();
                sqliteUtils.updateOrInsert(sql);
                sqliteUtils.close();
                JOptionPane.showMessageDialog(jpanel, "修改完成", "提示", JOptionPane.INFORMATION_MESSAGE);
                getTableInfo(allQuerySql);
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(jpanel, e1, "错误", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });

        button9.addActionListener(e -> {
            try {
                int[] selRow = table1.getSelectedRows();
                if (selRow.length >= 1) {
                    int selColumn = 0;
                    SqliteUtils sqliteUtils = new SqliteUtils(CommonConfig.dbPath);
                    sqliteUtils.openConnection();
                    int id = Integer.valueOf(table1.getValueAt(selRow[0], selColumn).toString());
                    String querySql = String.format("select * from record where id=%s", id);
                    List<Map<String, Object>> result = sqliteUtils.queryMap(querySql);
                    Map<String, Object> amap = result.get(0);
                    getDetails(amap);
                    sqliteUtils.close();
                } else {
                    JOptionPane.showMessageDialog(jpanel, "请选择任意一行导出", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(jpanel, e1, "错误", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });

        button10.addActionListener(e -> {
            try {
                String laberText = label24.getText();
                int totalNum = Integer.valueOf(laberText.substring(laberText.indexOf("共计") + 2, laberText.indexOf("张照片")));
                int currentNum = Integer.valueOf(laberText.substring(laberText.indexOf("第") + 1, laberText.lastIndexOf("张")));
                if (currentNum + 1 <= totalNum) {
                    nextPicture(pics[currentNum]);
                    label24.setText(String.format("共计%s张照片，第%s张", pics.length, currentNum + 1));
                } else {
                    JOptionPane.showMessageDialog(jpanel, "没有照片了！", "提示", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(jpanel, e1, "错误", JOptionPane.ERROR_MESSAGE);
                e1.printStackTrace();
            }
        });
    }

    private void clean() {
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
        textField8.setText("");
        textField9.setText("");
        textField10.setText("");
        comboBox1.setSelectedItem("");
        textField11.setText("");
        textField12.setText("");
        textField13.setText("");
        comboBox3.setSelectedItem("");
        textField15.setText("");
        textField16.setText("");
        textField17.setText("");
        comboBox2.setSelectedItem("");
        textArea1.setText("");
        textArea2.setText("");
        label23.setText("");
    }

    private String constructMapToQuerySql() {
        Map<String, Object> map = constructMap();
        if (haveValue(map)) {
            StringBuffer b1 = new StringBuffer();
            b1.append("SELECT * FROM record WHERE ");

            StringBuffer b2 = new StringBuffer();
            for (String key : map.keySet()) {
                if (!map.get(key).toString().isEmpty()) {
                    if (!b2.toString().isEmpty()) {
                        b2.append(" AND ");
                    }
                    b2.append(key).append("=").append("'").append(map.get(key).toString()).append("'");
                }
            }

            b1.append(b2.toString()).append(";");
            return b1.toString();
        } else {
            return allQuerySql;
        }
    }

    private boolean haveValue(Map<String, Object> map) {
        for (String key : map.keySet()) {
            if (!map.get(key).toString().trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private String constructMapToSql() {
        Map<String, Object> map = constructMap();
        StringBuffer b1 = new StringBuffer();
        b1.append("UPDATE record SET ");

        if (!label23.getText().isEmpty()) {
            StringBuffer picturePath = new StringBuffer();
            insertFiles = picturesChooser.getSelectedFiles();
            for (File afile : insertFiles) {
                String picpath = FileUtils.copy(afile, pictureDir);
                if (!picturePath.toString().isEmpty()) {
                    picturePath.append(";");
                }
                picturePath.append(picpath);
            }
            map.put("picturePaths", picturePath.toString());
        }

        StringBuffer b2 = new StringBuffer();
        for (String key : map.keySet()) {
            if (!b2.toString().isEmpty()) {
                b2.append(",");
            }
            b2.append(key).append("=").append("'").append(map.get(key).toString()).append("'");
        }


        b1.append(b2.toString()).append(" WHERE sampleId='").append(map.get(ExcelName.sampleId.getEngName()).toString()).append("';");

        return b1.toString();
    }

    private Map<String, Object> constructMap() {
        Map<String, Object> map = new HashMap<>();
        map.put(ExcelName.sampleId.getEngName(), textField2.getText());
        map.put(ExcelName.sampleLocation.getEngName(), textField3.getText());
        map.put(ExcelName.chinaName.getEngName(), textField4.getText());
        map.put(ExcelName.latiName.getEngName(), textField5.getText());
        map.put(ExcelName.proLocation.getEngName(), textField6.getText());
        map.put(ExcelName.sampleType.getEngName(), textField7.getText());
        map.put(ExcelName.category.getEngName(), textField8.getText());
        map.put(ExcelName.protectLevel.getEngName(), textField9.getText());
        map.put(ExcelName.origNumber.getEngName(), textField10.getText());
        map.put(ExcelName.sampleSource.getEngName(), comboBox1.getSelectedItem().toString());
        map.put(ExcelName.sampleStatus.getEngName(), textField11.getText());
        map.put(ExcelName.sampleCount.getEngName(), textField12.getText());
        map.put(ExcelName.pictureNumber.getEngName(), textField13.getText());
        map.put(ExcelName.sampleGone.getEngName(), comboBox3.getSelectedItem().toString());
        map.put(ExcelName.enterDate.getEngName(), textField15.getText());
        map.put(ExcelName.exploreCompany.getEngName(), textField16.getText());
        map.put(ExcelName.exploreDate.getEngName(), textField17.getText());
        map.put(ExcelName.exploreReason.getEngName(), comboBox2.getSelectedItem().toString());
        map.put(ExcelName.sampleDesc.getEngName(), textArea1.getText());
        map.put(ExcelName.remark.getEngName(), textArea2.getText());
        return map;
    }


    private void getDetails(Map<String, Object> amap) {
        for (String key : amap.keySet()) {
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
            if (key.equals(ExcelName.picturePaths.getEngName())) {
                insertFiles = null;
                label23.setText("");
                oneRecordPicturePaths = amap.get(key).toString();
                pics = oneRecordPicturePaths.split(";");
                label24.setText(String.format("共计%s张照片，第1张", pics.length));
                nextPicture(pics[0]);
            }
        }
    }

    private void nextPicture(String picturePath) {
        ImageIcon icon = new ImageIcon(picturePath);
        icon.setImage(icon.getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setIcon(icon);

        label.setBounds(50, 10, icon.getIconWidth(), icon.getIconHeight());
        pictureJPanel.setPreferredSize(new Dimension(pictureJScollPane.getWidth() + 200, pictureJScollPane.getHeight()));
        pictureJPanel.revalidate();
    }

    private boolean check2() {
        String checkSql = "SELECT sampleId FROM record;";
        try {
            SqliteUtils sqliteUtils = new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils.openConnection();
            List<Map<String, Object>> result = sqliteUtils.queryMap(checkSql);
            for (Map<String, Object> stringObjectMap : result) {
                String sampleId = stringObjectMap.get("sampleId").toString();
                if (textField2.getText().equals(sampleId)) {
                    JOptionPane.showMessageDialog(textField2, sampleId + ":样本ID重复", "错误", JOptionPane.ERROR_MESSAGE);
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
        if (textField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField2, label2.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField3, label3.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField4.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField4, label4.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField6, label6.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField10.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField10, label10.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField11.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField11, label12.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField12.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField12, label13.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField13.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField13, label14.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField15.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField15, label16.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField16.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField16, label17.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (textField17.getText().isEmpty()) {
            JOptionPane.showMessageDialog(textField17, label18.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (label23.getText().isEmpty()) {
            JOptionPane.showMessageDialog(label23, "未选择照片", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (comboBox1.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(comboBox1, label11.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (comboBox2.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(comboBox1, label19.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (comboBox3.getSelectedItem().toString().isEmpty()) {
            JOptionPane.showMessageDialog(comboBox1, label15.getText() + "内容缺失", "错误", JOptionPane.ERROR_MESSAGE);
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

            StringBuffer insertSql = new StringBuffer();
            insertSql.append("insert into record (sampleId,sampleLocation,chnName,latiName,proLocation,sampleType,category," +
                    "protectLevel,origNumber,sampleSource,sampleStatus,sampleCount,pictureNumber,sampleGone,enterDate,exploreCompany," +
                    "exploreDate,exploreReason,sampleDesc,remark,pictureCount,picturePaths) values (").append("'" + textField2.getText() + "',")
                    .append("'" + textField3.getText() + "',").append("'" + textField4.getText() + "',").append("'" + textField5.getText() + "',").append("'" + textField6.getText() + "',")
                    .append("'" + textField7.getText() + "',").append("'" + textField8.getText() + "',").append("'" + textField9.getText() + "',").append("'" + textField10.getText() + "',")
                    .append("'" + comboBox1.getSelectedItem().toString() + "',").append("'" + textField11.getText() + "',").append("'" + textField12.getText() + "',").append("'" + textField13.getText() + "',")
                    .append("'" + comboBox3.getSelectedItem().toString() + "',").append("'" + textField15.getText() + "',").append("'" + textField16.getText() + "',").append("'" + textField17.getText() + "',")
                    .append("'" + comboBox2.getSelectedItem().toString() + "',").append("'" + textArea1.getText() + "',").append("'" + textArea2.getText() + "',").append("'" + insertFiles.length + "',").append("'" + picturePath.toString() + "');");

            logger.info(insertSql.toString());
            SqliteUtils sqliteUtils1 = new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils1.openConnection();
            sqliteUtils1.updateOrInsert(insertSql.toString());
            JOptionPane.showMessageDialog(jpanel, "处理完成", "提示", JOptionPane.INFORMATION_MESSAGE);
            sqliteUtils1.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(jpanel, e, "错误", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Start();
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        jpanel = new JPanel();
        jpanel.setLayout(new GridLayoutManager(3, 3, new Insets(20, 20, 20, 20), -1, -1));
        jpanel.setAutoscrolls(true);
        jpanel.setBackground(new Color(-66049));
        jpanel.setFocusable(true);
        jpanel.setForeground(new Color(-66049));
        jpanel.setOpaque(true);
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-66049));
        jpanel.add(panel1, new GridConstraints(1, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 300), null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setBackground(new Color(-8617081));
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        table1 = new JTable();
        table1.setAutoCreateColumnsFromModel(true);
        table1.setAutoCreateRowSorter(true);
        table1.setAutoResizeMode(0);
        table1.setAutoscrolls(false);
        table1.setCellSelectionEnabled(true);
        table1.setColumnSelectionAllowed(true);
        table1.setDoubleBuffered(false);
        table1.setDragEnabled(false);
        table1.setEnabled(true);
        table1.setInheritsPopupMenu(true);
        table1.setOpaque(true);
        table1.setRowHeight(30);
        scrollPane1.setViewportView(table1);
        label1 = new JLabel();
        label1.setText("当前共计:0条项目信息");
        panel1.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tabbedPane1 = new JTabbedPane();
        jpanel.add(tabbedPane1, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 500), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(9, 7, new Insets(20, 20, 20, 20), -1, -1));
        panel2.setBackground(new Color(-66049));
        panel2.setForeground(new Color(-66049));
        tabbedPane1.addTab("登记信息", panel2);
        label2 = new JLabel();
        label2.setText("样本编号：");
        panel2.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField2 = new JTextField();
        panel2.add(textField2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label3 = new JLabel();
        label3.setText("样本位置：");
        panel2.add(label3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JTextField();
        panel2.add(textField3, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label4 = new JLabel();
        label4.setText("中文名称：");
        panel2.add(label4, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label5 = new JLabel();
        label5.setText("拉丁文名称：");
        panel2.add(label5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField5 = new JTextField();
        panel2.add(textField5, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label6 = new JLabel();
        label6.setText("产出地点：");
        panel2.add(label6, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField6 = new JTextField();
        panel2.add(textField6, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label7 = new JLabel();
        label7.setText("标本类别：");
        panel2.add(label7, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField7 = new JTextField();
        panel2.add(textField7, new GridConstraints(1, 5, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label8 = new JLabel();
        label8.setText("种属：");
        panel2.add(label8, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField8 = new JTextField();
        panel2.add(textField8, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label9 = new JLabel();
        label9.setText("保护级别：");
        panel2.add(label9, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField9 = new JTextField();
        panel2.add(textField9, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label10 = new JLabel();
        label10.setText("原有编号：");
        panel2.add(label10, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField10 = new JTextField();
        panel2.add(textField10, new GridConstraints(2, 5, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label11 = new JLabel();
        label11.setText("标本来源：");
        panel2.add(label11, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        comboBox1.setFocusTraversalPolicyProvider(false);
        comboBox1.setInheritsPopupMenu(false);
        comboBox1.setToolTipText("");
        panel2.add(comboBox1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label12 = new JLabel();
        label12.setText("标本状态：");
        panel2.add(label12, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField11 = new JTextField();
        panel2.add(textField11, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label13 = new JLabel();
        label13.setText("标本数量：");
        panel2.add(label13, new GridConstraints(3, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField12 = new JTextField();
        panel2.add(textField12, new GridConstraints(3, 5, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        textField4 = new JTextField();
        panel2.add(textField4, new GridConstraints(0, 5, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label14 = new JLabel();
        label14.setText("照片编号：");
        panel2.add(label14, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField13 = new JTextField();
        panel2.add(textField13, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label15 = new JLabel();
        label15.setText("标本去向：");
        panel2.add(label15, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label16 = new JLabel();
        label16.setText("收藏登记日期：");
        panel2.add(label16, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField15 = new JTextField();
        panel2.add(textField15, new GridConstraints(4, 5, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label17 = new JLabel();
        label17.setText("发掘单位：");
        panel2.add(label17, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField16 = new JTextField();
        panel2.add(textField16, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label18 = new JLabel();
        label18.setText("发掘日期：");
        panel2.add(label18, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField17 = new JTextField();
        panel2.add(textField17, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        label19 = new JLabel();
        label19.setText("发掘原因：");
        panel2.add(label19, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox2 = new JComboBox();
        panel2.add(comboBox2, new GridConstraints(5, 5, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textArea1 = new JTextArea();
        textArea1.setBackground(new Color(-1));
        textArea1.setDragEnabled(false);
        textArea1.setFocusCycleRoot(false);
        textArea1.setFocusTraversalPolicyProvider(false);
        textArea1.setFocusable(true);
        textArea1.setLineWrap(false);
        textArea1.setOpaque(true);
        textArea1.setTabSize(8);
        textArea1.setVerifyInputWhenFocusTarget(true);
        textArea1.setVisible(true);
        textArea1.setWrapStyleWord(false);
        panel2.add(textArea1, new GridConstraints(6, 1, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        comboBox3 = new JComboBox();
        comboBox3.setFocusTraversalPolicyProvider(false);
        comboBox3.setInheritsPopupMenu(false);
        comboBox3.setToolTipText("");
        panel2.add(comboBox3, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label23 = new JLabel();
        label23.setForeground(new Color(-12828863));
        label23.setText("");
        panel2.add(label23, new GridConstraints(7, 5, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button7 = new JButton();
        button7.setBorderPainted(false);
        button7.setFocusPainted(false);
        button7.setText("照片选择：");
        panel2.add(button7, new GridConstraints(6, 5, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textArea2 = new JTextArea();
        textArea2.setBackground(new Color(-1));
        panel2.add(textArea2, new GridConstraints(6, 3, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        label21 = new JLabel();
        label21.setText("备注：");
        panel2.add(label21, new GridConstraints(6, 2, 3, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label20 = new JLabel();
        label20.setText("标本描述：");
        panel2.add(label20, new GridConstraints(6, 0, 3, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(2, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setPreferredSize(new Dimension(24, 24));
        tabbedPane1.addTab("照片展示", panel3);
        pictureJScollPane = new JScrollPane();
        pictureJScollPane.setAutoscrolls(true);
        panel3.add(pictureJScollPane, new GridConstraints(1, 0, 1, 5, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pictureJPanel = new JPanel();
        pictureJPanel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        pictureJPanel.setAutoscrolls(true);
        pictureJScollPane.setViewportView(pictureJPanel);
        label = new JLabel();
        label.setText("Label");
        pictureJPanel.add(label, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label24 = new JLabel();
        label24.setText("共计*张照片，第*张");
        panel3.add(label24, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        button10 = new JButton();
        button10.setBorderPainted(true);
        button10.setFocusPainted(false);
        button10.setOpaque(true);
        button10.setText("下一张");
        panel3.add(button10, new GridConstraints(0, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel3.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        jpanel.add(toolBar1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(410, 20), null, 0, false));
        toolBar1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null));
        button1 = new JButton();
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setOpaque(false);
        button1.setText("新增");
        toolBar1.add(button1);
        button9 = new JButton();
        button9.setBorderPainted(false);
        button9.setFocusPainted(false);
        button9.setOpaque(false);
        button9.setText("详情");
        toolBar1.add(button9);
        button8 = new JButton();
        button8.setBorderPainted(false);
        button8.setFocusPainted(false);
        button8.setOpaque(false);
        button8.setText("保存");
        toolBar1.add(button8);
        final JSeparator separator1 = new JSeparator();
        separator1.setMaximumSize(new Dimension(10, 32767));
        separator1.setOrientation(1);
        toolBar1.add(separator1);
        button3 = new JButton();
        button3.setBorderPainted(false);
        button3.setFocusPainted(false);
        button3.setOpaque(false);
        button3.setText("查询");
        toolBar1.add(button3);
        button6 = new JButton();
        button6.setBorderPainted(false);
        button6.setFocusPainted(false);
        button6.setOpaque(false);
        button6.setText("清空");
        toolBar1.add(button6);
        final JSeparator separator2 = new JSeparator();
        separator2.setMaximumSize(new Dimension(10, 32767));
        separator2.setOrientation(1);
        toolBar1.add(separator2);
        button4 = new JButton();
        button4.setBorderPainted(false);
        button4.setFocusPainted(false);
        button4.setOpaque(false);
        button4.setText("数据导入");
        toolBar1.add(button4);
        button2 = new JButton();
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        button2.setOpaque(false);
        button2.setText("数据导出");
        toolBar1.add(button2);
        final JSeparator separator3 = new JSeparator();
        separator3.setMaximumSize(new Dimension(10, 32767));
        separator3.setOrientation(1);
        toolBar1.add(separator3);
        final Spacer spacer2 = new Spacer();
        toolBar1.add(spacer2);
        final JSeparator separator4 = new JSeparator();
        separator4.setMaximumSize(new Dimension(10, 32767));
        separator4.setOrientation(1);
        toolBar1.add(separator4);
        button5 = new JButton();
        button5.setBorderPainted(false);
        button5.setDefaultCapable(false);
        button5.setFocusPainted(false);
        button5.setFocusable(true);
        button5.setOpaque(false);
        button5.setText("删除");
        toolBar1.add(button5);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return jpanel;
    }

}
