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
import com.example.carwash.databinding.RowitemServiceBinding;
import com.example.carwash.model.dataAccess.entities.Car;
import com.example.carwash.model.dataAccess.entities.Service;

import java.util.ArrayList;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    public Context context;
    public Click click;
    public ArrayList<Service> serviceArrayList;
    public static int mItemSelected = -1;

    public ServiceAdapter(Context context, ArrayList<Service> contactArrayList, Click click) {
        this.context = context;
        this.click = click;
        this.serviceArrayList = contactArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RowitemServiceBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.rowitem_service, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Service service = serviceArrayList.get(position);
        holder.bind(service, holder.getAdapterPosition());
        if (serviceArrayList.get(position).isChecked()) {
            holder.binding.checkbox.setChecked(true);
            holder.binding.checkbox.setSelected(true);

        } else {
            holder.binding.checkbox.setChecked(false);
            holder.binding.checkbox.setSelected(false);
        }

        holder.binding.serviceLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                service.setChecked(!service.isChecked());
                notifyDataSetChanged();
                click.onclick(service, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return serviceArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RowitemServiceBinding binding;

        //constructor
        public ViewHolder(RowitemServiceBinding itemView) {
            super(itemView.getRoot());
            this.binding = itemView;
        }

        public void bind(Service service, int adapterPosition) {
            binding.setService(service);
        }
    }

    public interface Click {
        void onclick(Service service, int position);
    }
}
