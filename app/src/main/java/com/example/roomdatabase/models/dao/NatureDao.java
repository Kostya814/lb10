package com.example.roomdatabase.models.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.models.entities.Nature;

import java.util.List;

@Dao
public interface NatureDao {
    @Insert
    void insert(Nature nature);
    @Update
    void update(Nature nature);
    @Delete
    void delete(Nature nature);
    @Query("SELECT * FROM nature")
    List<Nature> getAllNatures();
    @Query("SELECT * FROM nature WHERE id = :id")
    Nature getNatureById(int id);

}
