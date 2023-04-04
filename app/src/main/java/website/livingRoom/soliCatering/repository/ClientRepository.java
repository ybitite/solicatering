package website.livingRoom.soliCatering.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import website.livingRoom.soliCatering.model.entitys.Client;
import website.livingRoom.soliCatering.model.room.AppDatabase;
import website.livingRoom.soliCatering.model.room.dao.ClientDAO;

public class ClientRepository {
    //FIELD
    final ClientDAO clientDAO;
    LiveData<List<Client>> client;

    //CONSTRUCTOR
    public ClientRepository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST CLIENT FROM dao
        clientDAO = db.clientDAO();
    }

    //RETURN OBSERVABLE LIVEDATA OF CLIENT.
    public LiveData<List<Client>> getClient() {
        client = clientDAO.getClient();
        return client;
    }

    //insert client "the call of the methode is not from  main thread"
    public void insertClient(Client client) {
        if (clientDAO.update(client) <= 0) {
            clientDAO.insert(client);
        }
    }
}
