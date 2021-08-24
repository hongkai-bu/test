package com.hongkai.model;

import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.util.LinkedList;

public class MyTableModel  extends AbstractTableModel {
    private int column = 4;

    private String[] columnName = null;

    private LinkedList resultSet = null;

    public MyTableModel() {
    }

    /** Creates a new instance of TableModel */

    public MyTableModel(ResultSet rs, String[] DScolumnName, String[] columnName) throws Exception {
        if (DScolumnName.length != columnName.length) {
            throw new Exception("指定JTable列和指定数据库列数不一致，无法进行数据绑定");

        }

        this.columnName = columnName;
        column = columnName.length;
        resultSet = new LinkedList();

        try {
            while (rs.next()) {
                String[] row = new String[column];
                for (int i = 0; i < column; i++) {
                    row[i] = rs.getString(DScolumnName[i]);
                }
                resultSet.add(row);
            }
        } catch (Exception e) {
          e.printStackTrace();
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * 取得总记录数
     * @return  总记录数
     */
    @Override
    public int getRowCount() {
        return resultSet.size();
    }

    /**
     * 取得总列数
     * @return  总列数
     */
    @Override
    public int getColumnCount() {
        return column;
    }

    /**
     * 取得指定行指定列数据
     * @param rowIndex      行
     * @param columnIndex   列
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] row = (String[]) resultSet.get(rowIndex);
        return row[columnIndex];
    }

    /**
     * 取得指定列名称
     * @param i  指定列
     * @return   指定列名称返回
     */
    @Override
    public String getColumnName(int i) {
        return columnName[i];
    }

}
