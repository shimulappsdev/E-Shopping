package com.example.eshopping.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.eshopping.R;
import com.example.eshopping.activities.EntryActivity;
import com.example.eshopping.databinding.FragmentSignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment {
    FragmentSignUpBinding binding;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater(), container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        binding.signUpBtn.setOnClickListener(view -> {

            String name = binding.userName.getText().toString().trim();
            String phone = binding.userPhone.getText().toString().trim();
            String email = binding.userEmail.getText().toString().trim();
            String password = binding.userPassword.getText().toString().trim();
            String rePassword = binding.userRePassword.getText().toString().trim();

            if (name.equals("")){
                binding.userName.setError("Required");
            }else if (phone.equals("")){
                binding.userPhone.setError("Required");
            }else if (email.equals("")){
                binding.userEmail.setError("Required");
            }else if (password.equals("")){
                binding.password.setError("Required");
            }else if (rePassword.equals("") || !rePassword.equals(password)){
                binding.userRePassword.setError("Not Match");
            }else {
                UserSignUp(name, phone, email, password, rePassword);
            }

        });

        binding.loginNowBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("signIn", "signIn");
            startActivity(intent);
        });

        return binding.getRoot();
    }

    private void UserSignUp(String name, String phone, String email, String password, String rePassword) {

        binding.progressBar.setVisibility(View.VISIBLE);
        binding.signUpBtn.setVisibility(View.GONE);

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                String myUser = firebaseUser.getUid();
                databaseReference = FirebaseDatabase.getInstance().getReference("user");
                Map<String,Object> userMap = new HashMap<>();
                userMap.put("user_id",myUser);
                userMap.put("user_name",name);
                userMap.put("user_email",email);
                userMap.put("user_phone",phone);
                userMap.put("user_password",password);
                userMap.put("user_rePassword",rePassword);
                userMap.put("user_profile","");
                databaseReference.child(myUser).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getActivity(), "Register Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(), EntryActivity.class);
                            intent.putExtra("signIn", "signIn");
                            startActivity(intent);
                            binding.progressBar.setVisibility(View.GONE);

                        }else {
                            ShowAlert(task.getException().getLocalizedMessage());
                        }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                binding.progressBar.setVisibility(View.GONE);
                binding.signUpBtn.setVisibility(View.VISIBLE);
                ShowAlert(e.getLocalizedMessage());
            }
        });

    }

    private void ShowAlert(String errorMsg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Caution!");
        builder.setMessage(errorMsg);
        builder.setIcon(android.R.drawable.stat_notify_error);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}