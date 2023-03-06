package website.livingRoom.soliCatering.db.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import website.livingRoom.soliCatering.db.entitys.Evenement;

import java.util.List;

@Dao
public interface EvenementDAO {
    //INSER NEW EVENT IN EVENEMENTS TABLE
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract void insert(Evenement evenement);

    //SELECT ALL EVENT FROM EVENEMENTS TABLE
    @Query("SELECT * FROM evenements ORDER BY date DESC")
    LiveData<List<Evenement>> getEvenements();
}
