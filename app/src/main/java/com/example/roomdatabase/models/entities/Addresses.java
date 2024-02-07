package com.example.roomdatabase.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "addresses",
        foreignKeys = {
                @ForeignKey(entity = Countries.class, parentColumns = "id", childColumns = "country_id"),
                @ForeignKey(entity = Regions.class, parentColumns = "id", childColumns = "region_id")
        },
        indices = {@Index(value = {"country_id"}),@Index(value = {"region_id"})}
)
public class Addresses {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "country_id")
    public int countryId;

    @ColumnInfo(name = "region_id")
    public int regionId;

    public Addresses(int id, int countryId, int regionId) {
        this.id = id;
        this.countryId = countryId;
        this.regionId = regionId;
    }

    @Ignore
    public Addresses(int countryId, int regionId){
        this.countryId = countryId;
        this.regionId = regionId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}
