package com.example.carwash.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.carwash.R;
import com.example.carwash.databinding.FragmentAddCarBinding;
import com.example.carwash.model.dataAccess.adapters.CustomSpinnerAdapter;
import com.example.carwash.model.dataAccess.entities.CustomItems;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class AddCarFragment extends BaseFragment {

    FragmentAddCarBinding fragmentAddCarBinding;
    ArrayList<CustomItems> customItems=new ArrayList<>();

    public AddCarFragment() {
        // Required empty public constructor
    }


    public static AddCarFragment newInstance(String param1, String param2) {
        AddCarFragment fragment = new AddCarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentAddCarBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_car, container, false);
        return fragmentAddCarBinding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeCarData();
        CustomSpinnerAdapter customSpinnerAdapter =new CustomSpinnerAdapter()
    }

    private void initializeCarData() {
        customItems.add(new CustomItems("Mehran"));
        customItems.add(new CustomItems("Corolla"));
        customItems.add(new CustomItems("City"));
        customItems.add(new CustomItems("Cultus"));
    }

    @Override
    public String getTagFragment() {
        return null;
    }

    @Override
    public String getTitle() {
        return null;
    }
}
