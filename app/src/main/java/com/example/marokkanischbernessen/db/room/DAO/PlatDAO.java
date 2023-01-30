package com.example.marokkanischbernessen.db.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.marokkanischbernessen.db.entity.Plat;

import java.util.List;

@Dao
public interface PlatDAO {
    //INSER NEW PLAT IN PLATS TABLE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract void insert(Plat plat);

    //SELECT ALL PLAT FROM PLATS TABLE
    @Query("SELECT * FROM plats")
    LiveData<List<Plat>> getPlats();

    //SELECT PLATS FROM PLATS TABLE WHERE THE POINT ARE LIKE ACTUEL CAT PONT
    @Query("SELECT * FROM plats WHERE plats.point LIKE :arg")
    LiveData<List<Plat>> getPlatsOfCat(int arg);
}
