package website.livingRoom.soliCatering.ripository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Plat;
import website.livingRoom.soliCatering.db.room.AppDatabase;
import website.livingRoom.soliCatering.db.room.DAO.PlatDAO;

import java.util.List;

public class PlatRipository {
    //FIELD
    final PlatDAO platDAO;
    LiveData<List<Plat>> listPlatofCat;

    //CONSTRUCTOR
    public PlatRipository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST PLAT FROM DAO
        platDAO = db.platDAO();
    }

    //PROPERTY RETURN OBSERVABLE LIVEDATA OF PLAT FOR THE CATEGORIE PASSED IN PARAM.
    public LiveData<List<Plat>> gatListPlatOfCat(int pointCat) {
        listPlatofCat = platDAO.getPlats(pointCat);
        Log.i(this.toString(), String.valueOf(pointCat));
        return listPlatofCat;
    }
}
