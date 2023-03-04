package com.example.eshopping.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eshopping.CartListener;
import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.R;
import com.example.eshopping.adapter.CartAdapter;
import com.example.eshopping.databinding.FragmentCartBinding;
import com.hishd.tinycart.model.Cart;
import com.hishd.tinycart.model.Item;
import com.hishd.tinycart.util.TinyCartHelper;

import java.util.ArrayList;
import java.util.Map;

public class CartFragment extends Fragment {
    double totalPrice = 0;
   int tax = 15;
    FragmentCartBinding binding;
    CartAdapter cartAdapter;
    Cart cart;
    ArrayList<Item_Product_Model> products;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(getLayoutInflater(), container, false);


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
                binding.subTotalAmount.setText(String.valueOf(cart.getTotalPrice()));
            }
        });
        binding.cartRecyclerView.setAdapter(cartAdapter);
        GridLayoutManager layoutManager =new GridLayoutManager(getActivity(),1, GridLayoutManager.HORIZONTAL,false);
        binding.cartRecyclerView.setLayoutManager(layoutManager);



        cartAdapter.notifyDataSetChanged();
        binding.subTotalAmount.setText(String.valueOf(cart.getTotalPrice()));
        totalPrice = (cart.getTotalPrice().doubleValue() * tax / 100) + cart.getTotalPrice().doubleValue();
        binding.totalAmount.setText("BDT " + totalPrice);
        super.onStart();
    }
}