package com.example.roomdatabase.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "regions")
public class Regions {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name_region")
    public String nameRegion;

    public Regions(int id, String nameRegion) {
        this.id = id;
        this.nameRegion = nameRegion;
    }

    @Ignore
    public Regions(String nameRegion){
        this.nameRegion = nameRegion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public void setNameRegion(String nameRegion) {
        this.nameRegion = nameRegion;
    }
}
