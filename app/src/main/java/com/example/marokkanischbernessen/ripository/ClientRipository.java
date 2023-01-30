package com.example.marokkanischbernessen.ripository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.marokkanischbernessen.db.entity.Client;
import com.example.marokkanischbernessen.db.room.AppDatabase;
import com.example.marokkanischbernessen.db.room.DAO.ClientDAO;

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
        if (clientDAO.updateClient(client) <= 0) {
            clientDAO.insert(client);
        }
    }
}
