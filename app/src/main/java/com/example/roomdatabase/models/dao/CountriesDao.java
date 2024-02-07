package com.example.roomdatabase.models.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.models.entities.Addresses;
import com.example.roomdatabase.models.entities.Countries;
import com.example.roomdatabase.models.entities.Nature;
import com.example.roomdatabase.models.entities.Regions;

import java.util.List;

@Dao
public interface CountriesDao {
    @Insert
    void insert(Countries countries);
    @Update
    void update(Countries countries);
    @Delete
    void delete(Countries countries);
    @Query("SELECT * FROM countries")
    List<Countries> getAllCountries();

    @Query("SELECT * FROM countries Where id =:id")
    Countries getCountryById(int id);
}
