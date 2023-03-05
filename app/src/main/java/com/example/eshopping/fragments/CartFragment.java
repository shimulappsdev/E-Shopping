package com.example.eshopping.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eshopping.CartListener;
import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.R;
import com.example.eshopping.activities.PaymentActivity;
import com.example.eshopping.adapter.CartAdapter;
import com.example.eshopping.databinding.FragmentCartBinding;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;
import com.sslwireless.sslcommerzlibrary.model.initializer.SSLCommerzInitialization;
import com.sslwireless.sslcommerzlibrary.model.response.SSLCTransactionInfoModel;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCCurrencyType;
import com.sslwireless.sslcommerzlibrary.model.util.SSLCSdkType;
import com.sslwireless.sslcommerzlibrary.view.singleton.IntegrateSSLCommerz;
import com.sslwireless.sslcommerzlibrary.viewmodel.listener.SSLCTransactionResponseListener;

import java.util.ArrayList;
import java.util.Map;

public class CartFragment extends Fragment implements SSLCTransactionResponseListener {
    double totalPrice = 0;
   int tax = 15;
    FragmentCartBinding binding;
    CartAdapter cartAdapter;
    Cart cart;
    ArrayList<Item_Product_Model> products;

    String tran_id = "0234325443546";
    String pro_ctg = "it";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(getLayoutInflater(), container, false);

binding.CheckOut.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent1 = new Intent(getActivity().getApplication(), PaymentActivity.class);
        startActivity(intent1);
    }
});
        return binding.getRoot();
    }
    @Override
    public void onStart() {
        products =new ArrayList<>();
        cart = TinyCartHelper.getCart();
        for(Map.Entry<Item, Integer> item : cart.getAllItemsWithQty().entrySet()) {
            Item_Product_Model product = (Item_Product_Model) item.getKey();
            int quantity = item.getValue();
            product.setQuantity(quantity);
            products.add(product);
        }
        cartAdapter = new CartAdapter(getActivity(), products, new CartListener() {
            @Override
            public void subtotal() {
                binding.subTotalAmount.setText(String.format("BDT %.2f",cart.getTotalPrice()));
                totalPrice =  ((cart.getTotalPrice().intValue()* tax / 100) + cart.getTotalPrice().intValue());
                binding.totalAmount.setText("BDT " + totalPrice);
            }
        });
        binding.cartRecyclerView.setAdapter(cartAdapter);
        GridLayoutManager layoutManager =new GridLayoutManager(getActivity(),1, GridLayoutManager.HORIZONTAL,false);
        binding.cartRecyclerView.setLayoutManager(layoutManager);

        binding.subTotalAmount.setText(String.format("BDT %.2f",cart.getTotalPrice()));

        totalPrice =  ((cart.getTotalPrice().intValue()* tax / 100) + cart.getTotalPrice().intValue());
        binding.totalAmount.setText("BDT " + totalPrice);
        super.onStart();
    }
    @Override
    public void transactionSuccess(SSLCTransactionInfoModel sslcTransactionInfoModel) {

//        String price = sslcTransactionInfoModel.getAmount();
//        amount_txt.setText(price + " tk");
//        constraintLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void transactionFail(String s) {

    }

    @Override
    public void merchantValidationError(String s) {

    }
}