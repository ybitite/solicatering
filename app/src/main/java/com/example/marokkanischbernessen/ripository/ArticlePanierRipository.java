package com.example.marokkanischbernessen.ripository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.ArticlePanier;
import com.example.marokkanischbernessen.db.entity.ArticlePanierAndPlat;
import com.example.marokkanischbernessen.db.room.AppDatabase;
import com.example.marokkanischbernessen.db.room.DAO.ArticlePanierDAO;

import java.util.List;

public class ArticlePanierRipository {
    //FIELD
    final ArticlePanierDAO articlePanierDAO;
    LiveData<List<ArticlePanierAndPlat>> listArticlePanierWithPlat = new LiveData<List<ArticlePanierAndPlat>>() {
    };

    //CONSTRUCTOR
    public ArticlePanierRipository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        Log.i("Article Panier ", db.toString());
        //GET LIVE DATA LIST ARCTILE PANIER FROM DAO
        articlePanierDAO = db.articlePanierDAO();
    }

    //RETURN OBSERVABLE LIVEDATA OF ARTICLE PANIER.
    public LiveData<List<ArticlePanierAndPlat>> getListArticlePanierWithPlat(int idPanier) {
        listArticlePanierWithPlat = articlePanierDAO.getAllArticlePanierWithPlat(idPanier);
        return listArticlePanierWithPlat;
    }

    //find article panier
    public boolean finArticlePanier(ArticlePanier articlePanier) {
        if (articlePanierDAO.findArticlePanier(articlePanier.getIdPlats(), articlePanier.getIdPanier()) != null) {
            return true;
        } else return false;
    }

    //INSERT NEW ARTICLE PANIER IN DATA BASE
    public void insert(ArticlePanier articlePanier) {
        articlePanierDAO.insert(articlePanier);
    }

    //UPDATE GIVEN ARTICLE PANIER
    public void updateArticlePanier(ArticlePanier articlePanier, int nombre) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articlePanierDAO.updateIdAP(articlePanier.getIdPlats(),
                    articlePanier.getIdPanier(), nombre);
        });
    }

    //DELETE CURENT PANIER IN THE SEM CURENT PANIER
    public void deleteCurentPanier(int idPanier) {
        //DO THE DELETE IN NON-UI THREAD
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articlePanierDAO.deleteCurentPanier(idPanier);
        });
    }

    //DELETE GIVING ARTICLE PANIER
    public void deleteArticlePanier(ArticlePanier articlePanier) {
        //DO THE DELETE IN NON-UI THREAD
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articlePanierDAO.deleteArticlePanier(articlePanier);
        });
    }


}
