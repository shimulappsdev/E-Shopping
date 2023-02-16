package com.example.eshopping.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.eshopping.Model.User;
import com.example.eshopping.R;
import com.example.eshopping.activities.EntryActivity;
import com.example.eshopping.databinding.FragmentProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    FirebaseAuth mauth;
    FragmentProfileBinding binding;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(getLayoutInflater(), container, false);
        mauth =FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();

        //*******Logout button*************
        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mauth.signOut();
                Intent intent = new Intent(getActivity(), EntryActivity.class);
                intent.putExtra("signIn", "signIn");
                startActivity(intent);
            }
        });
        databaseReference.child(uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                binding.profileName.setText(user.getUser_name());
                binding.profileEmail.setText(user.getUser_email());
                binding.mobileTv.setText(user.getUser_phone());
                binding.textView4.setText(user.getUser_email());
                Glide.with(getActivity()).load(user.getUser_profile()).placeholder(R.drawable.imagehint).into(binding.profileImageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return binding.getRoot();
    }


}