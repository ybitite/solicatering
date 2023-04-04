package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Panier;
import website.livingRoom.soliCatering.model.entitys.PanierWithAarticlePanierAndPlat;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.model.room.dao.PanierDAO;

public class PanierRepository {
    //FIELD
    private final PanierDAO panierDAO;
    private final LiveData<List<PanierWithAarticlePanierAndPlat>> listPanierWithAarticlePanier;

    //CONSTRUCTOR
    public PanierRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST PANIER FROM dao
        panierDAO = db.panierDAO();
        listPanierWithAarticlePanier = panierDAO.getPanierWithArticlePanierAndPlats();
    }

    //METHODE

    //insert new panier in data base (the call of methode is not on main thread)
    public void insertPanier(Panier panier) {
        panierDAO.insert(panier);
    }

    //return observable livedata of panier with article panier.
    public LiveData<List<PanierWithAarticlePanierAndPlat>> getPanierWithArticlePanierAndPlats() {
        return listPanierWithAarticlePanier;
    }



}
