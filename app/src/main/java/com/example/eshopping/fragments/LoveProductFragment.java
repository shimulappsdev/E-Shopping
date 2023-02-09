package com.example.eshopping.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eshopping.R;
import com.example.eshopping.databinding.FragmentLoveProductBinding;

public class LoveProductFragment extends Fragment {

    FragmentLoveProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoveProductBinding.inflate(getLayoutInflater(), container, false);



        return binding.getRoot();
    }
}