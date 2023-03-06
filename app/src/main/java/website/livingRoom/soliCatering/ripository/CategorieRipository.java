package website.livingRoom.soliCatering.ripository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Categorie;
import website.livingRoom.soliCatering.db.room.AppDatabase;
import website.livingRoom.soliCatering.db.room.DAO.CategorieDAO;

import java.util.List;

public class CategorieRipository {
    //FIELD
    final CategorieDAO categorieDAO;
    final LiveData<List<Categorie>> listCategorie;
    LiveData<String[]> listExempleContenu;
    //CONSTRUCTOR

    public CategorieRipository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST CATEGORIE FROM DAO
        categorieDAO = db.categorieDAO();
        listCategorie = categorieDAO.getCategories();
    }

    //PROPERTY RETURN OBSERVABLE LIVEDATA OF CATEGORIE.
    public LiveData<List<Categorie>> getListCategorie() {

        return listCategorie;
    }


}
