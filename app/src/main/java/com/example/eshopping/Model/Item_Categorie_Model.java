package com.example.eshopping.Model;

public class Item_Categorie_Model {
	String Image, name;

    public Item_Categorie_Model(String image, String name) {
        Image = image;
        this.name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
