package com.example.roomdatabase.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.roomdatabase.R;
import com.example.roomdatabase.models.Database;
import com.example.roomdatabase.models.entities.Nature;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Nature> {

    private LayoutInflater inflater;
    private int layout;
    private List<Nature> states;
    private Database db;

    private Activity activity;
    public MyAdapter(Context context, int resource, List<Nature> states,Activity activity) {
        super(context, resource, states);
        this.db  = Database.getDatabase(context);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        this.activity = activity;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View view=inflater.inflate(this.layout, parent, false);
        TextView fishName = view.findViewById(R.id.fishName);
        TextView typeFish = view.findViewById(R.id.typeFish);
        TextView lakeName = view.findViewById(R.id.lakeName);
        TextView lakeAddress = view.findViewById(R.id.lakeAddress);

        Nature state = states.get(position);

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                int id = db.lakesDao().getLakeById(state.getLakeId()).getId();
                int idCountry = db.addressesDao().getAddressById(id).countryId;
                int idRegion = db.addressesDao().getAddressById(id).regionId;

                String fish = db.fishesDao().getFishById(state.getFishId()).getNameFish();
                String fishType = db.typesFishDao().getTypeFishById(state.getFishId()).getName();
                String nameLake = db.lakesDao().getLakeById(state.getLakeId()).getNameLake();
                String addressLake = db.countriesDao().getCountryById(idCountry).getNameCountry() +" "+db.regionsDao().getRegionById(idRegion).getNameRegion();

                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fishName.setText(fish);
                        typeFish.setText(fishType);
                        lakeName.setText(nameLake);
                        lakeAddress.setText(addressLake);

                    }
                });
                }
        });
        th.start();
        return view;
    }
    public Nature getNatureByPosition(int position )
    {
        return states.get(position);
    }
}
