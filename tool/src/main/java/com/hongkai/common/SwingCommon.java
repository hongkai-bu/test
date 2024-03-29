package com.hongkai.common;

import com.hongkai.model.ShowTableModel;
import com.hongkai.utils.SqliteUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class SwingCommon {
    public static JButton changeIconSize(JButton button, String pictureName, int width, int height, String tip){
        button.setBounds(0,0,width,height);
        ImageIcon buttonImg=new ImageIcon("D:/DataBaseForNiHeWan/png/"+pictureName);
        //改变图片的大小
        Image temp=buttonImg.getImage().getScaledInstance(button.getWidth(), button.getHeight(), buttonImg.getImage().SCALE_DEFAULT);
        button.setIcon(new ImageIcon(temp));
        button.setIcon(buttonImg);
        button.setToolTipText(tip);    //提示
        return button;
    }

    public static JButton changeIconSize(JButton button, String pictureName, String tip){
        ImageIcon buttonImg=new ImageIcon("D:/DataBaseForNiHeWan/png/"+pictureName);
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

    public static void getTableInfo(JTable table1,JLabel label1,String sql){
        try{
            SqliteUtils sqliteUtils =new SqliteUtils(CommonConfig.dbPath);
            sqliteUtils.openConnection();
            List<Map<String,Object>> list= sqliteUtils.queryMap(sql);
            ShowTableModel showTableModel=new ShowTableModel(list);
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void FitTableColumns(JTable table) {
        JTableHeader header = table.getTableHeader(); //表头
        int rowCount = table.getRowCount(); //表格的行数
        TableColumnModel cm = table.getColumnModel(); //表格的列模型

        for (int i = 0; i < cm.getColumnCount(); i++) { //循环处理每一列
            TableColumn column = cm.getColumn(i); //第i个列对象
            int width = (int)header.getDefaultRenderer().getTableCellRendererComponent(table, column.getIdentifier(), false, false, -1, i).getPreferredSize().getWidth(); //用表头的绘制器计算第i列表头的宽度
            for(int row = 0; row<rowCount; row++){ //循环处理第i列的每一行，用单元格绘制器计算第i列第row行的单元格宽度
                int preferedWidth = (int)table.getCellRenderer(row, i).getTableCellRendererComponent(table, table.getValueAt(row, i), false, false, row, i).getPreferredSize().getWidth();
                width = Math.max(width, preferedWidth); //取最大的宽度
            }
            column.setPreferredWidth(width+table.getIntercellSpacing().width+30); //设置第i列的首选宽度
        }

        table.doLayout(); //按照刚才设置的宽度重新布局各个列
    }
}
