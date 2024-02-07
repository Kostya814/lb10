package com.example.roomdatabase.models.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.models.entities.Fishes;

import java.util.List;

@Dao
public interface FishesDao {
    @Insert
    void insert(Fishes fish);
    @Update
    void update(Fishes fish);
    @Delete
    void delete(Fishes fish);

    @Query("Select * from fishes")
    List<Fishes> getAllFishes();

    @Query("Select * from fishes WHERE id =:id")
    Fishes getFishById(int id);
}
