package website.livingRoom.soliCatering.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Plat;

@Dao
public interface PlatDAO {
    //INSER NEW PLAT IN PLATS TABLE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Plat plat);


    //SELECT PLATS FROM PLATS TABLE WHERE THE POINT ARE LIKE ACTUEL CAT PONT
    @Query("SELECT * FROM plats WHERE plats.point LIKE :arg")
    LiveData<List<Plat>> getPlats(int arg);
}
