package com.example.eshopping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eshopping.R;
import com.example.eshopping.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



    }
}