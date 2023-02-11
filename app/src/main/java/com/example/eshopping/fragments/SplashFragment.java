package com.example.eshopping.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eshopping.R;
import com.example.eshopping.activities.EntryActivity;
import com.example.eshopping.databinding.FragmentSplashBinding;

public class SplashFragment extends Fragment {

    FragmentSplashBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashBinding.inflate(getLayoutInflater(), container, false);

        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                finally {

                    Intent intent = new Intent(getActivity(), EntryActivity.class);
                    intent.putExtra("signIn", "signIn");
                    startActivity(intent);

                }
            }
        };thread.start();

        return binding.getRoot();
    }
}