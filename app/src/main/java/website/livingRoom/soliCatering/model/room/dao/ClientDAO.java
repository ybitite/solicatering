package website.livingRoom.soliCatering.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import website.livingRoom.soliCatering.model.entitys.Client;

import java.util.List;


@Dao
public interface ClientDAO {
    //todo to test

    //INSER NEW CLIENT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Client client);

    //SELECT CLIENT
    @Query("SELECT * FROM clients")
    LiveData<List<Client>> getClient();

    //UPDATE CLIENT
    @Update
    int update(Client... clients);
}
