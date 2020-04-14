package com.edu.dao.impl;

import com.edu.dao.UserDao;
import com.edu.domain.User;
import com.edu.utils.JDBC_untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoimpl implements UserDao {

    @Override
    public boolean login(User user) {

        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        try{
            //获得连接
            con = JDBC_untils.getConnection();
            //编写SQL
            String sql = "select username,password from user where username = ? and password = ?";
            //预编译
            pres = con.prepareStatement(sql);
            pres.setString(1,user.getUsername());
            pres.setString(2,user.getPassword());
            rs = pres.executeQuery();
            if (rs.next())
                return true;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con,rs);
        }
        return false;
    }
}
