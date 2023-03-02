package com.example.eshopping.Retrofit;

import com.example.eshopping.Model.Item_Product_Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface productapi {
    @GET("getproduct.php")

    Call<List<Item_Product_Model>> getallproduct();

}
