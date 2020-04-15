package com.edu.dao.impl;

import com.edu.dao.ProductDao;
import com.edu.domain.Product;
import com.edu.utils.JDBC_untils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
            String sql = "SELECT * FROM product p,category c where p.cid = c.cid";
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
}
