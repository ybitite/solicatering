package website.livingRoom.soliCatering.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.ArticlePanier;
import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.model.room.dao.ArticlePanierDAO;

public class ArticlePanierRepository {
    //FIELD
    final ArticlePanierDAO articlePanierDAO;
    LiveData<List<ArticlePanierAndPlat>> listArticlePanierWithPlat = new LiveData<>() {
    };

    //CONSTRUCTOR
    public ArticlePanierRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST ARCTILE PANIER FROM dao
        articlePanierDAO = db.articlePanierDAO();
    }

    //RETURN OBSERVABLE LIVEDATA OF ARTICLE PANIER.
    public LiveData<List<ArticlePanierAndPlat>> getListArticlePanierWithPlat(int idPanier) {
        listArticlePanierWithPlat = articlePanierDAO.getArticlePanierWithPlats(idPanier);
        return listArticlePanierWithPlat;
    }

    //find article panier call form background thread
    public boolean finArticlePanier(ArticlePanier articlePanier) {
        return articlePanierDAO.getArticlePanier(articlePanier.getIdPlats(), articlePanier.getIdPanier()) != null;
    }

    //INSERT NEW ARTICLE PANIER IN DATA BASE call form background thread
    public void insert(ArticlePanier articlePanier) {
        articlePanierDAO.insert(articlePanier);
    }

    //UPDATE GIVEN ARTICLE PANIER call form background thread
    public void updateArticlePanier(ArticlePanier articlePanier, int nombre) {
        articlePanierDAO.updateNombreArticlePanier(articlePanier.getIdPlats( ),articlePanier.getIdPanier( ), nombre);
    }

    //DELETE CURENT PANIER IN THE SEM CURENT PANIER
    public void deleteCurentPanier(int idPanier) {
        //DO THE DELETE IN NON-UI THREAD
        AppDatabase.getDatabaseWriteExecutor().execute(() -> {
            try {
                articlePanierDAO.deleteArticlePanier(idPanier);
            }
            catch (Exception exception){
                Log.e("delete artPanier failed",exception.getMessage());
            }
        });
    }

    //DELETE GIVING ARTICLE PANIER
    public void deleteArticlePanier(ArticlePanier articlePanier) {
        //DO THE DELETE IN NON-UI THREAD
        AppDatabase.getDatabaseWriteExecutor().execute(() -> {
            try {
                articlePanierDAO.deleteArticlePanier(articlePanier);
            }
            catch (Exception exception){
                Log.e("delete artPanier failed",exception.getMessage());
            }
        });
    }
}
