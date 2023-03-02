package com.example.eshopping.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item_Categorie_Model {

    @SerializedName("lname")
    @Expose
    private String cate_name;

    @SerializedName("icon")
    @Expose
    private String cate_image;

    public Item_Categorie_Model(String cate_name, String cate_image) {
        this.cate_name = cate_name;
        this.cate_image = cate_image;
    }

    public String getCate_name() {
        return cate_name;
    }

    public void setCate_name(String cate_name) {
        this.cate_name = cate_name;
    }

    public String getCate_image() {
        return cate_image;
    }

    public void setCate_image(String cate_image) {
        this.cate_image = cate_image;
    }
}
