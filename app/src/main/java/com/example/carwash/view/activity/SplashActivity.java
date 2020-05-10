package com.example.carwash.view.activity;

import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;

import com.example.carwash.R;
import com.example.carwash.databinding.ActivitySplashBinding;
import com.example.carwash.model.essentials.AppLevelConstant;
import com.example.carwash.model.utility.AnimationUtility;

public class SplashActivity extends BaseActivity {

    ActivitySplashBinding activitySplashBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySplashBinding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
        Animation animation= AnimationUtility.loadAnimation(this,R.anim.animation_scale);
        animation.reset();
        activitySplashBinding.tvTagLine.clearAnimation();
        activitySplashBinding.tvTagLine.startAnimation(animation);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }, 5*1000);
    }
}
