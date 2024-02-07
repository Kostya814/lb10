package com.example.roomdatabase.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "fishes",foreignKeys =
@ForeignKey(entity = TypesFish.class,
        parentColumns = "id",
        childColumns = "type_id"),
indices = {@Index(value = {"type_id"})})
public class Fishes {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name_fish")
    private String nameFish;
    @ColumnInfo (name = "type_id")
    private int typeId;

    public Fishes(int id, String nameFish, int typeId) {
        this.id = id;
        this.nameFish = nameFish;
        this.typeId = typeId;
    }

    @Ignore
    public Fishes(String nameFish, int typeId){
        this.nameFish = nameFish;
        this.typeId = typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameFish() {
        return nameFish;
    }

    public void setNameFish(String nameFish) {
        this.nameFish = nameFish;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }
}
