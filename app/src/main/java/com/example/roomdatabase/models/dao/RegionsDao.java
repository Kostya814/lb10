package com.example.roomdatabase.models.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.models.entities.Nature;
import com.example.roomdatabase.models.entities.Regions;

import java.util.List;

@Dao
public interface RegionsDao
{
    @Insert
    void insert(Regions region);
    @Update
    void update(Regions region);
    @Delete
    void delete(Regions region);
    @Query("SELECT * FROM regions")
    List<Regions> getAllRegions();

    @Query("SELECT * FROM regions Where id =:id")
    Regions getRegionById(int id);
}
