package com.hongkai.start;

import com.hongkai.common.CommonConfig;
import com.hongkai.common.SwingCommon;
import com.hongkai.model.ShowTableModel;
import com.hongkai.utils.SqliteUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;


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
    private JTextField textField14;
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
    private JLabel label22;
    private JLabel label23;
    private JLabel label1;
    private JButton button8;
    private JButton button9;
    private JButton button10;
    private JLabel label24;
    private JFileChooser fileChooser;

    public static JFrame frameWindow;
    public DefaultTableModel tableModel;

    public Start(){
        fileChooser=new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);


        tableModel=new DefaultTableModel(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };


        frameWindow=new JFrame("???????????????????????????????????????????????????");
        ImageIcon imageIcon=new ImageIcon(ClassLoader.getSystemResource("png").getPath()+"/picture.png");
        frameWindow.setIconImage(imageIcon.getImage());

        frameWindow.setContentPane(jpanel);
        frameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameWindow.pack();
        frameWindow.setVisible(true);
        frameWindow.setSize(1200,1000);
        frameWindow.setLocationRelativeTo(null);

        style();
        getTableInfo();
        event();
    }

    private void style() {
        SwingCommon.changeIconSize(button1,"Add.png","??????");
        SwingCommon.changeIconSize(button2,"FolderUp.png","????????????");
        SwingCommon.changeIconSize(button3,"Search.png","??????");
        SwingCommon.changeIconSize(button4,"FolderAdd.png","????????????");
        SwingCommon.changeIconSize(button5,"Remove.png","??????");
        SwingCommon.changeIconSize(button6,"DatabaseAdd.png","??????dbf");
        SwingCommon.changeIconSize(button7,"ImageAdd.png","????????????");
        SwingCommon.changeIconSize(button8,"Cd.png","??????");
        SwingCommon.changeIconSize(button9,"NoteEdit.png","??????");
        SwingCommon.changeIconSize(button10,"ImageNext.png","?????????");

        SwingCommon.changeBorder(textArea1);
        SwingCommon.changeBorder(textArea2);

        SwingCommon.changeFont(button1,16);
        SwingCommon.changeFont(button2,16);
        SwingCommon.changeFont(button3,16);
        SwingCommon.changeFont(button4,16);
        SwingCommon.changeFont(button5,16);
        SwingCommon.changeFont(button6,14);
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
        SwingCommon.changeFont(label22,12);
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
        SwingCommon.changeFont(textField14,12);
        SwingCommon.changeFont(textField15,12);
        SwingCommon.changeFont(textField16,12);
        SwingCommon.changeFont(textField17,12);


        SwingCommon.changeFont(comboBox1,12);
        SwingCommon.changeFont(comboBox2,12);

        comboBox1.addItem("??????");
        comboBox1.addItem("??????");
        comboBox1.addItem("??????");
        comboBox1.addItem("??????");
        comboBox1.addItem("??????");
        comboBox1.addItem("??????");

        comboBox2.addItem("??????");
        comboBox2.addItem("??????");
        comboBox2.addItem("??????");
        comboBox2.addItem("??????");
        comboBox2.addItem("??????");
    }



    private void getTableInfo(){
        try{
            SqliteUtils sqliteUtils =new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils.openConnection();
            ResultSet list= sqliteUtils.query("select * from record");
            ShowTableModel showTableModel=new ShowTableModel(list);
            table1.setModel(showTableModel);
            table1.setRowHeight(30);

            SwingCommon.FitTableColumns(table1);

            Dimension size = table1.getTableHeader().getPreferredSize();
            size.height = 32;//????????????????????????32
            table1.getTableHeader().setPreferredSize(size);
            table1.getTableHeader().setBackground(new Color(155, 221, 255));
            table1.getTableHeader().setFont(new Font("????????????", Font.BOLD, 12));
            table1.getTableHeader().setReorderingAllowed(false);
            table1.getTableHeader().setResizingAllowed(false);
//            SwingCommon.FitTableColumns(table1);
        }catch (Exception e){

        }


//        tableModel.setRowCount(0);
//        tableModel.setColumnIdentifiers(new Object[]{"??????","????????????","????????????"});
//        tableModel.addRow(new Object[]{1,"D:\\Downloads","5???1?????????"});
//        tableModel.addRow(new Object[]{2,"D:\\Downloads","5???2?????????"});
//        tableModel.addRow(new Object[]{3,"D:\\Downloads","5???3?????????"});
//        tableModel.addRow(new Object[]{4,"D:\\Downloads","5???4?????????"});
//        table1.setModel(tableModel);
//        table1.setRowHeight(30);
    }

    private void event() {

//        button1.addActionListener((ActionEvent e)->{
//            frameWindow.setEnabled(false);
//            new Add();
//        });

        button5.addActionListener(e->{
            Object[] options = {"??????","??????"};
            int response=JOptionPane.showOptionDialog(jpanel, "???????????????", "???????????????",JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if(response==0) {
                int[] selRow = table1.getSelectedRows();
                int count=selRow.length-1;
                for (int i = count; i >= 0; i--) {
                    tableModel.removeRow(selRow[i]);
                }
            }
        });



        pictureJPanel.setLayout(null);
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("png").getPath() + "/test.png");
        pictureJPanel.setOpaque(false);//????????????????????????
        JLabel label = new JLabel(icon,JLabel.CENTER);
        label.setBounds(50, 10, icon.getIconWidth(), icon.getIconHeight());

//        JLabel labelPicture = new JLabel();
//        labelPicture.setText("??????5???????????????1???");
//        labelPicture.setBounds(0, 0, 200, 20);

        pictureJPanel.add(label);
//        pictureJPanel.add(labelPicture);
        pictureJScollPane.setBounds(0,0, icon.getIconWidth(), icon.getIconHeight());
        pictureJPanel.setPreferredSize(new Dimension(pictureJScollPane.getWidth()+200, pictureJScollPane.getHeight()));
        pictureJPanel.revalidate();
    }


    public static void main(String[] args) {
        new Start();
    }
}
