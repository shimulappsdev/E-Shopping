package com.example.eshopping.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.databinding.ActivityProductdetailactivityBinding;
import com.example.eshopping.utils.Utils;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.util.TinyCartHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class productdetailactivity extends AppCompatActivity {
    ActivityProductdetailactivityBinding binding;
    Item_Product_Model currentproduct;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductdetailactivityBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Cart cart = TinyCartHelper.getCart();

        String image= getIntent().getStringExtra("image");
        double price= getIntent().getDoubleExtra("price",0);
        int id= getIntent().getIntExtra("id",0);
        Glide.with(this).load(image)
                .into(binding.productImage);

        getproductdetail(id);


        binding.productprice.setText("BDT:"+price);


        binding.addToCart.setOnClickListener(view -> {
            cart.addItem(currentproduct,0);
            binding.addToCart.setEnabled(false);
            binding.addToCart.setText("Added in Cart");
        });
    }

    private void getproductdetail(int id) {

       String url = Utils.GET_PRODUCT_DETAILS_URL+id;
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i<response.length();i++){
                            try {
                                JSONObject jsonObject =response.getJSONObject(i);
                                String description = jsonObject.getString("description");
                                binding.productdescription.setText(Html.fromHtml(description));
                                currentproduct =new Item_Product_Model(
                                        jsonObject.getString("name"),
                                        jsonObject.getString("image"),
                                        jsonObject.getString("status"),
                                        jsonObject.getDouble("price"),
                                        jsonObject.getDouble("price_discount"),
                                        jsonObject.getInt("stock"),
                                        jsonObject.getInt("id"));

                                //    Log.i("TAG", "onResponse: "+response);
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