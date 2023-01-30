package com.example.marokkanischbernessen.ui.categorie;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.Categorie;
import com.example.marokkanischbernessen.ripository.CategorieRipository;

import java.util.List;

public class CategorieViewModel extends AndroidViewModel {
    //FIELD
    private final CategorieRipository categorieRipository;
    private final LiveData<List<Categorie>> allCategorie;


    //CONSTRUCTOR
    public CategorieViewModel(Application application) {
        super(application);
        categorieRipository = new CategorieRipository(application.getBaseContext());
        allCategorie = categorieRipository.getListCategorie();


    }

    /*TO OBSERVED LIVE DATA LIST CATEGORIE FROM REPOSITORY*/
    public LiveData<List<Categorie>> getAllCategorie() {
        return allCategorie;
    }

}