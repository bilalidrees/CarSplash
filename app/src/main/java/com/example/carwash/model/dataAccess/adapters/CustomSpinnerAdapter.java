package com.example.carwash.model.dataAccess.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.carwash.R;
import com.example.carwash.model.dataAccess.entities.CustomItems;

import java.util.ArrayList;

public class CustomSpinnerAdapter extends ArrayAdapter<CustomItems> {

    public CustomSpinnerAdapter(Context context, ArrayList<CustomItems> customItemsArrayList) {
        super(context, 0, customItemsArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return customView(position, convertView, parent);
    }

    public View customView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.spinner_layout,parent,false);
        }
        CustomItems items=getItem(position);
        TextView itemName=convertView.findViewById(R.id.itemName);
        if(items!=null){
            itemName.setText(items.getSpinnerText());
        }
        return  convertView;
    }
}