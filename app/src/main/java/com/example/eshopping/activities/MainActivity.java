package com.example.eshopping.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.eshopping.Model.Item_Categorie_Model;
import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.R;
import com.example.eshopping.adapter.Categoris_adapter;
import com.example.eshopping.adapter.Products_adapter;
import com.example.eshopping.adapter.ViewPagerAdapter;
import com.example.eshopping.databinding.ActivityMainBinding;
import com.example.eshopping.fragments.CartFragment;
import com.example.eshopping.fragments.HomeFragment;
import com.example.eshopping.fragments.LoveProductFragment;
import com.example.eshopping.fragments.ProfileFragment;
import com.example.eshopping.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentArrayList.add(new HomeFragment());
        fragmentArrayList.add(new LoveProductFragment());
        fragmentArrayList.add(new CartFragment());
        fragmentArrayList.add(new ProfileFragment());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, fragmentArrayList);
        binding.viewpager.setAdapter(viewPagerAdapter);

        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        binding.bottomMenu.setSelectedItemId(R.id.homeMenu);
                        break;
                    case 1:
                        binding.bottomMenu.setSelectedItemId(R.id.loveMenu);
                        break;
                    case 2:
                        binding.bottomMenu.setSelectedItemId(R.id.cartMenu);
                        break;
                    case 3:
                        binding.bottomMenu.setSelectedItemId(R.id.profileMenu);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        binding.bottomMenu.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.homeMenu:
                        binding.viewpager.setCurrentItem(0);
                        break;

                    case R.id.loveMenu:
                        binding.viewpager.setCurrentItem(1);
                        break;

                    case R.id.cartMenu:
                        binding.viewpager.setCurrentItem(2);
                        break;

                    case R.id.profileMenu:
                        binding.viewpager.setCurrentItem(3);
                        break;
                }

                return true;
            }
        });


    }

}