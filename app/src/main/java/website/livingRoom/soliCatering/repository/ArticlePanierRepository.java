package website.livingRoom.soliCatering.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.model.entitys.ArticlePanier;
import website.livingRoom.soliCatering.model.entitys.ArticlePanierAndPlat;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.model.room.DAO.ArticlePanierDAO;

import java.util.List;

public class ArticlePanierRepository {
    //FIELD
    final ArticlePanierDAO articlePanierDAO;
    LiveData<List<ArticlePanierAndPlat>> listArticlePanierWithPlat = new LiveData<List<ArticlePanierAndPlat>>() {
    };

    //CONSTRUCTOR
    public ArticlePanierRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        Log.i("Article Panier ", db.toString());
        //GET LIVE DATA LIST ARCTILE PANIER FROM DAO
        articlePanierDAO = db.articlePanierDAO();
    }

    //RETURN OBSERVABLE LIVEDATA OF ARTICLE PANIER.
    public LiveData<List<ArticlePanierAndPlat>> getListArticlePanierWithPlat(int idPanier) {
        listArticlePanierWithPlat = articlePanierDAO.getArticlePanierWithPlats(idPanier);
        return listArticlePanierWithPlat;
    }

    //find article panier
    public boolean finArticlePanier(ArticlePanier articlePanier) {
        return articlePanierDAO.getArticlePanier(articlePanier.getIdPlats(), articlePanier.getIdPanier()) != null;
    }

    //INSERT NEW ARTICLE PANIER IN DATA BASE
    public void insert(ArticlePanier articlePanier) {
        articlePanierDAO.insert(articlePanier);
    }

    //UPDATE GIVEN ARTICLE PANIER
    public void updateArticlePanier(ArticlePanier articlePanier, int nombre) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articlePanierDAO.updateNombreArticlePanier(articlePanier.getIdPlats(),
                    articlePanier.getIdPanier(), nombre);
        });
    }

    //DELETE CURENT PANIER IN THE SEM CURENT PANIER
    public void deleteCurentPanier(int idPanier) {
        //DO THE DELETE IN NON-UI THREAD
        AppDatabase.databaseWriteExecutor.execute(() -> {
            articlePanierDAO.deleteArticlePanier(idPanier);
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
