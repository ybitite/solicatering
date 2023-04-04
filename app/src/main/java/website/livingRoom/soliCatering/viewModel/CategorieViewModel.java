package website.livingRoom.soliCatering.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Categorie;
import website.livingRoom.soliCatering.repository.CategorieRepository;

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