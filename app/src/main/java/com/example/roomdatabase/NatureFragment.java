package com.example.roomdatabase;

import android.app.Dialog;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.roomdatabase.adapters.AdapterCountries;
import com.example.roomdatabase.adapters.AdapterRegion;
import com.example.roomdatabase.adapters.MyAdapter;
import com.example.roomdatabase.models.Database;
import com.example.roomdatabase.models.entities.Addresses;
import com.example.roomdatabase.models.entities.Countries;
import com.example.roomdatabase.models.entities.Fishes;
import com.example.roomdatabase.models.entities.Lakes;
import com.example.roomdatabase.models.entities.Nature;
import com.example.roomdatabase.models.entities.Regions;
import com.example.roomdatabase.models.entities.TypesFish;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NatureFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NatureFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Database db;

    private List<Nature> natures;

    public NatureFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NatureFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NatureFragment newInstance(String param1, String param2) {
        NatureFragment fragment = new NatureFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_nature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button createBtn = view.findViewById(R.id.createBtn);

        ListView lw = view.findViewById(R.id.lw);
        Runnable search=()->{
            db = Database.getDatabase(getContext());
            /*db.regionsDao().insert(new Regions("Иркутская область"));
            db. countriesDao().insert(new Countries("Россия"));
            db.addressesDao().insert(new Addresses(2,2));
            db.typesFishDao().insert(new TypesFish("Озерные"));
            db.lakesDao().insert(new Lakes("Байкал", 2));
            db.fishesDao().insert(new Fishes("Окунь",1));
            db.natureDao().insert(new Nature(1,2));*/
            natures = db.natureDao().getAllNatures();
            MyAdapter adapter = new MyAdapter(getContext(),R.layout.list_item, natures,getActivity());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    lw.setAdapter(adapter);
                }
            });
};

        Thread treadbd = new Thread(search);
        treadbd.start();
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNature(view,(MyAdapter)lw.getAdapter());
            }
        });
        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                changeDialog(view,(MyAdapter)lw.getAdapter(),position);
            }
        });
        AdapterView.OnItemLongClickListener listener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                MyAdapter adapter =  (MyAdapter)lw.getAdapter();

                Nature mature =  adapter.getNatureByPosition(position);
                natures.remove(mature);
                adapter.notifyDataSetChanged();
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        db.natureDao().delete(mature);

                    }
                });
                th.start();
                return true;
            }
        };
        lw.setOnItemLongClickListener(listener);
    }
    private void changeDialog(View view,MyAdapter adapter,int position)
    {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_edit);

        Nature nature = adapter.getNatureByPosition(position);
        Button btnok = (Button) dialog.findViewById(R.id.btnok);
        Spinner spinnerCountry = dialog.findViewById(R.id.country);
        Spinner spinnerRegion = dialog.findViewById(R.id.region);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nature nature = adapter.getNatureByPosition(position);
                Regions regions = (Regions)spinnerRegion.getItemAtPosition(spinnerRegion.getSelectedItemPosition());
                Countries countries = (Countries)spinnerCountry.getItemAtPosition(spinnerCountry.getSelectedItemPosition());
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Addresses addresses = db.addressesDao().getAddressById(db.lakesDao().getLakeById(nature.getLakeId()).getAddressId());
                        addresses.setCountryId(countries.getId());
                        addresses.setRegionId(regions.getId());
                        db.addressesDao().update(addresses);

                    }
                });
                th.start();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        Button btncn = (Button) dialog.findViewById(R.id.btncn);
        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        Thread treadbd = new Thread(new Runnable() {
            @Override
            public void run() {
               AdapterRegion adapterRegion = new AdapterRegion(getContext(),R.layout.spnner_item,db.regionsDao().getAllRegions());
               AdapterCountries adapterCountries = new AdapterCountries(getContext(),R.layout.spnner_item,db.countriesDao().getAllCountries());
               spinnerRegion.setAdapter(adapterRegion);
               spinnerCountry.setAdapter(adapterCountries);
            }
        });
        treadbd.start();
        dialog.show();
    }
    private void createNature(View view, MyAdapter adapter)
    {
        final Dialog dialog = new Dialog(view.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_edit);

        EditText etName = dialog.findViewById(R.id.etNameLake);
        Button btnok = (Button) dialog.findViewById(R.id.btnok);
        Spinner spinnerCountry = dialog.findViewById(R.id.country);
        Spinner spinnerRegion = dialog.findViewById(R.id.region);

        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regions regions = (Regions)spinnerRegion.getItemAtPosition(spinnerRegion.getSelectedItemPosition());
                Countries countries = (Countries)spinnerCountry.getItemAtPosition(spinnerCountry.getSelectedItemPosition());
                Thread th = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (etName.getText().equals("")) return;
                        Addresses addresses = new Addresses(countries.getId(),regions.getId());
                        int idAddress = (int)db.addressesDao().insert(addresses);
                        int idLake = (int)db.lakesDao().insert(new Lakes(etName.getText().toString(),idAddress));
                        Nature nature = new Nature(1,idLake);
                        db.natureDao().insert(nature);
                        natures.add(nature);
                        adapter.notifyDataSetChanged();
                    }
                });
                th.start();
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        Button btncn = (Button) dialog.findViewById(R.id.btncn);
        btncn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });
        Thread treadbd = new Thread(new Runnable() {
            @Override
            public void run() {
                AdapterRegion adapterRegion = new AdapterRegion(getContext(),R.layout.spnner_item,db.regionsDao().getAllRegions());
                AdapterCountries adapterCountries = new AdapterCountries(getContext(),R.layout.spnner_item,db.countriesDao().getAllCountries());
                spinnerRegion.setAdapter(adapterRegion);
                spinnerCountry.setAdapter(adapterCountries);
            }
        });
        treadbd.start();
        dialog.show();
    }
}