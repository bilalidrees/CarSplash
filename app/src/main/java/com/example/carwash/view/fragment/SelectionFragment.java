package com.example.carwash.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.carwash.R;
import com.example.carwash.databinding.FragmentSelectionBinding;
import com.example.carwash.model.dataAccess.adapters.CarAdapter;
import com.example.carwash.model.dataAccess.adapters.ServiceAdapter;
import com.example.carwash.model.dataAccess.entities.Car;
import com.example.carwash.model.dataAccess.entities.Order;
import com.example.carwash.model.dataAccess.entities.Service;
import com.example.carwash.model.utility.FragmentUtility;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class SelectionFragment extends BaseFragment implements View.OnClickListener {

    private String TAG = "SelectionFragment";
    FragmentSelectionBinding fragmentSelectionBinding;
    ArrayList<Car> mcarlist = new ArrayList<>();
    ArrayList<Service> serviceslist = new ArrayList<>();
    ArrayList<Service> selectedServicesList = new ArrayList<>();
    CarAdapter carAdapter;
    ServiceAdapter serviceAdapter;
    Order order;
    boolean isCarSelected =false;

    public SelectionFragment() {

    }


    public static SelectionFragment newInstance(String param1, String param2) {
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        addCars();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentSelectionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_selection, container, false);
        return fragmentSelectionBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setOnclickListners();
        order = new Order();
        fragmentSelectionBinding.carRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        carAdapter = new CarAdapter(getContext(), mcarlist, new CarAdapter.Click() {
            @Override
            public void onclick(CarAdapter.ViewHolder holder, int position) {
                if (holder instanceof CarAdapter.NormalViewHolder) {
                    isCarSelected=true;
                    order.setCar(mcarlist.get(position));
                } else {
                    FragmentUtility.withManager(getFragmentManager())
                            .addToBackStack(TAG)
                            .replaceToFragment(new ConfirmBookingFragment(order));
                }
            }
        });
        fragmentSelectionBinding.carRecyclerview.setAdapter(carAdapter);

        fragmentSelectionBinding.serviceRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        serviceAdapter = new ServiceAdapter(getContext(), serviceslist, new ServiceAdapter.Click() {
            @Override
            public void onclick(Service service, int position) {
                if (service.isChecked()) {
                    selectedServicesList.add(service);
                } else {
                    selectedServicesList.remove(service);
                }

            }
        });
        fragmentSelectionBinding.serviceRecyclerview.setAdapter(serviceAdapter);


    }

    private void setOnclickListners() {
        fragmentSelectionBinding.itemCarLayout.setOnClickListener(this);
        fragmentSelectionBinding.itemServiceLayout.setOnClickListener(this);
        fragmentSelectionBinding.saveSelection.setOnClickListener(this);
    }

    private void addCars() {
        for (int i = 0; i <= 4; i++) {
            mcarlist.add(new Car("BMW M4", "MVS 5221"));
            serviceslist.add(new Service("BodyWash", "50"));
        }
    }

    @Override
    public String getTagFragment() {
        return TAG;
    }

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.item_car_layout:
                fragmentSelectionBinding.carRecyclerview.setVisibility(View.VISIBLE);
                fragmentSelectionBinding.serviceRecyclerview.setVisibility(View.GONE);
                fragmentSelectionBinding.itemCar.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.item_layout_selected_background));
                fragmentSelectionBinding.itemService.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.item_layout_unselected_background));
                fragmentSelectionBinding.imageCar.setImageResource(R.drawable.car_wash_selected);
                fragmentSelectionBinding.imageService.setImageResource(R.drawable.car_wash_unselected);
                break;

            case R.id.item_service_layout:
                if(isCarSelected){
                    fragmentSelectionBinding.carRecyclerview.setVisibility(View.GONE);
                    fragmentSelectionBinding.serviceRecyclerview.setVisibility(View.VISIBLE);
                    fragmentSelectionBinding.itemService.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.item_layout_selected_background));
                    fragmentSelectionBinding.itemCar.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.item_layout_unselected_background));
                    fragmentSelectionBinding.imageService.setImageResource(R.drawable.car_wash_selected);
                    fragmentSelectionBinding.imageCar.setImageResource(R.drawable.car_wash_unselected);
                }
                else{
                    Toast.makeText(getContext(), "Please select Car first", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.save_selection:
                setOrderDetails();
                break;

        }
    }

    private void setOrderDetails() {
        order.setServices(selectedServicesList);
        getAmount();
        getOrderDate();
        FragmentUtility.withManager(getFragmentManager())
                .addToBackStack(TAG)
                .replaceToFragment(new ConfirmBookingFragment(order));
    }

    private void getOrderDate() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//
        Date currentTime = Calendar.getInstance().getTime();
        String strDate = sdfDate.format(currentTime);
        order.setDate(strDate);
    }

    private void getAmount() {
        int amount = 0;
        for (Service service : selectedServicesList) {
            amount += Integer.parseInt(service.getPrice());
        }
        order.setTotalAmount(String.valueOf(amount));

    }
}
