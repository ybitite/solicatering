package website.livingRoom.soliCatering.ripository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Evenement;
import website.livingRoom.soliCatering.db.room.AppDatabase;
import website.livingRoom.soliCatering.db.room.DAO.EvenementDAO;

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



