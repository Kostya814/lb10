package com.example.roomdatabase.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.roomdatabase.R;
import com.example.roomdatabase.models.entities.Countries;
import com.example.roomdatabase.models.entities.Regions;

import java.util.List;

public class AdapterCountries extends ArrayAdapter<Countries> {
    private LayoutInflater inflater;
    private int layout;
    private List<Countries> states;
    public AdapterCountries(Context context, int resource, List<Countries> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(layout, parent, false);
            holder = new ViewHolder();
            holder.textView = view.findViewById(R.id.et);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Countries state = states.get(position);
        holder.textView.setText(state.getNameCountry());

        return view;
    }
    @Override
    public View getDropDownView(int position, View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
    private static class ViewHolder {
        TextView textView;
    }
}
