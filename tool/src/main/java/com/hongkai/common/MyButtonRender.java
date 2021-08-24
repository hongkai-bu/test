package com.hongkai.common;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MyButtonRender extends AbstractCellEditor implements TableCellRenderer, ActionListener, TableCellEditor {
    private static final long serialVersionUID = 1L;
    private JButton button = null;

    public MyButtonRender() {
        button = new JButton("删除");
        button.addActionListener(this);
    }

    @Override
    public Object getCellEditorValue() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        // 创建用于返回的渲染组件
        JCheckBox ck = new JCheckBox();
        // 使具有焦点的行对应的复选框选中
        ck.setSelected(isSelected);
        //设置背景颜色  这里是设置jcheckbox的背景颜色   直接设置为透明，我这里是用了一种明暗交替的颜色转换，所以背景颜色设置了一下
        ck.setOpaque(false);
        // 设置单选box.setSelected(hasFocus);
        // 使复选框在单元格内居中显示
        ck.setHorizontalAlignment((int) 0.5f);
        return ck;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(null, "渲染器学期", "消息", JOptionPane.OK_OPTION);

    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        // TODO Auto-generated method stub
        return button;
    }
}
