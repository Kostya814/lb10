package com.example.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.roomdatabase.models.Database;
import com.example.roomdatabase.models.entities.Addresses;
import com.example.roomdatabase.models.entities.Countries;
import com.example.roomdatabase.models.entities.Fishes;
import com.example.roomdatabase.models.entities.Lakes;
import com.example.roomdatabase.models.entities.Nature;
import com.example.roomdatabase.models.entities.Regions;
import com.example.roomdatabase.models.entities.TypesFish;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment1, new NatureFragment(), "Fragment")
                .commit();

    }
}