package com.example.carwash.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.os.Bundle;

import com.example.carwash.R;
import com.example.carwash.model.dataAccess.network.FirebaseRefrence;
import com.google.firebase.auth.FirebaseAuth;

public class BaseActivity extends AppCompatActivity {
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initializeFirebaseRefrence();
    }

    private void initializeFirebaseRefrence() {
        mAuth = FirebaseRefrence.getFirebaseInnstance();
    }

}
