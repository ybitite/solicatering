package com.example.marokkanischbernessen.ripository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.Categorie;
import com.example.marokkanischbernessen.db.room.AppDatabase;
import com.example.marokkanischbernessen.db.room.DAO.CategorieDAO;

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
