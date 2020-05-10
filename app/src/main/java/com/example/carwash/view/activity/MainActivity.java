package com.example.carwash.view.activity;

import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.carwash.R;
import com.example.carwash.databinding.ActivityMainBinding;
import com.example.carwash.model.dataAccess.interfaces.IFragmentUpdateListener;
import com.example.carwash.model.essentials.AppLevelConstant;
import com.example.carwash.model.essentials.SessionClass;
import com.example.carwash.model.utility.FragmentActivityHelper;
import com.example.carwash.model.utility.FragmentUtility;
import com.example.carwash.view.fragment.BaseFragment;
import com.example.carwash.view.fragment.SelectionFragment;

public class MainActivity extends BaseActivity implements IFragmentUpdateListener, FragmentActivityHelper.IActivityContract {

    ActivityMainBinding activityMainBinding;
    FragmentActivityHelper fragmentActivityHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        fragmentActivityHelper = new FragmentActivityHelper(this);
        setFragment();
    }

    private void setFragment() {
        FragmentUtility.withManager(getSupportFragmentManager())
                .intoContainerId(R.id.fragment_container)
                .replaceToFragment( new SelectionFragment());
    }

    @Override
    public void fragmentUpdated(BaseFragment fragment) {
        fragmentActivityHelper.fragmentUpdated(fragment);
    }

    @Override
    public int getHeaderTitleId() {
        return 0;
    }

    @Override
    public int getRightIconId() {
        return 0;
    }

    @Override
    public int getRightTwoIconId() {
        return 0;
    }

    @Override
    public int getLeftIcon() {
        return 0;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mAuth.signOut();
        SessionClass.getSession().setUser(this, null, AppLevelConstant.USER_KEY);
    }
}
