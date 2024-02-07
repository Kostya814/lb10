package com.example.roomdatabase.models.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.models.entities.Countries;
import com.example.roomdatabase.models.entities.Lakes;
import com.example.roomdatabase.models.entities.Nature;

import java.util.List;

@Dao
public interface LakesDao {
    @Insert
    long insert(Lakes lakes);

    @Update
    void update(Lakes lakes);
    @Delete
    void delete(Lakes lakes);
    @Query("SELECT * FROM lakes")
    List<Lakes> getAllLakes();

    @Query("SELECT * FROM lakes Where id =:id")
    Lakes getLakeById(int id);
}
