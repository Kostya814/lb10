package com.example.roomdatabase.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "types_fish")

public class TypesFish {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "type_name")
    private String name;

    public TypesFish(long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Ignore
    public TypesFish(String name){
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
