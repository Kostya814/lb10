package com.example.roomdatabase.models;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.roomdatabase.models.dao.AddressesDao;
import com.example.roomdatabase.models.dao.CountriesDao;
import com.example.roomdatabase.models.dao.FishesDao;
import com.example.roomdatabase.models.dao.LakesDao;
import com.example.roomdatabase.models.dao.NatureDao;
import com.example.roomdatabase.models.dao.RegionsDao;
import com.example.roomdatabase.models.dao.TypesFishDao;
import com.example.roomdatabase.models.entities.Addresses;
import com.example.roomdatabase.models.entities.Countries;
import com.example.roomdatabase.models.entities.Fishes;
import com.example.roomdatabase.models.entities.Lakes;
import com.example.roomdatabase.models.entities.Nature;
import com.example.roomdatabase.models.entities.Regions;
import com.example.roomdatabase.models.entities.TypesFish;

@androidx.room.Database(entities = {Addresses.class, Countries.class, Fishes.class, Lakes.class,Nature.class, Regions.class, TypesFish.class},version = 1)
public abstract class Database extends RoomDatabase {
    public abstract NatureDao natureDao();
    public abstract FishesDao fishesDao();
    public abstract AddressesDao addressesDao();
    public abstract CountriesDao countriesDao();
    public abstract LakesDao lakesDao();
    public abstract RegionsDao regionsDao();
    public abstract TypesFishDao typesFishDao();
    public static volatile Database INSTANCE;
    public static Database getDatabase(Context context)
    {
        if(INSTANCE == null)
        {
            synchronized (Database.class)
            {
                if(INSTANCE == null)
                {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            Database.class,
                            "my_database").build();
                }
            }
        }
        return INSTANCE;
    }
}
