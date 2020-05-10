package com.example.carwash.view.activity;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.carwash.R;
import com.example.carwash.databinding.ActivityLocationPermissionBinding;
import com.example.carwash.model.essentials.AppLevelConstant;

public class LocationPermission extends BaseActivity {
    ActivityLocationPermissionBinding activityLocationPermissionBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLocationPermissionBinding = DataBindingUtil.setContentView(this, R.layout.activity_location_permission);
        checkForLocationPermission();

        activityLocationPermissionBinding.turnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(LocationPermission.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                        }, AppLevelConstant.LOCATION_PERMISSION);
            }
        });
    }

    private void checkForLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            navigateToMainScreen();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case AppLevelConstant.LOCATION_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    navigateToMainScreen();
                } else {
                    Toast.makeText(this, "The app was not allowed to access your location", Toast.LENGTH_LONG).show();
                }
        }
    }

    public void navigateToMainScreen() {
        startActivity(new Intent(LocationPermission.this, MainActivity.class));
        finish();
    }
}
