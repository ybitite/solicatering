package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.model.room.dao.MenuDAO;

public class MenuRepository {
    //FIELD
    private final MenuDAO menuDAO;
    private final LiveData<List<Menu>> listMenu;

    //CONSTRUCTOR
    public MenuRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST EVENT FROM dao
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
