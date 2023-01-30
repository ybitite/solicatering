package com.example.marokkanischbernessen.ripository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.Menu;
import com.example.marokkanischbernessen.db.room.AppDatabase;
import com.example.marokkanischbernessen.db.room.DAO.MenuDAO;

import java.util.List;

public class MenuRipository {
    //FIELD
    final MenuDAO menuDAO;
    final LiveData<List<Menu>> listMenu;
    Menu menu;

    //CONSTRUCTOR
    public MenuRipository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST EVENT FROM DAO
        menuDAO = db.menuDAO();
        listMenu = menuDAO.getMenus();
    }

    //PROPERTY RETURN OBSERVABLE LIVEDATA.
    public LiveData<List<Menu>> getListMenu() {
        return listMenu;
    }

    public Menu getMenuByPoint(int actuelMenu) {
        menu = menuDAO.getMenuByPoint(actuelMenu);
        return menu;
    }
}
