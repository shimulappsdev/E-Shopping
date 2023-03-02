package com.example.eshopping.Retrofit;

import com.example.eshopping.Model.Item_Categorie_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface cateapi {
    @GET("getCate.php")
    Call<List<Item_Categorie_Model>> getallcete();
}
