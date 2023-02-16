package com.example.eshopping.Model;

public class Item_Product_Model {

    String Product_Image, Product_name,Product_price;

    public Item_Product_Model(String product_Image, String product_name, String product_price) {
        Product_Image = product_Image;
        Product_name = product_name;
        Product_price = product_price;
    }

    public Item_Product_Model() {
    }

    public String getProduct_Image() {
        return Product_Image;
    }

    public void setProduct_Image(String product_Image) {
        Product_Image = product_Image;
    }

    public String getProduct_name() {
        return Product_name;
    }

    public void setProduct_name(String product_name) {
        Product_name = product_name;
    }

    public String getProduct_price() {
        return Product_price;
    }

    public void setProduct_price(String product_price) {
        Product_price = product_price;
    }
}
