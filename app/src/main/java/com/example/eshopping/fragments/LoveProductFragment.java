package com.example.eshopping.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eshopping.databinding.FragmentLoveProductBinding;

import java.util.ArrayList;

public class LoveProductFragment extends Fragment {

    FragmentLoveProductBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoveProductBinding.inflate(getLayoutInflater(), container, false);


        return binding.getRoot();
    }
}