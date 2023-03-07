package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.db.entitys.Plat;
import website.livingRoom.soliCatering.db.room.AppDatabase;
import website.livingRoom.soliCatering.db.room.DAO.PlatDAO;

public class PlatRepository {
    //FIELD
    private final PlatDAO platDAO;
    private LiveData<List<Plat>> listPlatofCat;

    //CONSTRUCTOR
    public PlatRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST PLAT FROM DAO
        platDAO = db.platDAO();
    }

    //RETURN OBSERVABLE LIVEDATA OF PLAT FOR THE CATEGORIE PASSED IN PARAM.
    public LiveData<List<Plat>> gatPlats(int pointCat) {
        listPlatofCat = platDAO.getPlats(pointCat);
        return listPlatofCat;
    }
}
