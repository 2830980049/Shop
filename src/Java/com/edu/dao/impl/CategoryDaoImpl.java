package com.edu.dao.impl;

import com.edu.dao.CategoryDao;
import com.edu.domain.Category;
import com.edu.utils.JDBC_untils;
import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {
    @Override
    public List<Category> findAll() {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        List<Category> list = new ArrayList<>();
        try {
            con = JDBC_untils.getConnection();
            String sql = "select * from category";
            pres = con.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setCid(rs.getInt("cid"));
                category.setCname(rs.getString("cname"));
                category.setCdesc(rs.getString("cdesc"));

                list.add(category);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con,rs);
        }
        return list;
    }
}
