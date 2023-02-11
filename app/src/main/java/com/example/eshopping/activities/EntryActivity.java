package com.example.eshopping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.eshopping.R;
import com.example.eshopping.databinding.ActivityEntryBinding;
import com.example.eshopping.fragments.SignInFragment;
import com.example.eshopping.fragments.SignUpFragment;
import com.example.eshopping.fragments.SplashFragment;

public class EntryActivity extends AppCompatActivity {

    ActivityEntryBinding binding;
    SplashFragment splashFragment = new SplashFragment();
    SignUpFragment signUpFragment = new SignUpFragment();
    SignInFragment signInFragment = new SignInFragment();

    String signIn, signUp;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEntryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        intent = getIntent();
        signIn = intent.getStringExtra("signIn");
        signUp = intent.getStringExtra("signUp");

        if (signIn == null && signUp == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, splashFragment).commit();
        }

        if (signIn != null && signIn.equals("signIn")){
            getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, signInFragment).commit();
        }

        if (signUp != null && signUp.equals("signUp")){
            getSupportFragmentManager().beginTransaction().replace(R.id.entryActivity, signUpFragment).commit();
        }

    }
}