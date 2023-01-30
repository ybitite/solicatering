package com.example.marokkanischbernessen.db.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.marokkanischbernessen.db.entity.Menu;

import java.util.List;

@Dao
public interface MenuDAO {
    //INSER NEW MENU in MENUS TABLE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract void insert(Menu menu);

    //SELECT ALL MENU FROM MENUS TABLE
    @Query("SELECT * FROM menus ORDER BY point ASC")
    LiveData<List<Menu>> getMenus();

    @Query("select * from menus where point like :actuelMenu limit 1")
    Menu getMenuByPoint(int actuelMenu);
}
