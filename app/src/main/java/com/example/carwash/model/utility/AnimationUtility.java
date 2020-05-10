package com.example.carwash.model.utility;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class AnimationUtility {

    public static Animation loadAnimation(Context context,int animId){
        return AnimationUtils.loadAnimation(context, animId);
    }
}
