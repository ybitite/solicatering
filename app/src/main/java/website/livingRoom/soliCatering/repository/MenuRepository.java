package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.model.room.DAO.MenuDAO;

import java.util.List;

public class MenuRepository {
    //FIELD
    private final MenuDAO menuDAO;
    private final LiveData<List<Menu>> listMenu;

    //CONSTRUCTOR
    public MenuRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST EVENT FROM DAO
        menuDAO = db.menuDAO();
        listMenu = menuDAO.getMenus();
    }

    //RETURN OBSERVABLE LIVEDATA.
    public LiveData<List<Menu>> getMenu() {
        return listMenu;
    }

    public Menu getMenu(int actuelMenuPoint) {
        return menuDAO.getMenus(actuelMenuPoint);
    }
}
