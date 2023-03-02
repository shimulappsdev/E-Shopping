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
import com.example.eshopping.Retrofit.MyRetrofit;
import com.example.eshopping.Retrofit.cateapi;
import com.example.eshopping.Retrofit.productapi;
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

import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    RequestQueue queue;
    List<Item_Categorie_Model> categorie_modelList;
    List<Item_Product_Model> product_list;
    Products_adapter products_adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater(), container, false);

        //**************************this method is used for slider**************
        slider();

        //**************************this method is used for CATEGORIES*************
        GETCATEGORIE();
        categorie_modelList =new ArrayList<>();
        //**************************this method is used for product*************
        product();
        product_list=new ArrayList<>();


        return binding.getRoot();
    }

    private void product() {
        productapi productapi = MyRetrofit.getRetrofit().create(productapi.class);

        Call<List<Item_Product_Model>> lastproduct = productapi.getallproduct();

        lastproduct.enqueue(new Callback<List<Item_Product_Model>>() {
            @Override
            public void onResponse(Call<List<Item_Product_Model>> call, retrofit2.Response<List<Item_Product_Model>> response) {
                product_list = response.body();
                if (product_list.size()>0){
                    for (Item_Product_Model product : product_list){

                    }
                }
                products_adapter =new Products_adapter(getActivity(),product_list);
                binding.productRecyclerView.setAdapter(products_adapter);
            }

            @Override
            public void onFailure(Call<List<Item_Product_Model>> call, Throwable t) {
                Toast.makeText(getActivity(), "NO Internet", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GETCATEGORIE() {
        cateapi cateapi = MyRetrofit.getRetrofit().create(cateapi.class);

        Call<List<Item_Categorie_Model>> listcate = cateapi.getallcete();

        listcate.enqueue(new Callback<List<Item_Categorie_Model>>() {
            @Override
            public void onResponse(Call<List<Item_Categorie_Model>> call, retrofit2.Response<List<Item_Categorie_Model>> response) {
                categorie_modelList = response.body();
                if (categorie_modelList.size()>0){
                    for (Item_Categorie_Model cate : categorie_modelList){

                    }
                }

                Categoris_adapter categorisAdapter = new Categoris_adapter(getActivity(),categorie_modelList);
                binding.categoryRecyclerView.setAdapter(categorisAdapter);
            }

            @Override
            public void onFailure(Call<List<Item_Categorie_Model>> call, Throwable t) {

            }
        });

        GridLayoutManager layoutManager =new GridLayoutManager(getActivity(),2, GridLayoutManager.HORIZONTAL,false);
        binding.categoryRecyclerView.setLayoutManager(layoutManager);
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