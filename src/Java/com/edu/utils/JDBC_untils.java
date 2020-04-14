package com.edu.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC_untils {
    private static final ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql");
    /**
     * 获得连接
     *
     */
    public static Connection getConnection() throws Exception{
        Connection con = dataSource.getConnection();
        return con;
    }

    /**
     * 资源释放
     */
    public static void relese(Statement statement,Connection con){
        if(statement != null){
            try {
                statement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            statement = null;
        }
        if(con != null){
            try {
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            con = null;
        }
    }

    public static void relese(Statement statement, Connection con, ResultSet rs){
        if(statement != null){
            try {
                statement.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            statement = null;
        }
        if(con != null){
            try {
                con.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            con = null;
        }
        if(rs != null){
            try {
                rs.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            rs = null;
        }
    }
}
