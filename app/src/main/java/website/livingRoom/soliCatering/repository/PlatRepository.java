package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Plat;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.model.room.dao.PlatDAO;

public class PlatRepository {
    //FIELD
    private final PlatDAO platDAO;
    private LiveData<List<Plat>> listPlatofCat;

    //CONSTRUCTOR
    public PlatRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST PLAT FROM dao
        platDAO = db.platDAO();
    }

    //RETURN OBSERVABLE LIVEDATA OF PLAT FOR THE CATEGORIE PASSED IN PARAM.
    public LiveData<List<Plat>> gatPlats(int pointCat) {
        listPlatofCat = platDAO.getPlats(pointCat);
        return listPlatofCat;
    }
}
