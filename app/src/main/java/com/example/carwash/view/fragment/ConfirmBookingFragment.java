package com.example.carwash.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.carwash.R;
import com.example.carwash.databinding.FragmentConfirmBookingBinding;
import com.example.carwash.model.dataAccess.adapters.ServiceAdapter;
import com.example.carwash.model.dataAccess.entities.Order;
import com.example.carwash.model.dataAccess.entities.Service;
import com.example.carwash.model.utility.FragmentUtility;

import javax.annotation.Nullable;

public class ConfirmBookingFragment extends BaseFragment implements View.OnClickListener {

    private String TAG = "ConfirmBookingFragment";
    private Order order;
    FragmentConfirmBookingBinding fragmentConfirmBookingBinding;
    ServiceAdapter serviceAdapter;

    public ConfirmBookingFragment(Order order) {
        this.order = order;
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
        fragmentConfirmBookingBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_confirm_booking, container, false);
        return fragmentConfirmBookingBinding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnclickListners();
        setOrderData();
    }

    private void setOrderData() {
        fragmentConfirmBookingBinding.carSelectedValue.setText(order.getCar().getName());
        fragmentConfirmBookingBinding.dateTime.setText(order.getDate());
        fragmentConfirmBookingBinding.totalAmountValue.setText(order.getTotalAmount());
        fragmentConfirmBookingBinding.serviceRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        serviceAdapter = new ServiceAdapter(getContext(), order.getServices(), new ServiceAdapter.Click() {
            @Override
            public void onclick(Service service, int position) {
            }
        });
        fragmentConfirmBookingBinding.serviceRecyclerview.setAdapter(serviceAdapter);
    }

    private void setOnclickListners() {
        fragmentConfirmBookingBinding.order.setOnClickListener(this);

    }

    @Override
    public String getTagFragment() {
        return TAG;
    }

    @Override
    public String getTitle() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.order:
                getActivity().getSupportFragmentManager().popBackStack();
                Toast.makeText(getContext(), "Your Order has been Placed", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
