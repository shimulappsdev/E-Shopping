package com.example.eshopping.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eshopping.R;
import com.example.eshopping.activities.EntryActivity;
import com.example.eshopping.activities.MainActivity;
import com.example.eshopping.databinding.FragmentSignInBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignInFragment extends Fragment {
    FirebaseUser currentUser;
    FragmentSignInBinding binding;
    FirebaseAuth mauth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignInBinding.inflate(getLayoutInflater(), container, false);
        mauth = FirebaseAuth.getInstance();
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        //**********if user already sign up the active redirect to Mainactive*********
        if (currentUser!=null){
            startActivity(new Intent(getActivity(), MainActivity.class));

        }
//****************This Button wrok for if user don't have any account*************************
        binding.signUpNowBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("signUp", "signUp");
            startActivity(intent);
        });

//*****************Sign User***********************

        binding.signInBtn.setOnClickListener(view -> {
            String log_email= binding.userEmail.getText().toString();
            String log_password= binding.userPassword.getText().toString();
            if (log_email.equals("")){
                binding.userEmail.setError("Email can't be empty");
            }
            if (log_password.equals("")){
                binding.userPassword.setError("Password can't be empty");
            }
            else {
                login_user(log_email,log_password);

            }
        });


        return binding.getRoot();
    }

    private void login_user(String log_email, String log_password) {
    mauth.signInWithEmailAndPassword(log_email,log_password).addOnSuccessListener(authResult -> {
        startActivity(new Intent(getActivity(), MainActivity.class));

    }).addOnFailureListener(e -> {
        Toast.makeText(getActivity(), "Login Fail! Try aging", Toast.LENGTH_SHORT).show();
    });

    }
}