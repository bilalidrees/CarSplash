package com.example.carwash.model.utility;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.carwash.R;
import com.example.carwash.view.fragment.BaseFragment;

public class FragmentUtility {
    private BaseFragment replaceToFragment;
    private int containerId = R.id.fragment_container;//default fragment id
    private boolean isToAddToStack = false;
    private String setTag = "";
    private String backStackTag = "";
    private FragmentManager manager;

    private FragmentUtility(FragmentManager manager) {
        //no instance
        this.manager = manager;
    }


    public static FragmentUtility withManager(FragmentManager manager) {
        return new FragmentUtility(manager);
    }

    public FragmentUtility intoContainerId(int id) {
        this.containerId = id;
        return this;
    }



    public void replaceToFragment(BaseFragment fragment) {
        this.replaceToFragment = fragment;
        performTransaction(false);
    }

    public FragmentUtility setTag(String tag) {
        this.setTag = tag;
        return this;
    }

    public FragmentUtility addToBackStack(String tag) {
        isToAddToStack = true;
        backStackTag = tag;
        return this;
    }

    private void performTransaction(boolean isAdd) {
        if (manager != null && containerId != 0 && replaceToFragment != null) {
            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            setTag = replaceToFragment.getTagFragment();
            if (isAdd)
                fragmentTransaction.add(containerId, replaceToFragment, setTag);
            else
                fragmentTransaction.replace(containerId, replaceToFragment, setTag);
            if (isToAddToStack)
                fragmentTransaction.addToBackStack(backStackTag);

            try {
                fragmentTransaction.commitAllowingStateLoss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void addFragment(BaseFragment fragment) {
        this.replaceToFragment = fragment;
        performTransaction(true);
    }




    @Nullable
    public static BaseFragment getCurrentFragment(AppCompatActivity activity, int containerID) {
        Fragment fragment = activity.getSupportFragmentManager().findFragmentById(containerID);
        if (fragment instanceof BaseFragment)
            return ((BaseFragment) fragment);
        return null;
    }
}


