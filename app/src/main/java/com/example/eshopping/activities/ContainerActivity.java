package com.example.eshopping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.eshopping.R;
import com.example.eshopping.fragments.CartFragment;
import com.example.eshopping.fragments.LoveProductFragment;
import com.example.eshopping.fragments.ProfileFragment;

public class ContainerActivity extends AppCompatActivity {

    LoveProductFragment loveProductFragment = new LoveProductFragment();
    CartFragment cartFragment = new CartFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    Intent intent;
    String loveMenu, cartMenu, profileMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        intent = getIntent();
        loveMenu = intent.getStringExtra("loveMenu");
        cartMenu = intent.getStringExtra("cartMenu");
        profileMenu = intent.getStringExtra("profileMenu");

        if (loveMenu != null){
            if (loveMenu.equals("loveMenu")){
                getSupportFragmentManager().beginTransaction().replace(R.id.containerActivity, loveProductFragment).commit();
            }
        }

        if (cartMenu != null){
            if (cartMenu.equals("cartMenu")){
                getSupportFragmentManager().beginTransaction().replace(R.id.containerActivity, cartFragment).commit();
            }
        }

        if (profileMenu != null){
            if (profileMenu.equals("profileMenu")){
                getSupportFragmentManager().beginTransaction().replace(R.id.containerActivity, profileFragment).commit();
            }
        }

    }
}