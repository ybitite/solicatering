package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Categorie;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.model.room.dao.CategorieDAO;

public class CategorieRepository {
    //FIELD
    private final LiveData<List<Categorie>> listCategorie;

    //CONSTRUCTOR
    public CategorieRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST CATEGORIE FROM dao
        CategorieDAO categorieDAO = db.categorieDAO();
        listCategorie = categorieDAO.getCategories();
    }

    //RETURN OBSERVABLE LIVEDATA OF CATEGORIE.
    public LiveData<List<Categorie>> getListCategorie() {
        return listCategorie;
    }


}
