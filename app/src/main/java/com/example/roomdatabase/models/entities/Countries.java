package com.example.roomdatabase.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "countries")
public class Countries {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name_country")
    public String nameCountry;

    public Countries(int id, String nameCountry) {
        this.id = id;
        this.nameCountry = nameCountry;
    }

    @Ignore
    public Countries(String nameCountry){
        this.nameCountry = nameCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }
}