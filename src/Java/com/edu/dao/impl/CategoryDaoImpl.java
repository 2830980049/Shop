package com.edu.dao.impl;

import com.edu.dao.CategoryDao;
import com.edu.domain.Category;
import com.edu.utils.JDBC_untils;
import jdk.nashorn.internal.scripts.JD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    @Override
    public void save_data(Category category) {
        Connection con = null;
        PreparedStatement pres = null;
        try{
            String sql = "insert into category values(null,?,?)";
            con = JDBC_untils.getConnection();
            pres = con.prepareStatement(sql);
            System.out.println(category.getCname()+" "+category.getCdesc());
            pres.setString(1,category.getCname());
            pres.setString(2,category.getCdesc());
            pres.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con);
        }
    }

    @Override
    public Category edit_data(String cid) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet  rs = null;
        try{
            con = JDBC_untils.getConnection();
            String sql = "select * from category where cid = ?";
            pres = con.prepareStatement(sql);
            pres.setInt(1,Integer.parseInt(cid));
            rs = pres.executeQuery();
            if(rs.next()){
                Category category = new Category();
                category.setCid(rs.getInt("cid"));
                category.setCname(rs.getString("cname"));
                category.setCdesc(rs.getString("cdesc"));
                return  category;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con,rs);
        }
        return null;
    }

    @Override
    public void update_data(Category category) {
        Connection con = null;
        PreparedStatement pres = null;
        try {
            String sql = "update category set cname = ?, cdesc = ? where cid = ?";
            con = JDBC_untils.getConnection();
            pres = con.prepareStatement(sql);
            pres.setString(1,category.getCname());
            pres.setString(2,category.getCdesc());
            pres.setInt(3,category.getCid());
            pres.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con);
        }
    }

    @Override
    public void delete_data(Integer cid) {
        Connection con = null;
        PreparedStatement pres = null;
        try {
            String sql = "delete from category where cid = ?";
            con = JDBC_untils.getConnection();
            pres = con.prepareStatement(sql);
            pres.setInt(1,cid);
            pres.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con);
        }
    }

    @Override
    public void delete_data(Connection con, Integer cid) {
        PreparedStatement pres = null;
        try {
            String sql = "delete from category where cid = ?";
            con = JDBC_untils.getConnection();
            pres = con.prepareStatement(sql);
            pres.setInt(1,cid);
            pres.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            if (pres != null){
                try {
                    pres.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
