package com.example.eshopping.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eshopping.OfflineStorage;
import com.example.eshopping.R;
import com.example.eshopping.adapter.favadapter;
import com.example.eshopping.databinding.FragmentLoveProductBinding;

import java.util.ArrayList;

public class LoveProductFragment extends Fragment {

    FragmentLoveProductBinding binding;
    ArrayList<String> favlist =new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoveProductBinding.inflate(getLayoutInflater(), container, false);


        return binding.getRoot();
    }

    @Override
    public void onStart() {

        OfflineStorage offlineStorage =new OfflineStorage(getActivity());
        favlist=  offlineStorage.getListString("fav");
        if (!favlist.isEmpty()){
            LinearLayoutManager layoutManager =new LinearLayoutManager(getActivity());
            favadapter favadapter = new favadapter(getActivity(),favlist);
            binding.favrecyler.setLayoutManager(layoutManager);
            binding.favrecyler.setAdapter(favadapter);

        }

        else {

            Toast.makeText(getActivity() , "No item has been added", Toast.LENGTH_SHORT).show();
        }
        super.onStart();
    }
}