package com.example.eshopping.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item_Product_Model {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("name")
    @Expose
    private String product_name;

    @SerializedName("image")
    @Expose
    private String product_image;

    @SerializedName("description")
    @Expose
    private String product_description;

    @SerializedName("price")
    @Expose
    private String product_price;

    public Item_Product_Model(Integer id, String product_name, String product_image, String product_description, String product_price) {
        this.id = id;
        this.product_name = product_name;
        this.product_image = product_image;
        this.product_description = product_description;
        this.product_price = product_price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
