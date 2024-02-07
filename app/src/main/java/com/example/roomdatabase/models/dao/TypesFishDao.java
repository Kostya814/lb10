package com.example.roomdatabase.models.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.models.entities.Nature;
import com.example.roomdatabase.models.entities.Regions;
import com.example.roomdatabase.models.entities.TypesFish;

import java.util.List;

@Dao
public interface TypesFishDao {
    @Insert
    void insert(TypesFish typesFish);
    @Update
    void update(TypesFish typesFish);
    @Delete
    void delete(TypesFish typesFish);
    @Query("SELECT * FROM types_fish")
    List<TypesFish> getAllTypeFishes();

    @Query("Select * from types_fish WHERE id =:id")
    TypesFish getTypeFishById(int id);
}
