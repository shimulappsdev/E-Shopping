package com.example.eshopping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.eshopping.databinding.ActivityMainBinding;
import com.example.eshopping.databinding.ActivityProductdetailactivityBinding;

public class productdetailactivity extends AppCompatActivity {
    ActivityProductdetailactivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductdetailactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        String name= getIntent().getStringExtra("name");
        String description= getIntent().getStringExtra("description");
        String image= getIntent().getStringExtra("image");
        double price= getIntent().getDoubleExtra("price",0);
        int id= getIntent().getIntExtra("id",0);

        Glide.with(this).load(image)
                .into(binding.productImage);

        binding.productprice.setText("BDT:"+price);
        binding.productdescription.setText(description);
    }
}