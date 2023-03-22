package com.example.eshopping.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eshopping.Model.Item_Product_Model;
import com.example.eshopping.adapter.Products_adapter;
import com.example.eshopping.databinding.FragmentLoveProductBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LoveProductFragment extends Fragment {

    FragmentLoveProductBinding binding;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    String currentUser;
    Products_adapter adapter;
    List<Item_Product_Model> item_product_modelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoveProductBinding.inflate(getLayoutInflater(), container, false);

        item_product_modelList = new ArrayList<>();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){
            currentUser = firebaseUser.getUid();
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("user").child(currentUser).child("loved");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                item_product_modelList.clear();

                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Item_Product_Model items = dataSnapshot.getValue(Item_Product_Model.class);
                    item_product_modelList.add(items);
                    adapter = new Products_adapter(getActivity(),item_product_modelList);
                    binding.favrecyler.setAdapter(adapter);

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();
    }
}