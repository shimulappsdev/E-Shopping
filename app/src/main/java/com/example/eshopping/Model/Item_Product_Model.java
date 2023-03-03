package com.example.eshopping.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hishd.tinycart.model.Item;

import java.io.Serializable;
import java.math.BigDecimal;

public class Item_Product_Model implements  Item, Serializable {
    @SerializedName("name")
    @Expose
    private String product_name;

    @SerializedName("image")
    @Expose
    private String product_image;
    @SerializedName("status")
    @Expose
    private String product_status;

    @SerializedName("price")
    @Expose
    private double product_price;

    @SerializedName("price_discount")
    @Expose
    private double product_price_discount;

    @SerializedName("stock")
    @Expose
    private int product_stock;

    @SerializedName("id")
    @Expose
    private int id;
    private int quantity;

    public Item_Product_Model(String product_name, String product_image, String product_status, double product_price, double product_price_discount, int product_stock, int id) {
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_status = product_status;
        this.product_price = product_price;
        this.product_price_discount = product_price_discount;
        this.product_stock = product_stock;
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    public String getProduct_status() {
        return product_status;
    }

    public void setProduct_status(String product_status) {
        this.product_status = product_status;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public double getProduct_price_discount() {
        return product_price_discount;
    }

    public void setProduct_price_discount(double product_price_discount) {
        this.product_price_discount = product_price_discount;
    }

    public int getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(int product_stock) {
        this.product_stock = product_stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public BigDecimal getItemPrice() {
        return new BigDecimal(product_price);
    }

    @Override
    public String getItemName() {
        return product_name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
