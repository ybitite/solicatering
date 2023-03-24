package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.model.entitys.Menu;
import website.livingRoom.soliCatering.repository.MenuRepository;

import java.util.List;

public class MenuViewModel extends AndroidViewModel {
    //FIELD
    private final MenuRepository menuRepository;
    private final LiveData<List<Menu>> listMenu;

    //CONSTRUCTOR
    public MenuViewModel(Application application) {
        super(application);
        menuRepository = new MenuRepository(application.getBaseContext());
        listMenu = menuRepository.getMenu();
    }

    /*TO OBSERVED LIVE DATA LIST MENU*/
    public LiveData<List<Menu>> getListMenu() {
        return listMenu;
    }
}