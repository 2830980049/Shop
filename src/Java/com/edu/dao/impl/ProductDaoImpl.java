package com.edu.dao.impl;

import com.edu.dao.ProductDao;
import com.edu.domain.Product;
import com.edu.utils.JDBC_untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    @Override
    public List<Product> findAll() {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<Product>();
        try{
            con = JDBC_untils.getConnection();
            String sql = "SELECT * FROM product p,category c where p.cid = c.cid order by p.pid desc";
            pres = con.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setAuthor(rs.getString("author"));
                product.setDescription(rs.getString("description"));
                product.setFilename(rs.getString("filename"));
                product.setPath(rs.getString("path"));
                product.setPrice(rs.getDouble("price"));
                product.getCategory().setCid(rs.getInt("cid"));
                product.getCategory().setCname(rs.getString("cname"));
                product.getCategory().setCdesc(rs.getString("cdesc"));
                list.add(product);
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
    public void save_data(Product product) {
        Connection con = null;
        PreparedStatement pres = null;
        try {
            con = JDBC_untils.getConnection();
            String sql = "insert into product values(null,?,?,?,?,?,?,?)";
            pres = con.prepareStatement(sql);
            pres.setString(1,product.getPname());
            pres.setString(2,product.getAuthor());
            pres.setDouble(3,product.getPrice());
            pres.setString(4,product.getDescription());
            pres.setString(5,product.getFilename());
            pres.setString(6,product.getPath());
            pres.setInt(7,product.getCategory().getCid());
            int num = pres.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con);
        }
    }

    @Override
    public Product edit_data(Integer pid) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        try {
            Product product = new Product();
            con = JDBC_untils.getConnection();
            String sql = "select * from product p,category c where p.cid = c.cid and pid = ?";
            pres = con.prepareStatement(sql);
            pres.setInt(1,pid);
            rs = pres.executeQuery();
            if(rs.next()) {
                product.setPid(pid);
                product.setPname(rs.getString("pname"));
                product.setAuthor(rs.getString("author"));
                product.setPrice(rs.getDouble("price"));
                product.setDescription(rs.getString("description"));
                product.setFilename(rs.getString("filename"));
                product.setPath(rs.getString("path"));
                product.getCategory().setCid(rs.getInt("cid"));
                product.getCategory().setCname(rs.getString("cname"));
                product.getCategory().setCdesc(rs.getString("cdesc"));
                return product;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con,rs);
        }
        return null;
    }

    @Override
    public void update_data(Product product) {
        Connection con = null;
        PreparedStatement pres = null;
        try{
            con = JDBC_untils.getConnection();
            String sql = "update product set pname = ?,author = ?,price = ?,description = ?,filename = ?, path = ?,cid = ? where pid = ?";
            pres = con.prepareStatement(sql);
            pres.setString(1,product.getPname());
            pres.setString(2,product.getAuthor());
            pres.setDouble(3,product.getPrice());
            pres.setString(4,product.getDescription());
            pres.setString(5,product.getFilename());
            pres.setString(6,product.getPath());
            pres.setObject(7,product.getCategory().getCid());
            pres.setInt(8,product.getPid());
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
    public void delete_data(Integer pid) {
        Connection con = null;
        PreparedStatement pres = null;
        try{
            con = JDBC_untils.getConnection();
            String sql = "delete from product where pid = ?";
            pres = con.prepareStatement(sql);
            pres.setInt(1,pid);
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
    public List<Product> findCid(Integer cid) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        List<Product> list = new ArrayList<Product>();
        try{
            con = JDBC_untils.getConnection();
            String sql = "SELECT * FROM product p,category c where p.cid = c.cid and p.cid = ? order by p.pid desc";
            pres = con.prepareStatement(sql);
            pres.setInt(1,cid);
            rs = pres.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setAuthor(rs.getString("author"));
                product.setDescription(rs.getString("description"));
                product.setFilename(rs.getString("filename"));
                product.setPath(rs.getString("path"));
                product.setPrice(rs.getDouble("price"));
                product.getCategory().setCid(rs.getInt("cid"));
                product.getCategory().setCname(rs.getString("cname"));
                product.getCategory().setCdesc(rs.getString("cdesc"));
                list.add(product);
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
    public void update_data(Connection con, Product product) {
        PreparedStatement pres = null;
        try{
            String sql = "update product set pname = ?,author = ?,price = ?,description = ?,filename = ?, path = ?,cid = ? where pid = ?";
            pres = con.prepareStatement(sql);
            pres.setString(1,product.getPname());
            pres.setString(2,product.getAuthor());
            pres.setDouble(3,product.getPrice());
            pres.setString(4,product.getDescription());
            pres.setString(5,product.getFilename());
            pres.setString(6,product.getPath());
            pres.setObject(7,product.getCategory().getCid());
            pres.setInt(8,product.getPid());
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
                pres = null;
            }
        }
    }

    @Override
    public Integer findCount() {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        Integer count = 0;
        try {
            Product product = new Product();
            con = JDBC_untils.getConnection();
            String sql = "select count(*) as count from product";
            pres = con.prepareStatement(sql);
            rs = pres.executeQuery();
            if(rs.next()) {
                count = rs.getInt("count");
                return count;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            JDBC_untils.relese(pres,con,rs);
        }
        return count;
    }

    @Override
    public List<Product> findByPage(Integer begin, Integer limit) {
        Connection con = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        List<Product> list = null;
        try{
            list = new ArrayList<Product>();
            con = JDBC_untils.getConnection();
            String sql = "SELECT * FROM product limit ?,?";
            pres = con.prepareStatement(sql);
            pres.setInt(1,begin);
            pres.setInt(2,limit);
            rs = pres.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setAuthor(rs.getString("author"));
                product.setDescription(rs.getString("description"));
                product.setFilename(rs.getString("filename"));
                product.setPath(rs.getString("path"));
                product.setPrice(rs.getDouble("price"));
                list.add(product);
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
