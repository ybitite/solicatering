package com.example.marokkanischbernessen.ripository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.Panier;
import com.example.marokkanischbernessen.db.entity.PanierWithAarticlePanierAndPlat;
import com.example.marokkanischbernessen.db.room.AppDatabase;
import com.example.marokkanischbernessen.db.room.DAO.PanierDAO;

import java.util.List;

public class PanierRipository {
    //FIELD
    final PanierDAO panierDAO;
    final LiveData<List<PanierWithAarticlePanierAndPlat>> listPanierWithAarticlePanier;

    //CONSTRUCTOR
    public PanierRipository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST PANIER FROM DAO
        panierDAO = db.panierDAO();
        listPanierWithAarticlePanier = panierDAO.getAllPanierWithArticlePanier();
    }

    //METHODE

    //insert new panier in data base (the call of methode is not on main thread)
    public void insertPanier(Panier panier) {
        panierDAO.insert(panier);
    }

    //return observable livedata of panier with article panier.
    public LiveData<List<PanierWithAarticlePanierAndPlat>> getListPanierWithArticlePanier() {
        return listPanierWithAarticlePanier;
    }

    //DELETE CURENT PANIER IN THE SEM CURENT PANIER
    public void deleteCurentPanier(int idPanier) {
        //DO THE DELETE IN NON-UI THREAD
        AppDatabase.databaseWriteExecutor.execute(() -> {
            panierDAO.deleteCurentPanier(idPanier);
        });
    }


}
