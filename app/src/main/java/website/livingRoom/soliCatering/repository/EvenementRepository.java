package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Evenement;
import website.livingRoom.soliCatering.db.room.AppDatabase;
import website.livingRoom.soliCatering.db.room.DAO.EvenementDAO;

import java.util.List;

public class EvenementRepository {
    //FIELD
    private final LiveData<List<Evenement>> listEvenement;

    //CONSTRUCTOR
    public EvenementRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST EVENT FROM DAO
        EvenementDAO evenementDAO = db.evenementDAO();
        listEvenement = evenementDAO.getEvenements();
    }

    //RETURN OBSERVABLE LIVEDATA OF EVENT.
    public LiveData<List<Evenement>> getListEvenement() {
        return listEvenement;
    }

}



