package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Categorie;
import website.livingRoom.soliCatering.db.room.AppDatabase;
import website.livingRoom.soliCatering.db.room.DAO.CategorieDAO;

import java.util.List;

public class CategorieRepository {
    //FIELD
    private final LiveData<List<Categorie>> listCategorie;

    //CONSTRUCTOR
    public CategorieRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST CATEGORIE FROM DAO
        CategorieDAO categorieDAO = db.categorieDAO();
        listCategorie = categorieDAO.getCategories();
    }

    //RETURN OBSERVABLE LIVEDATA OF CATEGORIE.
    public LiveData<List<Categorie>> getListCategorie() {
        return listCategorie;
    }


}
