package com.example.eshopping.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eshopping.R;
import com.example.eshopping.activities.EntryActivity;
import com.example.eshopping.databinding.FragmentSignInBinding;

public class SignInFragment extends Fragment {

    FragmentSignInBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater(), container, false);

        binding.signUpNowBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("signUp", "signUp");
            startActivity(intent);
        });

        return binding.getRoot();
    }
}