package com.edu.domain;

public class Product {
    private Integer pid;
    private String pname;
    private String author;
    private Double price;
    private String description;
    private String filename;
    private String path;
    //表中分类ID 面向对象语言 如果表示商品所属分类你呢？
    private Category category = new Category();

    public Product(Integer pid, String pname, String author, Double price, String description, String filename, String path, Category category) {
        this.pid = pid;
        this.pname = pname;
        this.author = author;
        this.price = price;
        this.description = description;
        this.filename = filename;
        this.path = path;
        this.category = category;
    }

    public Product() {
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", pname='" + pname + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", filename='" + filename + '\'' +
                ", path='" + path + '\'' +
                ", category=" + category +
                '}';
    }
}

