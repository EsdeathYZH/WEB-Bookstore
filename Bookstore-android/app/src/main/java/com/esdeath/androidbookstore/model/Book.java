package com.esdeath.androidbookstore.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

public class Book {
    @SerializedName("id")
    private int id;
    @SerializedName("bookname")
    private String bookname;
    @SerializedName("price")
    private BigDecimal price;
    @SerializedName("author")
    private String author;
    @SerializedName("amount")
    private int amount;
    @SerializedName("saleAmount")
    private int sale_amount;
    @SerializedName("introduction")
    private String introduction;
    @SerializedName("type")
    private String type;
    @SerializedName("imagePath")
    private String image_path;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getSale_amount() {
        return sale_amount;
    }

    public void setSale_amount(int sale_amount) {
        this.sale_amount = sale_amount;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }
}
