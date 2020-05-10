package com.example.carwash.model.dataAccess.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carwash.R;
import com.example.carwash.databinding.RowitemCarBinding;
import com.example.carwash.databinding.RowitemFootercarBinding;
import com.example.carwash.model.dataAccess.entities.Car;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    public Context context;
    public Click click;
    public ArrayList<Car> carArrayList;
    private static final int FOOTER_VIEW = 1001;
    private int selectedCarPosition = -1;

    public CarAdapter(Context context, ArrayList<Car> contactArrayList, Click click) {
        this.context = context;
        this.click = click;
        this.carArrayList = contactArrayList;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == carArrayList.size()) {
            // This is where we'll add footer.
            return FOOTER_VIEW;
        }

        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == FOOTER_VIEW) {
            RowitemFootercarBinding binding = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.getContext()),
                    R.layout.rowitem_footercar, parent, false);

            return new FooterViewHolder(binding);
        }
        RowitemCarBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.rowitem_car, parent, false);
        return new NormalViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            if (holder instanceof NormalViewHolder) {
                NormalViewHolder normalViewHolder = (NormalViewHolder) holder;
                final Car user = carArrayList.get(position);
                normalViewHolder.bind(user, holder.getAdapterPosition());
                // if (carArrayList.get(position).isSelected()) {
                //   normalViewHolder.binding.radio.setChecked(true);
                //   normalViewHolder.binding.radio.setSelected(true);
                //} else {
                //   normalViewHolder.binding.radio.setChecked(false);
                //   normalViewHolder.binding.radio.setSelected(false);
                //}
                if (selectedCarPosition == position) {
                    normalViewHolder.binding.radio.setChecked(true);
                    normalViewHolder.binding.radio.setSelected(true);
                } else {
                    normalViewHolder.binding.radio.setChecked(false);
                    normalViewHolder.binding.radio.setSelected(false);
                }
                normalViewHolder.binding.selectCar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        selectedCarPosition=position;
                        notifyDataSetChanged();
                        click.onclick(normalViewHolder, position);
                    }
                });
            } else if (holder instanceof FooterViewHolder) {
                FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
                footerViewHolder.binding.addLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        click.onclick(footerViewHolder, position);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {

        if (carArrayList.size() == 0) {
            //Return 1 here to show nothing
            return 1;
        }
        // Add extra view to show the footer view
        return carArrayList.size() + 1;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        //constructor
        public ViewHolder(View itemView) {
            super(itemView);
        }

    }

    public class NormalViewHolder extends ViewHolder {
        RowitemCarBinding binding;

        //constructor
        public NormalViewHolder(RowitemCarBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Car user, int adapterPosition) {
            binding.setCar(user);
        }
    }

    public class FooterViewHolder extends ViewHolder {
        RowitemFootercarBinding binding;

        //constructor
        public FooterViewHolder(RowitemFootercarBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

    }


    public interface Click {
        void onclick(ViewHolder holder, int position);
    }
}
