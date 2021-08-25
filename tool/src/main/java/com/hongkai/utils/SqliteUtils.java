package com.hongkai.utils;

import org.sqlite.SQLiteConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqliteUtils {
    public Connection conn = null;
    public Statement statement=null;
    private String dbUrl=null;

    public SqliteUtils(String fullFilePath){
        try{
            Class.forName("org.sqlite.JDBC");
            this.dbUrl=String.format( "jdbc:sqlite:%s",fullFilePath);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void openConnection() throws SQLException {
        SQLiteConfig config=new SQLiteConfig();
        config.enableLoadExtension(true);
        conn=DriverManager.getConnection(this.dbUrl);
        statement=conn.createStatement();
    }

    public ResultSet query(String sql){
        ResultSet rs=null;
        try{
            rs=statement.executeQuery(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public boolean updateOrInsert(String sql){
        boolean rs=false;
        try{
            rs=statement.execute(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }

    public List<Map<String,Object>> queryMap(String sql){
        List<Map<String, Object>> list = new ArrayList<>();
        ResultSet rs=null;
        try{
            rs=statement.executeQuery(sql);
            ResultSetMetaData rsmd=rs.getMetaData();
            while(rs.next()){
                Map<String, Object> rowData = new HashMap<String, Object>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    rowData.put(rsmd.getColumnName(i), rs.getObject(i));
                }
                list.add(rowData);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
