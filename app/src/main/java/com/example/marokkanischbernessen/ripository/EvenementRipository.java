package com.example.marokkanischbernessen.ripository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.Evenement;
import com.example.marokkanischbernessen.db.room.AppDatabase;
import com.example.marokkanischbernessen.db.room.DAO.EvenementDAO;

import java.util.List;

public class EvenementRipository {
    //FIELD
    final EvenementDAO evenementDAO;
    final LiveData<List<Evenement>> listEvenement;

    //CONSTRUCTOR
    public EvenementRipository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST EVENT FROM DAO
        evenementDAO = db.evenementDAO();
        listEvenement = evenementDAO.getEvenements();
    }

    //PROPERTY RETURN OBSERVABLE LIVEDATA OF EVENT.
    public LiveData<List<Evenement>> getListEvenement() {
        return listEvenement;
    }

}



