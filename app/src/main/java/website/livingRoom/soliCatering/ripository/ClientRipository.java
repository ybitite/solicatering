package website.livingRoom.soliCatering.ripository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import website.livingRoom.soliCatering.db.entitys.Client;
import website.livingRoom.soliCatering.db.room.AppDatabase;
import website.livingRoom.soliCatering.db.room.DAO.ClientDAO;

import java.util.List;

public class ClientRipository {
    //FIELD
    final ClientDAO clientDAO;
    LiveData<List<Client>> client;

    //CONSTRUCTOR
    public ClientRipository(Context context) {
        //GET DATA BASE
        AppDatabase db = AppDatabase.getDatabase(context);
        //GET LIVE DATA LIST CLIENT FROM DAO
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
