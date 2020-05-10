package com.example.carwash.view.fragment;

import android.location.Location;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.View;


import com.example.carwash.model.dataAccess.interfaces.IFragmentUpdateListener;

import javax.annotation.Nullable;



public abstract class BaseFragment extends Fragment {



    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() != null && getActivity() instanceof IFragmentUpdateListener) {
            ((IFragmentUpdateListener) getActivity()).fragmentUpdated(this);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public abstract String getTagFragment();
    public abstract String getTitle();

}
