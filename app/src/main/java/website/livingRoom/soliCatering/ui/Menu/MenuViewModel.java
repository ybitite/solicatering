package website.livingRoom.soliCatering.ui.Menu;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Menu;
import website.livingRoom.soliCatering.repository.MenuRepository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    //FIELD
    private final MenuRepository menuRepository;
    private final LiveData<List<Menu>> allMenu;

    //CONSTRUCTOR
    public MenuViewModel(Application application) {
        super(application);
        menuRepository = new MenuRepository(application.getBaseContext());
        allMenu = menuRepository.getMenu();
    }

    /*TO OBSERVED LIVE DATA LIST MENU FROM REPOSITORY*/
    public LiveData<List<Menu>> getAllMenu() {
        return allMenu;
    }
}