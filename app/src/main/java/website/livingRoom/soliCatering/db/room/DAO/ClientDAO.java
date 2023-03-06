package website.livingRoom.soliCatering.db.room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import website.livingRoom.soliCatering.db.entitys.Client;

import java.util.List;


@Dao
public interface ClientDAO {

    //INSER NEW CLIENT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Client client);

    //SELECT CLIENT
    @Query("SELECT * FROM clients")
    LiveData<List<Client>> getClient();

    //UPDATE CLIENT
    @Update
    int updateClient(Client... clients);
}
