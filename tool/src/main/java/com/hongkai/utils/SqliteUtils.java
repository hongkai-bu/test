package com.hongkai.utils;

import org.sqlite.SQLiteConfig;

import java.sql.*;

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
}
