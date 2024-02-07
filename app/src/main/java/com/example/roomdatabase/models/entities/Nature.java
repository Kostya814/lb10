package com.example.roomdatabase.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "nature",
        foreignKeys = {
                @ForeignKey(entity = Fishes.class, parentColumns = "id", childColumns = "fish_id"),
                @ForeignKey(entity = Lakes.class, parentColumns = "id", childColumns = "lake_id")
        },
        indices = {@Index(value = {"fish_id"}),@Index(value = {"lake_id"})})
public class Nature {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "fish_id")
    public int fishId;

    @ColumnInfo(name = "lake_id")
    public int lakeId;

    public Nature(int id, int fishId, int lakeId) {
        this.id = id;
        this.fishId = fishId;
        this.lakeId = lakeId;
    }

    @Ignore
    public Nature(int fishId, int lakeId){
        this.fishId = fishId;
        this.lakeId = lakeId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFishId() {
        return fishId;
    }

    public void setFishId(int fishId) {
        this.fishId = fishId;
    }

    public int getLakeId() {
        return lakeId;
    }

    public void setLakeId(int lakeId) {
        this.lakeId = lakeId;
    }
}