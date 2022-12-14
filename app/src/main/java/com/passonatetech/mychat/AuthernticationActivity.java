package com.passonatetech.mychat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.passonatetech.mychat.databinding.ActivityAuthernticationBinding;

import java.util.Locale;

public class AuthernticationActivity extends AppCompatActivity {
    ActivityAuthernticationBinding binding;

    String name, email, password;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthernticationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //name=binding.name.getText().toString();
                email = binding.email.getText().toString();
                password = binding.password.getText().toString();
                Toast.makeText(AuthernticationActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                login();
            }
        });
        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.name.getText().toString();
                email = binding.email.getText().toString();
                password = binding.password.getText().toString();
                Toast.makeText(AuthernticationActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                signUp();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(AuthernticationActivity.this, MainActivity.class));
            finish();
        }
    }


    private void login() {
        FirebaseAuth
                .getInstance()
                .signInWithEmailAndPassword(email.trim(), password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(AuthernticationActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AuthernticationActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }

    private void signUp() {
        FirebaseAuth
                .getInstance()
                .createUserWithEmailAndPassword(email.trim(), password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(AuthernticationActivity.this, "SignUp Success", Toast.LENGTH_SHORT).show();
                        UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder().setDisplayName(name).build();
                        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser.updateProfile(userProfileChangeRequest);
                        UserModel userModel = new UserModel(FirebaseAuth.getInstance().getUid(), name, email, password);
                        databaseReference.child(FirebaseAuth.getInstance().getUid()).setValue(userModel);
                        startActivity(new Intent(AuthernticationActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }
}

