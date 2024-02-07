package com.example.roomdatabase.models.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabase.models.entities.Addresses;
import com.example.roomdatabase.models.entities.Nature;

import java.util.List;

@Dao
public interface AddressesDao {
    @Insert
    long insert(Addresses addresses);
    @Update
    void update(Addresses addresses);
    @Delete
    void delete(Addresses addresses);
    @Query("SELECT * FROM addresses")
    List<Addresses> getAllAddresses();
    @Query("Select * from addresses WHERE id =:id")
    Addresses getAddressById(int id);

}
