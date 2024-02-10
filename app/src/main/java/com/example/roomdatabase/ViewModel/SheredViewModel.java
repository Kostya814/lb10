package com.example.roomdatabase.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.roomdatabase.models.Database;
import com.example.roomdatabase.models.entities.Nature;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SheredViewModel extends AndroidViewModel {
    private Database db;
    private MutableLiveData<List<Nature>> nature;

    public SheredViewModel(@NonNull Application application) {
        super(application);
        db =Database.getDatabase(application);
        nature = new MutableLiveData<>();
    }


    public void setNature(MutableLiveData<List<Nature>> nature) {
        this.nature = nature;
    }

    public LiveData<List<Nature>> getNature() {
        return nature;
    }
    public void loadNaturesFromDatabase()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                nature.postValue(db.natureDao().getAllNatures());
            }
        }).start();

    }
}
