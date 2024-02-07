package com.example.roomdatabase.models.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "lakes",
        foreignKeys = @ForeignKey(entity = Addresses.class, parentColumns = "id", childColumns = "address_id"),
        indices = {@Index(value = {"address_id"})})
public class Lakes {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name_lake")
    public String nameLake;

    @ColumnInfo(name = "address_id")
    public int addressId;

    public Lakes(int id, String nameLake, int addressId) {
        this.id = id;
        this.nameLake = nameLake;
        this.addressId = addressId;
    }

    @Ignore
    public Lakes(String nameLake, int addressId){
        this.nameLake = nameLake;
        this.addressId = addressId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameLake() {
        return nameLake;
    }

    public void setNameLake(String nameLake) {
        this.nameLake = nameLake;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }
}
