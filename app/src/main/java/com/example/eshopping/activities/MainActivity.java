package com.example.eshopping.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.eshopping.R;
import com.example.eshopping.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.homeMenu:
                        Toast.makeText(MainActivity.this, "Home Clicked", Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.loveMenu:
                        Intent intent1 = new Intent(getApplicationContext(), ContainerActivity.class);
                        intent1.putExtra("loveMenu", "loveMenu");
                        startActivity(intent1);
                        break;

                    case R.id.cartMenu:
                        Intent intent2 = new Intent(getApplicationContext(), ContainerActivity.class);
                        intent2.putExtra("cartMenu", "cartMenu");
                        startActivity(intent2);
                        break;

                    case R.id.profileMenu:
                        Intent intent3 = new Intent(getApplicationContext(), ContainerActivity.class);
                        intent3.putExtra("profileMenu", "profileMenu");
                        startActivity(intent3);
                        break;

                }

                return false;
            }
        });

    }


}