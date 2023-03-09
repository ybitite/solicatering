package website.livingRoom.soliCatering.ui.categorie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Categorie;
import website.livingRoom.soliCatering.repository.CategorieRepository;

import java.util.List;

public class CategorieViewModel extends AndroidViewModel {
    //FIELD
    private final LiveData<List<Categorie>> listCategorie;


    //CONSTRUCTOR
    public CategorieViewModel(Application application) {
        super(application);
        CategorieRepository categorieRepository = new CategorieRepository(application.getBaseContext());
        listCategorie = categorieRepository.getListCategorie();


    }

    /*TO OBSERVED LIVE DATA LIST CATEGORIE */
    public LiveData<List<Categorie>> getListCategorie() {
        return listCategorie;
    }

}