package website.livingRoom.soliCatering.ui.categorie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Categorie;
import website.livingRoom.soliCatering.repository.CategorieRepository;

import java.util.List;

public class CategorieViewModel extends AndroidViewModel {
    //FIELD
    private final CategorieRepository categorieRepository;
    private final LiveData<List<Categorie>> allCategorie;


    //CONSTRUCTOR
    public CategorieViewModel(Application application) {
        super(application);
        categorieRepository = new CategorieRepository(application.getBaseContext());
        allCategorie = categorieRepository.getListCategorie();


    }

    /*TO OBSERVED LIVE DATA LIST CATEGORIE FROM REPOSITORY*/
    public LiveData<List<Categorie>> getAllCategorie() {
        return allCategorie;
    }

}