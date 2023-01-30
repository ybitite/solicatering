package com.example.marokkanischbernessen.ui.historique;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.PanierWithAarticlePanier;
import com.example.marokkanischbernessen.ripository.PanierRipository;

import java.util.List;

public class HistoriqueViewModel extends AndroidViewModel {
    //FIELD
    private final PanierRipository panierRipository;
    private final LiveData<List<PanierWithAarticlePanier>> allPanierWithArticlePanier;

    //CONSTRUCTOR
    public HistoriqueViewModel(Application application) {
        super(application);
        panierRipository = new PanierRipository(application.getBaseContext());
        allPanierWithArticlePanier = panierRipository.getListPanierWithArticlePanier();
    }

    /*TO OBSERVED LIVE DATA LIST PANIER FROM REPOSITORY*/
    public LiveData<List<PanierWithAarticlePanier>> getAllPanierWithArticlePanier() {
        return allPanierWithArticlePanier;
    }

}