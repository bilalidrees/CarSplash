package com.example.carwash.model.utility;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;


import com.example.carwash.view.activity.BaseActivity;

import javax.annotation.Nullable;


public class ActivityNavigationUtility {
    private Activity mContext;
    private Intent mIntent;
    private ActivityOptions mOption;

    private ActivityNavigationUtility(Activity context) {
        //no instance
        mContext = context;
        mIntent = new Intent();
    }


    public static ActivityNavigationUtility navigateWith(Activity context){
        return new  ActivityNavigationUtility(context);
    }

    public ActivityNavigationUtility setData(Bundle bundle) {
        mIntent.putExtras(bundle);
        return this;
    }

    public ActivityNavigationUtility setClearStack(){
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|
                Intent.FLAG_ACTIVITY_CLEAR_TASK|
                Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return this;
    }

    public ActivityNavigationUtility setActivityOption(@Nullable ActivityOptions option){
        mOption = option;
        return this;
    }


    public void navigateTo(Class<? extends BaseActivity> toClass){
        mIntent.setClass(mContext,toClass);
        if(mOption!=null)
            mContext.startActivity(mIntent,mOption.toBundle());
        else
            mContext.startActivity(mIntent);
    }
}
