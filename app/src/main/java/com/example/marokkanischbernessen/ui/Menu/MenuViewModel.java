package com.example.marokkanischbernessen.ui.Menu;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.Menu;
import com.example.marokkanischbernessen.ripository.MenuRipository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    //FIELD
    private final MenuRipository menuRipository;
    private final LiveData<List<Menu>> allMenu;

    //CONSTRUCTOR
    public MenuViewModel(Application application) {
        super(application);
        menuRipository = new MenuRipository(application.getBaseContext());
        allMenu = menuRipository.getListMenu();
    }

    /*TO OBSERVED LIVE DATA LIST MENU FROM REPOSITORY*/
    public LiveData<List<Menu>> getAllMenu() {
        return allMenu;
    }
}