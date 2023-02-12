package com.example.eshopping.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.eshopping.R;
import com.example.eshopping.databinding.ActivityMainBinding;
import com.example.eshopping.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    RequestQueue queue;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
   //**************************this method is used for slider**************
        slider();



        binding.bottomMenu.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.homeMenu:

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

    private void slider() {
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET, Utils.GET_OFFERS_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i<response.length();i++){
                            try {
                                JSONObject jsonObject =response.getJSONObject(i);
                                String image = jsonObject.getString("n_image");
                               binding.carousel.addData(new CarouselItem(image));
                                 //   Log.i("TAG", "onResponse: "+image);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", "onErrorResponse: "+error.toString());
            }
        }
        );

        queue = Volley.newRequestQueue(this);
        queue.add(request);
    }


}