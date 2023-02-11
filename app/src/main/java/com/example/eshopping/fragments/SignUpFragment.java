package com.example.eshopping.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eshopping.R;
import com.example.eshopping.activities.EntryActivity;
import com.example.eshopping.databinding.FragmentSignUpBinding;

public class SignUpFragment extends Fragment {

    FragmentSignUpBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater(), container, false);

        binding.loginNowBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("signIn", "signIn");
            startActivity(intent);
        });

        return binding.getRoot();
    }
}