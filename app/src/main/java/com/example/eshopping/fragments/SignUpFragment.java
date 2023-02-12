package com.example.eshopping.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    DatabaseReference userReference;
    FragmentSignUpBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSignUpBinding.inflate(getLayoutInflater(), container, false);
        mAuth = FirebaseAuth.getInstance();
        userReference = FirebaseDatabase.getInstance().getReference("user");

 //     ***************This button Wrok for if User has already an account***************
        binding.loginNowBtn.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), EntryActivity.class);
            intent.putExtra("signIn", "signIn");
            startActivity(intent);
        });
//           ***************Signup button***************
        binding.signUpBtn.setOnClickListener(view -> {
            binding.signUpBtn.setEnabled(false);
            binding.signUpBtn.setText("Processing....");
            usersignup();
        });


        return binding.getRoot();
    }

    private void usersignup() {
        String name= binding.userName.getText().toString();
        String phone= binding.userPhone.getText().toString();
        String email= binding.userEmail.getText().toString();
        String password= binding.userPassword.getText().toString();
        String re_password= binding.userRePassword.getText().toString();

        if (name.equals("")){
            binding.userName.setError("Name can't be empty");
        }
        if (phone.length()>14){
            binding.userPhone.setError("Invalid Number");
        }
        if (email.equals("")){
            binding.userEmail.setError("Email can't be empty");
        }
        if (password.equals("")){
            binding.userPassword.setError("Password don't match");
        }
        else {
            registerUser(name,phone,email,password);

        }
    }

    private void registerUser(String name, String phone, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(authResult -> {
            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
            String uid = firebaseUser.getUid();
            Log.i("TAG", "onCreateView: "+"uid create");
            Map<String, Object> userMap = new HashMap<>();
            userMap.put("user_id", uid);
            userMap.put("user_name", name);
            userMap.put("user_phone", phone);
            userMap.put("user_email", email);
            userMap.put("user_profile", "");
            userMap.put("user_password", password);
            Log.i("TAG", "onCreateView: "+" data added....");
           userReference.child(uid).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
               @Override
               public void onComplete(@NonNull Task<Void> task) {
                   if (task.isSuccessful()) {
                       Intent intent = new Intent(getActivity(), EntryActivity.class);
                       intent.putExtra("signIn", "signIn");
                       startActivity(intent);
                   } else {

                       showAlert(task.getException().getLocalizedMessage());
                   }
               }
           });
        }).addOnFailureListener(e -> {
            showAlert(e.getLocalizedMessage());
        });

    }



    private void showAlert(String localizedMessage) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Warning!");
        builder.setMessage(localizedMessage);
        builder.setIcon(android.R.drawable.stat_notify_error);

        builder.setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();

            }
        });
        builder.show();
    }
}