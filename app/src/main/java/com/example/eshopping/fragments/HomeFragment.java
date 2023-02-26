package com.example.eshopping.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.eshopping.activities.MainActivity;
import com.example.eshopping.adapter.Categoris_adapter;
import com.example.eshopping.adapter.Products_adapter;
import com.example.eshopping.databinding.ActivityMainBinding;
import com.example.eshopping.databinding.FragmentHomeBinding;
import com.example.eshopping.utils.Utils;

import org.imaginativeworld.whynotimagecarousel.model.CarouselItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    RequestQueue queue;
    List<Item_Categorie_Model> categorie_modelList;
    List<Item_Product_Model> product_list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

        //**************************this method is used for slider**************
        slider();

        //**************************this method is used for CATEGORIES*************
        GETCATEGORIE();
        //**************************this method is used for product*************
        product();



        return binding.getRoot();
    }

    private void product() {
        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET, Utils.GET_PRODUCT_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for (int i=0; i<response.length();i++){
                            try {
                                JSONObject jsonObject =response.getJSONObject(i);
                                String product_image = jsonObject.getString("image");
                                String product_name = jsonObject.getString("name");
                                String product_price = jsonObject.getString("price");
                                String product_description = jsonObject.getString("description");
                                int id = jsonObject.getInt("id");
                                Item_Product_Model product =new Item_Product_Model(product_image,product_name,product_price,product_description,id);
                                product_list.add(product);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i("TAG", "onResponse_product: "+product_list.size());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Can't the Internet", Toast.LENGTH_SHORT).show();

            }
        }
        );

        queue = Volley.newRequestQueue(getActivity());
        queue.add(request);

        product_list =new ArrayList<>();
        Products_adapter products_adapter = new Products_adapter(getActivity(),product_list);
        binding.productRecyclerView.setAdapter(products_adapter);
    }

    private void GETCATEGORIE() {

        JsonArrayRequest request =new JsonArrayRequest(Request.Method.GET, Utils.GET_CATEGORIES_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for (int i=0; i<response.length();i++){
                            try {
                                JSONObject jsonObject =response.getJSONObject(i);
                                String image = jsonObject.getString("icon");
                                String name = jsonObject.getString("lname");
                                Item_Categorie_Model categorie =new Item_Categorie_Model(image,name);
                                categorie_modelList.add(categorie);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.i("TAG", "onResponse_cate: "+categorie_modelList.size());

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        queue = Volley.newRequestQueue(getActivity());
        queue.add(request);

        categorie_modelList =new ArrayList<>();
        GridLayoutManager layoutManager =new GridLayoutManager(getActivity(),2, GridLayoutManager.HORIZONTAL,false);
        // StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        binding.categoryRecyclerView.setLayoutManager(layoutManager);
        Categoris_adapter categorisAdapter = new Categoris_adapter(getActivity(),categorie_modelList);
        binding.categoryRecyclerView.setAdapter(categorisAdapter);

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

        queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }

}